package alphasights.apps.delivery.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Collection;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class deliveryBasePage {
    //region Main Nav Links
    public SelenideElement dashboardLink = $(By.linkText("Dashboard"));

    public SelenideElement whiteboardAndProjectsLink = $(By.linkText("Whiteboard & Projects"));

    public SelenideElement quickLink = $("a.top-nav__item__link.top-nav__item__link--quick-links");
    public SelenideElement performanceLink = $("a.top-nav__item__link.top-nav__item__link--performance");
    public SelenideElement resourceHubLink = $("a.top-nav__item__link.top-nav__item__link--resource-hub");
    public SelenideElement commercialHubLink = $("a.top-nav__item__link.top-nav__item__link--commercial-hub");
    //endregion

    //region Sub Nav Links
    public SelenideElement projectSearchLink = $(By.linkText("Project Search"));
    public SelenideElement newProjectLink = $(By.linkText("New Project"));
    public SelenideElement recentProjectsLink = $(By.linkText("Recent Projects"));
    public Collection<SelenideElement> recentlyVisitedProjects = $$("li.top-nav-projects-item.top-nav-projects-item--recent > a");
    public Collection<SelenideElement> starredProjects = $$("li.top-nav-projects-item.top-nav-projects-item--starred > a");
    public SelenideElement quickLinksTodaysInteraction = $(By.linkText("Today's Interaction"));
    public SelenideElement quickLinksUpcomingInteractions = $(By.linkText("Upcoming Interactions"));
    public SelenideElement quickLinksInteractionHistory = $(By.linkText("Interaction History"));
    public SelenideElement quickLinksAdvisorSearch = $(By.linkText("Advisor Search"));
    public SelenideElement quickLinksAccounts = $(By.linkText("Accounts"));
    public SelenideElement quickLinksActivity = $(By.linkText("Activity"));
    public SelenideElement quickLinksClients = $x("//span[text()='Clients']");
    public SelenideElement quickLinkAdmin = $x("//span[text()='Admin']");
    public SelenideElement performanceLinkYourPerformance = $(By.linkText("Your Performance"));
    public SelenideElement performanceLinkTeamPerformance = $(By.linkText("Team Performance"));
    public SelenideElement performanceLinkOfficePerformance = $(By.linkText("Office Performance"));
    //Finish the rest of the Performance links, Resource Hub and Commercial Hub
    //endregion

    //region 3rd level link



    public SelenideElement userNavDropdown = $("ul:last-child > li.top-nav__item.top-nav__item--has-dropdown:last-child");

    public deliveryBasePage clickUserNavDropdown()
    {
        $(userNavDropdown).shouldBe(editable);
        userNavDropdown.click();
        Selenide.switchTo().window(1);
        return this;
    }

    public deliveryBasePage clickWhiteboardAndProjects()
    {
        $(whiteboardAndProjectsLink).shouldBe(editable);
        whiteboardAndProjectsLink.click();
        return this;
    }

    public deliveryBasePage clickNewProject()
    {
        clickWhiteboardAndProjects();
        $(newProjectLink).shouldBe(editable);
        newProjectLink.click();
        return this;
    }
}