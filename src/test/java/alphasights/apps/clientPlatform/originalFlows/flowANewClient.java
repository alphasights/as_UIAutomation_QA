package alphasights.apps.clientPlatform.originalFlows;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.delivery.pages.*;
import alphasights.apps.delivery.pages.whiteboardAndProjs.*;
import alphasights.apps.pistachio.pages.pistachioBasePage;
import alphasights.apps.pistachio.pages.clientAccountsPage;
import alphasights.apps.pistachio.pages.clientContactsPage;
import alphasights.apps.pistachio.tests.loginTests.login;
import alphasights.apps.utilities.jdbcOperations;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;
import static alphasights.apps.pistachio.pages.internalTabNavs.CLIENTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_ACCOUNTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_CONTACTS;
import static com.codeborne.selenide.Selenide.*;

public class flowANewClient extends login {

    //region Variables
    private final String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject) obj;
    String clientPlatformUsername = (String)jsonObject.get("clientPlatformUsername");
    String clientPlatformPassword = (String)jsonObject.get("clientPlatformPassword");
    pistachioBasePage PistachioBasePage = new pistachioBasePage();
    deliveryBasePage DeliveryBasePage = new deliveryBasePage();
    clientAccountsPage ClientsAccountsPage = new clientAccountsPage();
    clientContactsPage ClientContactsPage = new clientContactsPage();
    dashboardPage DashboardPage = new dashboardPage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();
    projectSearchPage ProjectSearchPage = new projectSearchPage();
    projectSearchResultsPage ProjectSearchResultsPage = new projectSearchResultsPage();
    projectResearchPage ProjectResearchPage = new projectResearchPage();
    projectDetailsPage ProjectDetailsPage = new projectDetailsPage();
    projectEditPage ProjectEditPage = new projectEditPage();
    signInPage SignInPage = new signInPage();
    clientPlatformBasePage ClientPlatformBasePage = new clientPlatformBasePage();
    jdbcOperations JDBCOperations = new jdbcOperations();
    //endregion


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

    public void selectFirstProjectFromClientContact()
    {
        ClientContactsPage
                .clickFirstProjFromClientContact();
    }

    public void navigateToClientContactsFromDeliveryBase() throws InterruptedException {
        DeliveryBasePage
                .clickUserNavDropdown();
        PistachioBasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_CONTACTS);
    }

    @Test(groups = {"Delivery", "New Client Contact", "Delivery_new_contact" })
    public void createClientContact_Delivery_SendInviteFromPTO() throws InterruptedException, SQLException, ClassNotFoundException {
        clientContactCreation_Delivery();
        navigateToClientContactsFromDeliveryBase();
        ClientContactsPage
                .pageLoaded()
                .searchContact("Automation Tester A")
                .pageLoadedAfterSearch()
                .selectSearchedContact("Automation Tester A");
        sendPortalInvitation_PTO();
        selectFirstProjectFromClientContact();
        ProjectDetailsPage
                .clickEditProject();
        ProjectEditPage
                .deleteContact(0)
                .saveProjEdits()
                .clickUserNavDropdown();
        PistachioBasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_CONTACTS);
        ClientContactsPage
                .pageLoaded()
                .searchContact("Automation Tester A");

    }

    @Test(groups = {"Delivery", "New Client Contact", "Delivery_new_contact"})
    public void createClientContact_Delivery_SendInviteFromDelivery() throws InterruptedException, SQLException, ClassNotFoundException {
        clientContactCreation_Delivery();
        sendPortalInvitation_Delivery();
    }

    public void clientContactCreation_Delivery() throws InterruptedException, SQLException, ClassNotFoundException {
        JDBCOperations.ensureProjectDoesNotExist();
        if(JDBCOperations.projectExists == true){
            System.out.println("Project already created in this environment.");
            DashboardPage
                    .hoverOverWhiteboardAndProjects()
                    .clickProjectSearch();
            ProjectSearchPage
                    .pageLoad()
                    .clickProjectSearchInput()
                    .enterValueInProjectSearch(ProjectCreationPage.codeNameVal)
                    .clickSearchBtn();
            ProjectSearchResultsPage
                    .clickFirstProjectResult();
            ProjectResearchPage
                    .clickProjectName();
        }
        else{
            createProjectStandard();
        }
        ProjectDetailsPage
                .clickEditProject();
        ProjectEditPage
                .addNewClientContact()
                .saveProjEdits();
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
        JDBCOperations.ensureInvitationAndClientProfileDataCleared();
        vikiToPWScraper();
        WebDriverRunner.closeWebDriver();
    }

    public void createProjectStandard()
    {
        DashboardPage
                .pageLoad()
                .clickNewProjectBtn();
        ProjectCreationPage
                .createProject(ON_COMPLETION, "Alpha Test", "Competitors", "Test Competitor Angle")
                .clickCreateProjects();
        ProjectDetailsPage
                .verifyProjectCreated();
    }
    
    public void vikiToPWScraper() throws SQLException, ClassNotFoundException {
        executeJavaScript("window.open('https://viki.alphasights.com/rds/temp-access', '_blank');");
        switchTo().window(1);
        SelenideElement vikiDB = $(By.id("database"));
        vikiDB.selectOption("pistachio-qa-staging-db.alphasights.com");
        SelenideElement vikiAccessType = $(By.id("type"));
        vikiAccessType.selectOption("read-write");
        SelenideElement vikiSubmit = $x("//button[text()='Submit']]");
        vikiSubmit.click();
        SelenideElement vikiPassword = $("body > div > div > div:nth-child(2)");
        JDBCOperations.vikiPW = vikiPassword.getText();
        int index = JDBCOperations.vikiPW.indexOf(":");
        JDBCOperations.vikiPW = JDBCOperations.vikiPW.substring(index + 2);
        JDBCOperations.disableExistingProj(JDBCOperations.vikiPW);
        Selenide.closeWindow();
    }

    @AfterTest
    public void flowTeardown()
    {
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

}