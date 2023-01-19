package alphasights.apps.delivery.pages;

import alphasights.apps.pistachio.pages.pistachioBasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.*;

public class projectDetailsPage extends pistachioBasePage{

    //region Variable
    private String projectDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/contactDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(projectDetails));
    JSONObject jsonObject = (JSONObject)obj;
    public String clientContactName = (String)jsonObject.get("contactName");
    public String clientContactEmail = (String)jsonObject.get("primaryEmail");

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

    //region Client Contact creation features
    public SelenideElement addContact = $("div.aui-mt-4.aui-mb-4 > button.aui-bg-grey-5");
    public SelenideElement newContact = $("div.aui-mt-4.aui-mb-4 > button.aui-bg-white");
    public SelenideElement clientContactSelector = $x("//span[text()= 'Select Client Contact']");
    public ElementsCollection searchInput = $$("input.ember-power-select-search-input");
    public SelenideElement firstEmberSelectionOption = $("li.ember-power-select-option:first-child");
    public SelenideElement emberDropdown = $("div.ember-basic-dropdown-content.ember-power-select-dropdown.ember-basic-dropdown-content--in-place.ember-basic-dropdown-content--left.ember-basic-dropdown-content--below.ember-basic-dropdown--transitioned-in.ember-view");
    //region newContactModal
    public SelenideElement newClientContactModal = $("div.project-form-group--is-modal.as-overlay__content.ember-view");
    public SelenideElement clientContactNameInput = $x("//input[@placeholder='ex. John Smith']");
    public SelenideElement clientContactPrimaryEmailInput = $x("//input[@placeholder='ex. john@alphasights.com']");
    public SelenideElement saveClientContact = $("div.project-form-group__row > button.x-form__button.x-form__button--primary.ember-view");
    public SelenideElement lastClientCreated = $x("//span[text() = '" + clientContactName + "']");
    public projectDetailsPage() throws IOException, ParseException {
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

    public projectDetailsPage clickAddContact()
    {
        $(addContact).shouldBe(editable);
        addContact.click();
        return this;
    }

    public projectDetailsPage clickNewContact()
    {
        $(newContact).shouldBe(editable);
        newContact.click();
        return this;
    }

    public projectDetailsPage addNewClientContact()
    {
        $(newContact.shouldBe(interactable));
        clickNewContact();
        $(clientContactNameInput).shouldBe(editable);
        clientContactNameInput.sendKeys(clientContactName);
        $(clientContactPrimaryEmailInput).shouldBe(editable);
        clientContactPrimaryEmailInput.sendKeys(clientContactEmail);
        $(saveClientContact).shouldBe(editable);
        saveClientContact.click();
        verifyNewClientContactAdded();
        return this;
    }

    public projectDetailsPage addExistingClientContact()
    {
        clickAddContact();
        $(clientContactSelector).shouldBe(editable);
        clientContactSelector.click();
        //Send keys to input
        //select first option method
        return this;
    }

    public projectDetailsPage verifyNewClientContactAdded()
    {
        $(lastClientCreated).should(exist);
        System.out.println("New Client contact successfully added via Delivery.");
        return this;
    }



    public projectDetailsPage selectFirstOptionEmberDropdown(String inputVal)
    {
        $(searchInput.last()).shouldBe(editable);
        searchInput.get(0).click();
        searchInput.get(0).clear();
        searchInput.get(0).sendKeys(inputVal);
        $(emberDropdown).should(editable, Duration.ofSeconds(5));
        if (inputVal == clientContactName) {
            $(firstEmberSelectionOption).shouldNotBe(partialText("Searching for contacts..."), Duration.ofSeconds(5));
        }
        firstEmberSelectionOption.click();
        return this;
    }
    //endregion
}
