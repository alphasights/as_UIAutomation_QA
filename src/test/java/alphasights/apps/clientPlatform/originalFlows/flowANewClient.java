package alphasights.apps.clientPlatform.originalFlows;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.delivery.pages.dashboardPage;
import alphasights.apps.delivery.pages.projectCreationPage;
import alphasights.apps.delivery.pages.projectDetailsPage;
import alphasights.apps.delivery.pages.deliveryBasePage;
import alphasights.apps.pistachio.pages.pistachioBasePage;
import alphasights.apps.pistachio.pages.clientAccountsPage;
import alphasights.apps.pistachio.pages.clientContactsPage;
import alphasights.apps.pistachio.tests.loginTests.login;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;
import static alphasights.apps.pistachio.pages.internalTabNavs.CLIENTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_ACCOUNTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_CONTACTS;

public class flowANewClient extends login {

    pistachioBasePage PistachioBasePage = new pistachioBasePage();
    deliveryBasePage DeliveryBasePage = new deliveryBasePage();

    clientAccountsPage ClientsAccountsPage = new clientAccountsPage();
    clientContactsPage ClientContactsPage = new clientContactsPage();
    dashboardPage DashboardPage = new dashboardPage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();
    projectDetailsPage ProjectDetailsPage = new projectDetailsPage();
    signInPage SignInPage = new signInPage();
    clientPlatformBasePage ClientPlatformBasePage = new clientPlatformBasePage();

    private String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject) obj;
    String clientPlatformUsername = (String)jsonObject.get("clientPlatformUsername");
    String clientPlatformPassword = (String)jsonObject.get("clientPlatformPassword");
    public String dbURL = "jdbc:postgresql://client-portal-qa-staging-db.alphasights.com:5432/production";
    public String dbUserId = (String) jsonObject.get("clientPlatformDBUser");
    public String dbPassword = (String) jsonObject.get("clientPlatformDBPassword");
    public String deleteProfileDetailsAndInvites =
            "delete from client_profile_updates\n"+
                    "where client_profile_id in (select id from client_profiles where email like 'automationtester%');"+
                    "delete from client_profiles\n" +
                    "where email like 'automationtester%';\n" +
                    "delete from invitations\n" +
                    "where email like 'automationtester%';";


    public flowANewClient() throws IOException, ParseException {
    }

    @BeforeGroups(groups = {"Pistachio"})
    public void automationClientAccountSetup() throws InterruptedException {
        //verify Client Account Page is active
        PistachioBasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_ACCOUNTS);
        ClientsAccountsPage
                .pageLoaded()
                .searchAccount("AS_QA_Automation_Account")//Set to match your Account Details JSON
                .verifyIfAccountExistsIfNotCreate();
    }

    @Test(groups = {"Pistachio", "New Client Contact", "PTO_new_contact"})
    public void createClientContact_PTO() throws InterruptedException
    {
        PistachioBasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_CONTACTS);
        ClientContactsPage
                .pageLoaded()
                .searchContact("Automation Tester A")
                .verifyIfContactExistsIfNotCreate();
        sendPortalInvitation_PTO();
    }

    public void sendPortalInvitation_PTO()
    {
        ClientContactsPage
                .clickSendInvite()
                .verifySendInviteModalDisplays()
                .clickYesSendInvite()
                .verifyPortalInviteConfirmation();
    }

    @Test(groups = {"Delivery", "New Client Contact", "Delivery_new_contact" })
    public void createClientContact_Delivery_SendInviteFromPTO() throws InterruptedException {
        clientContactCreation_Delivery();
        DeliveryBasePage
                .clickUserNavDropdown();
        PistachioBasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_CONTACTS);
        ClientContactsPage
                .pageLoaded()
                .searchContact("Automation Tester A")
                .selectSearchedContact("Automation Tester A");
        sendPortalInvitation_PTO();
    }

    @Test(groups = {"Delivery"})
    public void createClientContact_Delivery_SendInviteFromDelivery() throws InterruptedException {
        clientContactCreation_Delivery();
        sendPortalInvitation_Delivery();
    }

    public void clientContactCreation_Delivery() throws InterruptedException {
        createProjectStandard();
        ProjectDetailsPage
                .clickEditProject()
                .addNewClientContact();
    }

    public void sendPortalInvitation_Delivery()
    {
        ProjectDetailsPage
                .clickPortalInviteLink(1)
                .verifySendPortalInviteModalDisplays()
                .clickConfirmSendPortalInvite()
                .verifyPortalInviteCompleted(1);
    }

    @Test(groups = {"ClientPlatform"})
    public void signOutOfPortal() throws InterruptedException {
        SignInPage
                .enterUserName(clientPlatformUsername)
                .enterPassword(clientPlatformPassword)
                .verifyLoginStandard();
        ClientPlatformBasePage
                .clickUserNavDropdown()
                .clickSignOut();
    }

    @AfterMethod(groups = {"New Client Contact"})
    public void deleteClientContact() throws SQLException, ClassNotFoundException{
        ClientContactsPage
                .clickDeleteContact()
                .confirmDeleteContact()
                .verifyClientDeletionMessage();
        ensureInvitationAndClientProfileDataCleared();
        WebDriverRunner.closeWebDriver();
    }

    public void ensureInvitationAndClientProfileDataCleared() throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection(dbURL, dbUserId, dbPassword);
        Class.forName("org.postgresql.Driver");
        Statement stmt = con.createStatement();
        stmt.executeUpdate(deleteProfileDetailsAndInvites);
        System.out.println("Newly created contact has been deleted.");
    }

    public void createProjectStandard()
    {
        DashboardPage
                .pageLoad()
                .clickNewProject();
        ProjectCreationPage
                .createProject(ON_COMPLETION, "Alpha Test", "Competitors", "Test Competitor Angle")
                .clickCreateProjects()
                .verifyProjectCreated();
    }

    @AfterTest
    public void flowTeardown()
    {
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

}