package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class clientAccountsPage extends pistachioBasePage{
    //region Variable
    public String clientAccountsUrl = "https://qa-pistachio.alphasights.com/client/accounts";
    public String currentKeyword = null;

    private String accountDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/accountDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(accountDetails));
    JSONObject jsonObject = (JSONObject)obj;

    String accountName = (String) jsonObject.get("accountName");
    String supervisors = (String)jsonObject.get("supervisors");
    String entity = (String)jsonObject.get("entity");
    String businessUnit = (String)jsonObject.get("businessUnit");
    String subBusinessUnit = (String)jsonObject.get("subBusinessUnit");
    String office = (String)jsonObject.get("office");
    //endregion

    //region Locators
    //region Main
    public SelenideElement pageHeading = $("h1.heading");
    public SelenideElement newAccount = $("a.button.new");
    public SelenideElement accountsKeywordSearch = $("input#keywords.browser_autocomplete_off.auto_complete.ac_input");
    public SelenideElement submitAccountsKeywordSearch = $(By.cssSelector("button.search.button"));
    public SelenideElement accountPredictiveCompleteOptions = $("div.ac_results");
    public SelenideElement searchListing = $("table.listing.striped");
    public SelenideElement firstResultName = $("table.listing.striped > tbody > tr:nth-child(1) > td:nth-child(1)");
    public SelenideElement lastAccountRow = $(("table.listing.striped > thead > tr:last-child"));

    //endregion

    //region New Account
    public SelenideElement newAccountNameInput = $(By.id("client_account_name"));
    public SelenideElement newAccountOwnerSelector = $(By.id("client_account_owner_id"));
    public SelenideElement newAccountSupervisorSelector = $(By.id("s2id_autogen1"));
    public SelenideElement newAccountEntitySelector = $(By.id("s2id_client_account_entity_id"));
    public SelenideElement newAccountEntityInput = $("input.select2-input");
    public SelenideElement newAccountEntityLabel = $("div.select2-result-label");
    public SelenideElement newAccountTimeZoneSelector = $(By.id("client_account_time_zone"));
    public SelenideElement newAccountCountrySelector = $(By.id("client_account_country_id"));
    public SelenideElement newAccountBusinessUnitSelector = $(By.id("client_account_business_unit_id"));
    public SelenideElement newAccountSubBusinessUnitSelector = $(By.id("client_account_sub_business_unit"));
    public SelenideElement newAccountOffice = $(By.id("client_account_office_id"));
    public SelenideElement saveAccount = $("button.save");
    public SelenideElement discardAccount = $("a.cancel");




    //endregion

    //endregion

    //region Methods
    public clientAccountsPage pageLoaded()
    {
        $(pageHeading).shouldBe(enabled);
        $(lastAccountRow).should(enabled, Duration.ofSeconds(10));
        $(pageHeading).shouldHave(text("Client Accounts"));
        System.out.println("Client Accounts Page is loaded: Page Header - " + pageHeading.getText());
        return this;
    }
    
    public clientAccountsPage clickNewAccount()
    {
        $(newAccount).shouldBe(enabled);
        newAccount.click();
        return this;
    }

    public clientAccountsPage searchAccount(String keywords)
    {
        $(accountsKeywordSearch).shouldBe(enabled);
        accountsKeywordSearch.sendKeys(keywords);
        submitAccountsKeywordSearch.click();
        currentKeyword = keywords;
        return this;
    }

    public clientAccountsPage createAccountBasic()
    {
        newAccountNameInput.sendKeys(accountName);
        newAccountSupervisorSelector.setValue(supervisors).pressEnter();
        newAccountEntitySelector.click();
        newAccountEntityInput.sendKeys(entity);
        newAccountEntityLabel.click();
        newAccountBusinessUnitSelector.selectOption(businessUnit);
        newAccountSubBusinessUnitSelector.selectOption(subBusinessUnit);
        newAccountOffice.selectOption(office);
        saveAccount.click();
        return this;
    }

    public clientAccountsPage verifyIfAccountExistsIfNotCreate()
    {
        if(firstResultName.exists()) {
            if (firstResultName.equals(currentKeyword)) {
                System.out.println("Account already exists in this Environment.");
            }
        }
        else{
            clickNewAccount();
            createAccountBasic();
            System.out.println("Account has been created successfully.");
        }
        return this;
    }
    //endregion

    public clientAccountsPage() throws IOException, ParseException {
    }


}