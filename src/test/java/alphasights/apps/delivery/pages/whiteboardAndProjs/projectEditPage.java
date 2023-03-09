package alphasights.apps.delivery.pages.whiteboardAndProjs;

import alphasights.apps.delivery.pages.deliveryBasePage;
import alphasights.apps.utilities.jdbcOperations;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.awt.SystemColor.window;
import static org.openqa.selenium.Keys.HOME;
import static org.openqa.selenium.Keys.PAGE_UP;

public class projectEditPage extends deliveryBasePage {
    //region Variables
    private final String projectDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/contactDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(projectDetails));
    JSONObject jsonObject = (JSONObject)obj;
    public String clientContactName = (String)jsonObject.get("contactName");
    public String clientContactEmail = (String)jsonObject.get("primaryEmail");
    //endregion

    //region Locators
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
    public SelenideElement editProjectForm = $("div.aui-block.aui-text-body");
    public SelenideElement saveEdits = $("button#btn-project-save");
    public String verifyClientContactExists =
            "delete from client_profile_updates\n"+
                    "where client_profile_id in (select id from client_profiles where email like 'automationtester%');"+
                    "delete from client_profiles\n" +
                    "where email like 'automationtester%';\n" +
                    "delete from invitations\n" +
                    "where email like 'automationtester%';";
    jdbcOperations JDBCOperations = new jdbcOperations();

    public projectEditPage() throws IOException, ParseException {
    }

    //endregion
    public projectEditPage saveProjEdits()
    {
        editProjectForm.click();
        Selenide.actions().sendKeys(HOME);
        $(saveEdits).shouldBe(editable);
        saveEdits.click();
        return this;
    }


    public projectEditPage clickAddContact()
    {
        $(addContact).shouldBe(editable);
        addContact.click();
        return this;
    }

    public projectEditPage clickNewContact()
    {
        $(newContact).shouldBe(editable);
        newContact.click();
        return this;
    }

    public projectEditPage addNewClientContact()
    {

        clickNewContact();
        $(newClientContactModal).is(editable);
        Selenide.switchTo().activeElement();
        $(clientContactNameInput).shouldBe(editable);
        if(JDBCOperations.userExists == true)
        {
            clientContactNameInput.sendKeys(clientContactName + "2");
        }
        else
        {
            clientContactNameInput.sendKeys(clientContactName);
        }
        $(clientContactPrimaryEmailInput).shouldBe(editable);
        clientContactPrimaryEmailInput.sendKeys(clientContactEmail);
        $(saveClientContact).shouldBe(editable);
        saveClientContact.click();
        verifyNewClientContactAdded();
        return this;
    }

    public projectEditPage addExistingClientContact()
    {
        clickAddContact();
        $(clientContactSelector).shouldBe(editable);
        clientContactSelector.click();
        //Send keys to input
        //select first option method
        return this;
    }

    public projectEditPage verifyNewClientContactAdded()
    {
        $(lastClientCreated).should(exist);
        System.out.println("New Client contact successfully added via Delivery.");
        return this;
    }



    public projectEditPage selectFirstOptionEmberDropdown(String inputVal)
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

}
