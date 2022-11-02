package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import java.time.Duration;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class basePage {
    //region Variables
    //endregion

    //region Locators
    //region TopNav
    public SelenideElement profileLinkDelivery = $("div.account");
    public SelenideElement newIdeaLink = $(By.linkText("New Idea"));
    public SelenideElement firmPulseLink = $(By.linkText("Firm Pulse"));
    public SelenideElement activityLink = $(By.linkText("Activity"));
    public SelenideElement manualsLink = $(By.linkText("Manuals"));
    public SelenideElement signOut = $(By.linkText("Sign Out"));
    public SelenideElement search = $(By.id("search_search"));
    //endregion

    //region Internal Tab Nav
    public SelenideElement me = $("ul#main > li:nth-child(1)");
    public SelenideElement townHall = $(By.linkText("Town Hall"));
    public SelenideElement projects = $(("ul#projects_sub.semantic_navigation"));
    public SelenideElement experts = $(By.linkText("Experts"));
    public SelenideElement clients = $(By.linkText("Clients"));
    public SelenideElement finance = $(By.linkText("Finance"));
    public SelenideElement metrics = $(By.linkText("Metrics"));
    public SelenideElement hr = $(By.linkText("HR"));
    //endregion

    //region Internal Tab subnav
    //region me subnav
    public SelenideElement dashboardLink = $(By.linkText("Dashboard..."));
    public SelenideElement profileLink = $(By.linkText("Profile"));
    public SelenideElement contactsLink = $(By.linkText("Contacts"));
    public SelenideElement myAccountsLink = $(By.linkText("Accounts"));
    public SelenideElement weeklyLink = $(By.linkText("Weekly"));
    public SelenideElement individualPerformanceLink = $(By.linkText("Individual Performance"));
    public SelenideElement teamPerformanceLink = $(By.linkText("Team performance"));
    public SelenideElement officePerformanceLink = $(By.linkText("Office performance"));
    public SelenideElement accountProjectsLink = $(By.linkText("Account projects"));
    public SelenideElement unusedAdvisorsLink = $(By.linkText("Unused Advisors"));
    public SelenideElement signatureLink = $(By.linkText("Signature"));
    public SelenideElement clientWidgetLink = $(By.linkText("Client Widgets"));
    //endregion

    //region townHall subnav
    public SelenideElement facebookLink = $(By.linkText("Facebook"));
    public SelenideElement phonebookLink = $(By.linkText("Phonebook"));
    public SelenideElement manualsAndDocumentsLink = $(By.linkText("Manuals & Documents"));
    public SelenideElement alphaWellLink = $(By.linkText("AlphaWell..."));
    public SelenideElement travelLink = $(By.linkText("Travel..."));
    public SelenideElement restrictedListLink = $(By.linkText("Restricted List"));
    //endregion

    //region projects subnav
    public SelenideElement projectsListLink = $("ul#projects_sub.semantic_navigation > li:nth-child(1) > a");
    public SelenideElement whiteboardLink = $(By.linkText("Whiteboard..."));
    public SelenideElement todaysInteractionsLink = $(By.linkText("Today's Interactions"));
    public SelenideElement upcomingInteractionsLink = $(By.linkText("Upcoming Interactions"));
    public SelenideElement interactionHistoryLink = $(By.linkText("Interaction History"));
    public SelenideElement emailTemplatesLink = $(By.linkText("Email Templates"));
    public SelenideElement industriesLink = $(By.linkText("Industries"));
    //endregion

    //region experts subnav
    public SelenideElement expertSearchLink = $(By.linkText("Search"));
    public SelenideElement companiesLink = $(By.linkText("Companies"));
    public SelenideElement researchToolsLink = $(By.linkText("Research Tools"));
    public SelenideElement termsLink = $(By.linkText("Terms"));
    public SelenideElement doNotCallLink = $(By.linkText("Do Not Call"));
    //endregion

    //region clients subnav
    public SelenideElement globalLink = $(By.linkText("Global"));
    public SelenideElement entitiesLink = $(By.linkText("Entities"));
    public SelenideElement clientAccountsLink = $(By.linkText("Accounts"));
    public SelenideElement clientContactsLink = $("ul#clients_sub > li:nth-child(4) > a");
    public SelenideElement clientTargetGroupsLink = $(By.linkText("Client Target Groups"));
    public SelenideElement clientSettingsLink = $(By.linkText("Client Settings"));
    public SelenideElement complianceLoginsLink = $(By.linkText("Compliance Logins"));
    public SelenideElement marketingPackagesLink = $(By.linkText("Marketing Packages"));
    public SelenideElement ourServicesLink = $(By.linkText("Our Services Link"));

    //endregion

    //region finance subnav
    public SelenideElement takenRevenueLink = $(By.linkText("Taken Revenue"));
    public SelenideElement deferredRevenueLink = $(By.linkText("Deferred Revenue"));
    public SelenideElement summaryLink = $(By.linkText("Summary"));
    public SelenideElement accrualsLink = $(By.linkText("Accruals"));
    public SelenideElement builderLink = $(By.linkText("Builder"));
    public SelenideElement trackerLink = $(By.linkText("Tracker"));
    public SelenideElement prepayLink = $(By.linkText("Prepay"));
    public SelenideElement advisorInvoicesLink = $(By.linkText("Advisor Invoices"));
    public SelenideElement chequesLink = $(By.linkText("Cheques"));
    public SelenideElement raisedLink = $(By.linkText("Raised"));
    public SelenideElement unraisedLink = $(By.linkText("Unraised"));
    public SelenideElement charityLink = $(By.linkText("Charity"));
    public SelenideElement refundsLink = $(By.linkText("Refunds"));
    public SelenideElement adjustmentsLink = $(By.linkText("Adjustments"));
    //endregion

    //region metrics subnav
    public SelenideElement reportingWorldLink = $(By.linkText("Reporting World..."));
    //endregion

    //region hr subnav
    public SelenideElement employeesLink = $(By.linkText("Employee"));
    public SelenideElement teamsLink = $(By.linkText("Teams"));
    public SelenideElement whiteboardsLink = $(By.linkText("Whiteboards"));
    public SelenideElement bonusAssignmentsLink = $(By.linkText("Bonus Assignments"));
    //endregion

    //region Methods
    //region Actions
    public basePage clickMainLink(internalTabNavs mainLinkName )
    {
        switch(mainLinkName){
            case ME:
                $(me).shouldBe(editable);
                me.click();
                break;
            case TOWN_HALL:
                $(townHall).shouldBe(editable);
                townHall.click();
                break;
            case PROJECTS:
                $(projects).shouldBe(editable);
                projects.click();
                break;
            case EXPERTS:
                $(experts).shouldBe(editable);
                experts.click();
                break;
            case CLIENTS:
                $(clients).shouldBe(editable);
                clients.click();
                break;
            case METRICS:
                $(metrics).shouldBe(editable);
                metrics.click();
                break;
            case HR:
                $(hr).shouldBe(editable);
                hr.click();
                break;
        }
        return this;
    }


    public basePage clickSubNavLink(pistachioPages subNavlinkName) throws InterruptedException {

        switch(subNavlinkName) {
            case NEW_IDEA:
                $(newIdeaLink).shouldBe(editable);
                while (!newIdeaLink.isSelected()) {
                    newIdeaLink.click();
                }
                break;
            case FIRM_PULSE:
                $(firmPulseLink).shouldBe(editable);
                while (!firmPulseLink.isSelected()){
                    firmPulseLink.click();
                }
                break;
            case ACTIVITY:
                $(activityLink).shouldBe(editable);
                activityLink.click();
                break;
            case MANUALS:
                $(manualsLink).shouldBe(editable);
                manualsLink.click();
                break;
            case DASHBOARD:
                $(dashboardLink).shouldBe(editable);
                dashboardLink.click();
                break;
            case PROFILE:
                $(profileLink).shouldBe(editable);
                profileLink.click();
                break;
            case CONTACTS:
                $(contactsLink).shouldBe(editable);
                contactsLink.click();
                break;
            case MY_ACCOUNTS:
                $(myAccountsLink).shouldBe(editable);
                myAccountsLink.click();
                break;
            //Need to complete rest of Profile, and entirety of Town Hall, Finance, Metrics and HR links
            case PROJECTS:
                $(projectsListLink).shouldBe(editable);
                projectsListLink.click();
                break;
            case WHITEBOARD:
                $(whiteboardLink).shouldBe(editable);
                whiteboardLink.click();
                break;
            case TODAYS_INTERACTIONS:
                $(todaysInteractionsLink).shouldBe(editable);
                todaysInteractionsLink.click();
                break;
            case UPCOMING_INTERACTIONS:
                $(upcomingInteractionsLink).shouldBe(editable);
                upcomingInteractionsLink.click();
                break;
            case INTERACTION_HISTORY:
                $(interactionHistoryLink).shouldBe(editable);
                interactionHistoryLink.click();
                break;
            case EMAIL_TEMPLATES:
                $(emailTemplatesLink).shouldBe(editable);
                emailTemplatesLink.click();
                break;
            case INDUSTRIES:
                $(industriesLink).shouldBe(editable);
                industriesLink.click();
                break;
            case EXPERT_SEARCH:
                $(expertSearchLink).shouldBe(editable);
                expertSearchLink.click();
                break;
            case COMPANIES:
                $(companiesLink).shouldBe(editable);
                companiesLink.click();
                break;
            case RESEARCH_TOOLS:
                $(researchToolsLink).shouldBe(editable);
                researchToolsLink.click();
                break;
            case TERMS:
                $(termsLink).shouldBe(editable);
                termsLink.click();
                break;
            case DO_NOT_CALL:
                $(doNotCallLink).shouldBe(editable);
                doNotCallLink.click();
                break;
            case GLOBAL:
                $(globalLink).shouldBe(editable);
                globalLink.click();
                break;
            case ENTITIES:
                $(entitiesLink).shouldBe(editable);
                entitiesLink.click();
                break;
            case CLIENT_ACCOUNTS:
                $(clientAccountsLink).should(editable, Duration.ofSeconds(10)).click();
                Assert.assertTrue((WebDriverRunner.getWebDriver().getCurrentUrl().equals("https://qa-pistachio.alphasights.com/client/accounts")));
                break;
            case CLIENT_CONTACTS:
                $(clientContactsLink).should(editable, Duration.ofSeconds(10)).click();
                Assert.assertTrue((WebDriverRunner.getWebDriver().getCurrentUrl().equals("https://qa-pistachio.alphasights.com/client/contacts")));
                break;
            case CLIENT_TARGET_GROUPS:
                $(clientTargetGroupsLink).shouldBe(editable);
                clientTargetGroupsLink.click();
                break;
            case CLIENT_SETTINGS:
                $(clientSettingsLink).shouldBe(editable);
                clientSettingsLink.click();
                break;
            case COMPLIANCE_LOGINS:
                $(complianceLoginsLink).shouldBe(editable);
                companiesLink.click();
                break;
            case MARKETING_PACKAGES:
                $(marketingPackagesLink).shouldBe(editable);
                marketingPackagesLink.click();
                break;
            case OUR_SERVICES_LINK:
                $(ourServicesLink).shouldBe(editable);
                ourServicesLink.click();
                break;
            default:
                System.out.println("An invalid link name was entered.");
                break;
        }
        return this;
    }

    public basePage clickSignOut()
    {
        $(signOut).shouldBe(editable);
        signOut.click();
        return this;
    }

    public basePage clickSearch()
    {
        $(search).shouldBe(editable);
        search.click();
        return this;
    }

    public basePage enterSearch(String query)
    {
        $(search).shouldBe(editable);
        search.sendKeys(query);
        return this;
    }
    //endregion

    //region Assertions
    //endregion

    //endregion

    public basePage()
    {
    }
}
