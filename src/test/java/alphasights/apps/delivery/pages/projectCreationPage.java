package alphasights.apps.delivery.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import com.github.javafaker.*;
import org.openqa.selenium.Keys;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Locale;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;

public class projectCreationPage extends basePage {
    //region Variable
    private String projectDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/projectDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(projectDetails));
    JSONObject jsonObject = (JSONObject)obj;
    public String clientInstructions = (String)jsonObject.get("clientInstructions");
    public String clientEntity = (String)jsonObject.get("clientEntity");
    public String category = (String)jsonObject.get("category");
    public String clientContact = (String)jsonObject.get("clientContact");
    public String account = (String)jsonObject.get("account");
    Faker faker = new Faker();

    String externalTitleVal = faker.rickAndMorty().character() + "Company";
    String excludedCompaniesVal = faker.rickAndMorty().location() + "Company";
    String codeNameVal = faker.rickAndMorty().quote();
    //endregion

    //region Locators
    public SelenideElement projectOverviewForm = $("div.aui-bg-white.aui-fade-in");
    public SelenideElement briefOverviewTab = $(By.id("overview-tab"));
    public SelenideElement projectAnglesTab = $(By.id("angle-tab"));
    public SelenideElement cancelBtn = $x("//button[contains(text() , 'Cancel')]");
    public SelenideElement createProject = $x("//button[text()='Create Project']");
    public SelenideElement addAnchorCompanies = $("i.aui-icon-plus");
    public SelenideElement clientInstructionsTextArea = $x("//textarea[contains (@placeholder, 'Overall project scope. For example, workforce management solutions or')]");
    public SelenideElement internalDeliveryGuidelinesTextArea = $x("//textarea[text()='Internal project notes, for example Update the client every day at 6']");
    public SelenideElement clientEntitySelector = $x("//span[text()='Select Entity']");
    public SelenideElement clientEntityInput = $("input.ember-power-select-search-input");
    public SelenideElement lastClientEntitySelectOption = $("li.ember-power-select-option:last-child");
    public SelenideElement firstClientEntitySelectionOption = $("li.ember-power-select-option:first-child");

    public SelenideElement categorySelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view");
    public SelenideElement categoryInput = $("input.ember-power-select-search-input");
    public SelenideElement categorySelectOption = $("li.ember-power-select-option");
    public SelenideElement clientContactSelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-basic-dropdown-trigger--left.ember-basic-dropdown-trigger--below.ember-view");
    public SelenideElement clientContactInput = $("div.ember-power-select-search");
    public SelenideElement clientContactSelectOption = $("li.ember-power-select-option");
    public SelenideElement clientAccountSelector = $("div.ember-view > span.aui.x-form-field__element--select.aui-border-error-2.ember-view");
    public SelenideElement clientAccountInput = $("div.ember-power-select-search");
    public SelenideElement lastClientAccountSelectOption = $("li.ember-power-select-option:last-child");
    public SelenideElement allNPSoptions = $("project-form-group__row.project-form-group__row--middle");
    public SelenideElement onCompletion = $x("//checkbox[text()='On completion']");
    public SelenideElement atOneWeekInactivity = $x("//checkbox[text()='At one week inactivity']");
    public SelenideElement optOut = $x("//checkbox[text()='Opt-out']");
    public SelenideElement contactMethodNewSpeak = $x("//button[text()='New Speak']");
    public SelenideElement contactMethodConference = $x("//button[text()='Conference']");
    public SelenideElement contactMethodCustom = $x("//button[text()='Custom']");
    public SelenideElement conferenceTypeSelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-basic-dropdown-trigger--left.ember-basic-dropdown-trigger--below.ember-view");
    public SelenideElement externalTitle = $x("//input[@placeholder= 'Will be visible to Advisors and Clients']");
    public SelenideElement lastExternalDescriptor = $("div.project-form-group__checkbox.project-form-group__checkbox--perspectives:last-child");
    public SelenideElement excludedCompanies = $x("//input[@placeholder= 'e.g. Shell, Statoil']");
    public SelenideElement codeNameInput = $x("//div[@class= 'x-form-field x-form-field--input x-form-field--codename x-form-field--required ember-view']//label[text()='Codename']/following-sibling::input");

    public projectCreationPage() throws IOException, ParseException {
    }
    //public SelenideElement excludedCompanies = $("input.aui-rounded.aui-p-3.focus:aui-outline-none.aui-shadow-inner.aui-appearance-none.aui-text-dark-1.aui-bg-grey-1.focus:aui-bg-white.aui-border.focus:aui-border-error-1.aui-border-grey-2.aui-border-solid.aui-w-full.ember-view");
    //public SelenideElement internalCodename = $("input.aui-rounded.aui-p-3.focus:aui-outline-none.aui-shadow-inner.aui-appearance-none.aui-text-dark-1.aui-bg-grey-1.focus:aui-bg-white.aui-border.focus:aui-border-error-1.aui-border-grey-2.aui-border-solid.aui-w-full.ember-view");
    //public SelenideElement internalState = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view");
    //endregion

    //region Methods
    public projectCreationPage clickBriefOverviewTab()
    {
        $(briefOverviewTab).shouldBe(editable);
        briefOverviewTab.click();
        return this;
    }

    public projectCreationPage clickProjectAngles()
    {
        $(projectAnglesTab).shouldBe(editable);
        projectAnglesTab.click();
        return this;
    }

    public projectCreationPage clickCancelButton()
    {
        $(cancelBtn).shouldBe(editable);
        cancelBtn.click();
        return this;
    }

    public projectCreationPage clickCreateProjects()
    {
        $(createProject).shouldBe(editable);
        createProject.click();
        return this;
    }

    public projectCreationPage createProject(NPSOptions NPSOption)
    {
        $(projectOverviewForm).should(editable, Duration.ofSeconds(10));
        projectOverviewForm.click();
        enterClientInstructions();
        enterClientEntity();
        enterCategory();
        enterClientContact();
        enterAccount();
        selectNPS(NPSOption);
        enterExternalTitle();
        enterExcludedCompanies();
        enterCodeName();
        return this;
    }

    public projectCreationPage enterClientInstructions()
    {
        $(clientInstructionsTextArea).shouldBe(editable);
        clientInstructionsTextArea.sendKeys(clientInstructions);
        return this;
    }

    public projectCreationPage enterClientEntity()
    {
        $(clientEntitySelector).shouldBe(editable);
        clientEntitySelector.click();
        $(clientEntityInput).shouldBe(editable);
        clientEntityInput.click();
        clientEntityInput.sendKeys(clientEntity + ENTER);
        return this;
    }

    public projectCreationPage enterCategory()
    {
        $(categorySelector).shouldBe(editable);
        categorySelector.click();
        $(categoryInput).shouldBe(editable);
        categoryInput.sendKeys(category);
        $(categorySelectOption).shouldBe(editable);
        categorySelectOption.click();
        return this;
    }

    public projectCreationPage enterClientContact()
    {
        $(clientContactSelector).shouldBe(editable);
        clientContactSelector.click();
        $(clientContactInput).shouldBe(editable);
        clientContactInput.sendKeys(clientContact);
        $(clientContactSelectOption).shouldBe(editable);
        ElementsCollection clientContactOption = $$("li.ember-power-select-option");
        clientContactOption.first().click();
        return this;
    }

    public projectCreationPage enterAccount()
    {
        $(clientAccountSelector).shouldBe(editable);
        clientAccountSelector.click();
        $(clientAccountInput).shouldBe(editable);
        clientAccountInput.sendKeys(account);
        $(lastClientAccountSelectOption).shouldBe(editable);

        return this;
    }


    public projectCreationPage selectNPS(NPSOptions npsOption)
    {
        $(allNPSoptions).shouldBe(editable);
        switch(npsOption)
        {
            case ON_COMPLETION:
                $(onCompletion).shouldBe(editable);
                onCompletion.click();
                break;
            case AT_ONE_WEEK_INACTIVITY:
                $(atOneWeekInactivity).shouldBe(editable);
                atOneWeekInactivity.click();
                break;
            case OPT_OUT:
                $(optOut).shouldBe(editable);
                optOut.click();
                break;
            default:
                System.out.println("Invalid NPS Option entered.");
        }
        return this;
    }

    //External Title, Excluded Comps, and Codename autogenerated

    public projectCreationPage enterExternalTitle()
    {
        $(externalTitle).shouldBe(editable);
        externalTitle.sendKeys(externalTitleVal);
        return this;
    }

    public projectCreationPage enterExcludedCompanies()
    {
        $(excludedCompanies).shouldBe(editable);
        excludedCompanies.sendKeys(excludedCompaniesVal);
        return this;
    }

    public projectCreationPage enterCodeName()
    {
        $(codeNameInput).shouldBe(editable);
        codeNameInput.sendKeys(codeNameVal);
        return this;
    }



    //endregion

}