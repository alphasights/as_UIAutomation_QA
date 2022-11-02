package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class clientContactsPage extends basePage{
    //region Variables
    public String clientContactsUrl = "https://qa-pistachio.alphasights.com/client/contacts";
    public String currentKeyword = null;

    private String contactDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/contactDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(contactDetails));
    JSONObject jsonObject = (JSONObject)obj;

    String contactName = (String)jsonObject.get("contactName");
    String primaryEmail = (String)jsonObject.get("primaryEmail");
    String accountName = (String)jsonObject.get("AccountName");
    String supervisors = (String)jsonObject.get("supervisors");
    String role = (String)jsonObject.get("role");

    //endregion


    //region Locators
    //region Main
    public SelenideElement pageHeading = $("h1.heading");
    public SelenideElement newContact = $("a.button.new.new_client_contact");
    public SelenideElement contactsKeywordSearch = $("input#keywords.browser_autocomplete_off");
    public SelenideElement submitContactKeywordSearch = $(By.cssSelector("button.search.button"));
    public SelenideElement firstResultName = $("table.listing.striped > tbody > tr:nth-child(1) > td:nth-child(1)");
    public SelenideElement lastClientContactRow = $(("table.listing.striped > thead > tr:last-child"));
    //endregion

    //region New Client Contact
    public SelenideElement newContactNameInput = $(By.id("client_contact_name"));
    public SelenideElement newContactPrimaryEmailInput = $(By.id("client_contact_email"));
    public SelenideElement newContactDetailsInput = $(By.id("client_contact_new_contact_details"));
    public SelenideElement newContactAccountSelector = $(By.id("select2-chosen"));
    public SelenideElement newContactAccountInput = $(By.id("div#select2-drop > div.select2-search > input.select2-input"));
    public SelenideElement newContactAccountLabel = $("li:first-child > div.select2-result-label");
    public SelenideElement newContactOwnerSelector = $(By.id("client_contact_owner_id"));
    public SelenideElement newContactSupervisorSelector = $(By.id("s2id_client_contact_supervisor_ids"));
    public SelenideElement newContactRoleSelector = $(By.id("client_contact_role"));
    public SelenideElement newContactStateSelector = $(By.id("client_contact_state"));
    public SelenideElement newContactReferredBySelector = $(By.id("s2id_client_contact_referrer_id"));
    public SelenideElement newContactTrackOnPulseCheckbox = $(By.id("client_contact_track"));
    public SelenideElement newContactTimeZoneSelector = $(By.id("client_contact_time_zone"));
    public SelenideElement newContactAddressInput = $(By.id("client_contact_correspondence_address"));
    public SelenideElement newContactCountrySelector = $(By.id("client_contact_country_id"));
    public SelenideElement saveContact = $(("button.button.save"));

    //endregion
    //endregion

    //region Methods
    public clientContactsPage pageLoaded()
    {
        $(pageHeading).shouldBe(enabled);
        $(lastClientContactRow).should(enabled, Duration.ofSeconds(10));
        $(pageHeading).shouldHave(text("Clients"));
        System.out.println("Client Contacts Page is loaded: Page Header - " + pageHeading.getText());
        return this;
    }

    public clientContactsPage clickNewContact()
    {
        $(newContact).shouldBe(editable);
        newContact.click();
        return this;
    }

    public clientContactsPage searchContact(String keywords)
    {
        $(contactsKeywordSearch).shouldBe(editable);
        contactsKeywordSearch.sendKeys(keywords);
        submitContactKeywordSearch.click();
        currentKeyword = keywords;
        return this;
    }

    public clientContactsPage createContactBasic()
    {
        newContactNameInput.sendKeys(contactName);
        newContactPrimaryEmailInput.sendKeys(primaryEmail);
        newContactAccountSelector.click();
        newContactAccountInput.sendKeys(accountName);
        newContactAccountLabel.click();
        newContactSupervisorSelector.setValue(supervisors).pressEnter();
        newContactRoleSelector.selectOption(role);
        saveContact.click();
        return this;
    }

    public clientContactsPage verifyIfContactExistsIfNotCreate()
    {
        if(firstResultName.exists()) {
            if (firstResultName.equals(currentKeyword)) {
                System.out.println("Contact already exists in this Environment.");
            }
        }
        else{
            clickNewContact();
            createContactBasic();
            System.out.println("Contact has been created successfully.");
        }
        return this;
    }
    //endregion
    public clientContactsPage() throws IOException, ParseException {
    }



}