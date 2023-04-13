package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class clientContactsPage extends pistachioBasePage{
    //region Variables
    public String clientContactsUrl = "https://qa-pistachio.alphasights.com/client/contacts";
    public String currentKeyword = null;

    private String contactDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/contactDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(contactDetails));
    JSONObject jsonObject = (JSONObject)obj;

    String contactName = (String)jsonObject.get("contactName");
    String primaryEmail = (String)jsonObject.get("primaryEmail");
    String accountName = (String)jsonObject.get("accountName");
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
    public SelenideElement newContactAccountSelector = $x("//span[contains(text(),'Select Account')]");
    public SelenideElement searchboxInput = $("#select2-drop > div > input");
    public SelenideElement searchboxDropdown = $("#select2-drop");
    public SelenideElement firstSearchResult = $("div.select2-result-label");
    public SelenideElement newContactAccountInput = $("input.select2-input.select2-focused");
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
    public SelenideElement editContact = $("a.button.edit.small");
    public SelenideElement projectsListing = $(("section#projects"));
    public SelenideElement firstProject = $(("ul#projects > li.clear.clearfix.high_state.project:nth-child(2) > div.name > a"));
    public SelenideElement sendInvite = $x(("//a[contains(text(), 'Send invite')]"));
    public SelenideElement sendPortalInviteModal = $("#client-contacts_controller > div.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-dialog-buttons");
    public SelenideElement sendPortalInviteText = $x("//div[text()='This will send the Client an email invite to the Portal. Continue?']");
    public SelenideElement yesSendInvite = $x("//button[text()='Yes, Send Invite']");
    public SelenideElement noCancel = $x("//button[text()= 'No, Cancel']");
    public SelenideElement closeSendPortalInviteModal = $("#client-contacts_controller > div.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-dialog-buttons > div.ui-dialog-titlebar.ui-corner-all.ui-widget-header.ui-helper-clearfix > button");
    public SelenideElement portalInviteConfirmation = $("div.span.success");
    public SelenideElement deleteButton = $x("//a[text()='Delete']");
    public SelenideElement clientDeletionConfirmation = $("div.message_flash.success");
    public SelenideElement nextButton = $("div#content > nav.pagination > span.next > a");
    //endregion
    //endregion

    //region Methods
    public clientContactsPage pageLoaded()
    {
        $(pageHeading).shouldBe(enabled);
        $(lastClientContactRow).should(enabled);
        $(pageHeading).shouldHave(exactText("Clients"));
        System.out.println("Client Contacts Page is loaded: Page Header - " + pageHeading.getText());
        return this;
    }

    public clientContactsPage pageLoadedAfterSearch()
    {
        $(pageHeading).shouldBe(enabled);
        $(lastClientContactRow).should(enabled);
        $(pageHeading).shouldHave(exactText("Clients"));
        $(nextButton).shouldNot(exist);
        System.out.println("Client Contacts Page search results are loaded: Page Header - " + pageHeading.getText());
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

    public clientContactsPage selectSearchedContact(String keywords)
    {
        SelenideElement searchedClientContact = $(By.linkText(keywords));
        searchedClientContact.click();
        return this;
    }

    public clientContactsPage createContactBasic()
    {
        $(newContactNameInput).shouldBe(editable);
        newContactNameInput.sendKeys(contactName);
        $(newContactPrimaryEmailInput).shouldBe(editable);
        newContactPrimaryEmailInput.sendKeys(primaryEmail);
        newContactAccountSelector.click();
        selectFirstOption();
        saveContact.click();
        return this;
    }

    public clientContactsPage selectFirstOption()
    {
        $(searchboxInput).shouldBe(editable);
        searchboxInput.click();
        searchboxInput.clear();
        searchboxInput.sendKeys(accountName);
        $(searchboxDropdown).shouldBe(editable);
        firstSearchResult.click();
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

    public clientContactsPage clickEditContact()
    {
        $(editContact).is(enabled);
        editContact.click();
        return this;
    }

    public clientContactsPage clickFirstProjFromClientContact()
    {
        firstProject.should(exist);
        firstProject.scrollIntoView(true);
        firstProject.click();
        return this;
    }


    public clientContactsPage clickSendInvite()
    {
        $(sendInvite).shouldBe(enabled);
        sendInvite.click();
        return this;
    }

    public clientContactsPage verifySendInviteModalDisplays()
    {
        $(sendPortalInviteModal).shouldBe(enabled);
        $(sendPortalInviteText).should(exist);
        System.out.println("Send Portal Invite modal displays successfully.");
        return this;
    }

    public clientContactsPage clickYesSendInvite()
    {
        $(yesSendInvite).shouldBe(editable);
        yesSendInvite.click();
        System.out.println("Send Portal Invite accepted.");
        return this;
    }
    
    public clientContactsPage clickNoCancelInvite()
    {
        $(noCancel).shouldBe(editable);
        noCancel.click();
        return this;
    }

    public clientContactsPage clickCloseSendInviteModal()
    {
        $(closeSendPortalInviteModal).shouldBe(editable);
        closeSendPortalInviteModal.click();
        return this;
    }

    public clientContactsPage verifyPortalInviteConfirmation()
    {
        $(portalInviteConfirmation).shouldBe(editable);
        return this;
    }

    public clientContactsPage clickDeleteContact()
    {
        $(deleteButton).shouldBe(editable);
        deleteButton.click();
        return this;
    }

    public clientContactsPage confirmDeleteContact(){
        Selenide.switchTo().alert().accept();
        return this;
    }

    public clientContactsPage verifyClientDeletionMessage()
    {
        $(clientDeletionConfirmation).shouldBe(visible);
        return this;
    }


    //endregion
    public clientContactsPage() throws IOException, ParseException {
    }



}