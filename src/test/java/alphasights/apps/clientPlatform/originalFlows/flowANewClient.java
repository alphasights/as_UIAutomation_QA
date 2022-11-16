package alphasights.apps.clientPlatform.originalFlows;

import alphasights.apps.pistachio.pages.basePage;
import alphasights.apps.pistachio.pages.clientAccountsPage;
import alphasights.apps.pistachio.pages.clientContactsPage;
import alphasights.apps.pistachio.tests.loginTests.loginTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import java.io.IOException;

import static alphasights.apps.pistachio.pages.internalTabNavs.CLIENTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_ACCOUNTS;
import static alphasights.apps.pistachio.pages.pistachioPages.CLIENT_CONTACTS;
import static com.codeborne.selenide.Configuration.timeout;

public class flowANewClient extends loginTests {

    basePage BasePage = new basePage();
    clientAccountsPage ClientsAccountsPage = new clientAccountsPage();
    clientContactsPage ClientContactsPage = new clientContactsPage();

    public flowANewClient() throws IOException, ParseException {
    }

    @BeforeGroups(groups = {"Pistachio"})
    public void automationClientAccountSetup() throws InterruptedException {
        //verify Client Account Page is active
        BasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_ACCOUNTS);
        ClientsAccountsPage
                .pageLoaded()
                .searchAccount("AS_QA_Automation_Account3")//Set to match your Account Details JSON
                .verifyIfAccountExistsIfNotCreate();
    }

    @Test(groups = {"Pistachio", "New Client"})
    public void createClientContact_PTO() throws InterruptedException {
        BasePage
                .clickMainLink(CLIENTS)
                .clickSubNavLink(CLIENT_CONTACTS);
        ClientContactsPage
                .pageLoaded()
                .searchContact("Automation Tester A")
                .verifyIfContactExistsIfNotCreate();
    }

    @Test(groups = {"Pistachio", "New Client"})
    public void sendPortalInvitation_PTO()
    {

    }

    @Test(groups = {"Pistachio", "New Client"})
    public void createClientContact_Delivery()
    {

    }







}