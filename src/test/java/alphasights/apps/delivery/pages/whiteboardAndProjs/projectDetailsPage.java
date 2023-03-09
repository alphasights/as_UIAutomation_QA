package alphasights.apps.delivery.pages.whiteboardAndProjs;

import alphasights.apps.delivery.pages.deliveryBasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class projectDetailsPage extends deliveryBasePage {

    //region Variable
    //endregion

    //region Locators
    public SelenideElement projectExternalTitle = $("div.aui-text-section");
    public SelenideElement projectCodeName = $("div.project-header-details__item--codename");
    public SelenideElement projAccountLink = $("a.aui-text-primary-1.aui-no-underline");

    //region Project Sub Nav
    public SelenideElement detailsLink = $x("//a[text()= 'Details']");
    public SelenideElement alphaSearchLink = $x("//a[text()= 'AlphaSearch']");
    public SelenideElement recruitingLink = $x("//a[text()= 'Recruiting']");
    public SelenideElement proposalLink = $x("//a[text()= 'Proposal']");
    public SelenideElement interactionsLink = $x("//a[text()= 'Interactions']");
    public SelenideElement surveysLink = $("a.project-header-nav__item--surveys");
    public SelenideElement activityLink = $("a.project-header-nav__item--activity");
    public SelenideElement researchLink = $x("//a[text()= 'Research']");
    //endregion

    public SelenideElement editProject = $("button#project-details-edit-project");
    public ElementsCollection sendPortalInviteLink = $$("a.project-detail__portal-invite-link");
    public SelenideElement sendPortalInviteModal = $("div.as-overlay__wrapper");
    public SelenideElement confirmSendPortalInvite = $x("//button[text()='Yes, send invite']");
    public SelenideElement cancelSendPortalInvite = $x("//button[text()='No, cancel']");
    public ElementsCollection viewAsClientLink = $$("a.project-detail__reactivate-profile-link");

    public projectDetailsPage(){
    }
    //endregion
    //endregion

    //region Methods
    public projectDetailsPage clickEditProject()
    {
        $(editProject).shouldBe(interactable);
        editProject.click();
        return this;
    }

    public projectDetailsPage clickPortalInviteLink(int index)
    {
        $(sendPortalInviteLink.last()).shouldBe(editable);
        actions().moveToElement(sendPortalInviteLink.get(index));
        sendPortalInviteLink.get(index).click();
        return this;
    }

    public projectDetailsPage verifySendPortalInviteModalDisplays()
    {
        $(sendPortalInviteModal).shouldBe(enabled);
        return this;
    }

    public projectDetailsPage clickConfirmSendPortalInvite()
    {
        $(confirmSendPortalInvite).shouldBe(editable);
        confirmSendPortalInvite.click();
        return this;
    }

    public projectDetailsPage verifyPortalInviteCompleted(int index)
    {
        $(sendPortalInviteLink.last()).shouldBe(editable);
        System.out.println(sendPortalInviteLink.get(index).text());
        Assert.assertTrue(sendPortalInviteLink.get(index).text().contains("Re-send Portal Invite"));
        return this;
    }

    public projectDetailsPage clickViewAsClientLink(int index)
    {
        $(viewAsClientLink.last()).shouldBe(editable);
        viewAsClientLink.get(index).click();
        return this;
    }

    public projectDetailsPage verifyProjectCreated()
    {
        $(projectExternalTitle).shouldBe(enabled);
        $(projectExternalTitle).shouldHave(text(projectCreationPage.externalTitleVal));
        return this;
    }

    //endregion
}
