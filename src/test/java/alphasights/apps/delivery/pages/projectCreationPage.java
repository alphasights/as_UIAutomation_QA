package alphasights.apps.delivery.pages;

import com.codeborne.selenide.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import com.github.javafaker.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static alphasights.apps.delivery.pages.ExternalDescriptions.COMMERCIAL;
import static com.codeborne.selenide.Condition.*;
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
    public String clientCategory = (String)jsonObject.get("clientCategory");
    public String clientContact = (String)jsonObject.get("clientContact");
    public String clientAccount = (String)jsonObject.get("clientAccount");
    Faker faker = new Faker();

    String externalTitleVal = faker.rickAndMorty().character() + " Company";
    String excludedCompaniesVal = faker.rickAndMorty().location() + " Company";
    String codeNameVal = externalTitleVal + " " + excludedCompaniesVal + " Test";
    //endregion

    //region Locators
    //Project Creation Nav and Subnav
    public SelenideElement briefOverviewTab = $(By.id("overview-tab"));
    public SelenideElement projectAnglesTab = $(By.id("angle-tab"));
    public SelenideElement cancelBtn = $x("//button[contains(text() , 'Cancel')]");
    public SelenideElement createProject = $x("//button[text()='Create Project']");
    public SelenideElement addAnchorCompanies = $("div:first-child > div > div > i.aui-icon.aui-icon-plus.aui-text-white.aui-font-hairline.aui-text-modal-header");
    public SelenideElement addInvestmentTargets = $("div:last-child > div > div > i.aui-icon.aui-icon-plus.aui-text-white.aui-font-hairline.aui-text-modal-header");
    //endregion

    //region Brief Overview Form
    public SelenideElement projectOverviewForm = $("div.aui-bg-white.aui-fade-in");

    public SelenideElement alphaCompaniesSearchSidebar = $("div.as-sidebar");
    public SelenideElement alphaCompaniesClosingIcon = $("i.aui-icon-close");
    public SelenideElement projectMatchesDetectedModal = $("div.aui-modal-content.aui-shadow-huge.aui-bg-white.aui-rounded.aui-mx-auto.as-deal-confirmation-modal");
    public SelenideElement projectMatchesModalClose = $("div.aui-inline-block > i.aui-icon-close");
    public SelenideElement alphaCompaniesSearch = $x("//textarea[@placeholder= 'Enter companies…']");
    public SelenideElement alphaCompanyAddedConfirmationMsg = $("div.aui-fade-in.aui-font-medium");
    public SelenideElement alphaCompaniesSearchIcon = $("i.company-search-icon");
    public SelenideElement alphaCompaniesNextStep = $("button.aui-border.aui-bg-grey-5.aui-border-grey-5.aui-border-solid.aui-text-white.aui-cursor-pointer.aui-py-3.aui-px-4.aui-rounded.aui-text-base.aui-outline-none.aui-font-semibold.aui-text-sub-text.aui-px-6.aui-mt-16");
    public SelenideElement alphaCompaniesAddUnknown = $("div > button.aui-border.aui-bg-transparent.aui-border-transparent.aui-text-dark-1.aui-cursor-pointer.aui-py-3.aui-px-4.aui-outline-none.aui-font-semibold");
    public SelenideElement clientInstructionsTextArea = $x("//textarea[contains (@placeholder, 'Overall project scope. For example, workforce management solutions or')]");
    public SelenideElement internalDeliveryGuidelinesTextArea = $x("//textarea[text()='Internal project notes, for example Update the client every day at 6']");
    public SelenideElement clientEntitySelector = $x("//span[text()='Select Entity']");
    public ElementsCollection emberInput = $$("input.ember-power-select-search-input");
    public SelenideElement emberDropdown = $("div.ember-basic-dropdown-content.ember-power-select-dropdown.ember-basic-dropdown-content--in-place.ember-basic-dropdown-content--left.ember-basic-dropdown-content--below.ember-basic-dropdown--transitioned-in.ember-view");
    public SelenideElement lastClientEntitySelectOption = $("li.ember-power-select-option:last-child");
    public SelenideElement firstEmberSelectionOption = $("li.ember-power-select-option:first-child");
    public SelenideElement selectedClientEntity = $("div.x-form-field.x-form-field--select.x-form-field--client-entity.x-form-field--required.ember-view > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view  > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view > span.ember-power-select-selected-item");

    public SelenideElement clientCategorySelector = $("div.project-form-group__column.x-form-field.x-form-field--select.x-form-field--category.x-form-field--required.ember-view > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view");
    public SelenideElement selectedClientCategory = $("div.project-form-group__column.x-form-field.x-form-field--select.x-form-field--category.x-form-field--required.ember-view > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view > span.ember-power-select-selected-item");
    public SelenideElement clientContactSelector = $x("//span[text()='Select Client Contact']");
    public SelenideElement selectedClientContact = $("div.ember-view.tooltipstered > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view > span.ember-power-select-selected-item");
    public SelenideElement clientAccountSelector = $("div.x-form-field.x-form-field--select.x-form-field--client-account.x-form-field--required.ember-view > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view");
    public SelenideElement selectedClientAccount = $("div.x-form-field.x-form-field--select.x-form-field--client-account.x-form-field--required.ember-view > div.ember-view > span.aui.x-form-field__element--select.ember-view > div.ember-basic-dropdown.ember-view > div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view > span.ember-power-select-selected-item");
    public SelenideElement clientAccountInput = $("div.ember-power-select-search");
    public SelenideElement lastClientAccountSelectOption = $("li.ember-power-select-option:last-child");
    public SelenideElement allNPSoptions = $("div.project-form-group__row.project-form-group__row--middle");
    public SelenideElement onCompletion = $("div.x-form-field--send-nps-on-completion > label > input");
    public SelenideElement atOneWeekInactivity = $("div.x-form-field--send-nps-on-inactivity > label > input");
    public SelenideElement optOut = $("div.x-form-field--nps-opt-out > label > input");
    public SelenideElement contactMethodNewSpeak = $x("//button[text()='New Speak']");
    public SelenideElement contactMethodConference = $x("//button[text()='Conference']");
    public SelenideElement contactMethodCustom = $x("//button[text()='Custom']");
    public SelenideElement conferenceTypeSelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-basic-dropdown-trigger--left.ember-basic-dropdown-trigger--below.ember-view");
    public SelenideElement externalTitle = $x("//input[@placeholder= 'Will be visible to Advisors and Clients']");
    public SelenideElement externalDescriptions = $("div.x-form-field--description-perspectives");
    public SelenideElement extDescCommercial = $x("//label [text()= 'Commercial' ]");
    public SelenideElement lastExternalDescriptor = $("div.project-form-group__checkbox.project-form-group__checkbox--perspectives:last-child");
    public SelenideElement excludedCompanies = $x("//input[@placeholder= 'e.g. Shell, Statoil']");
    public SelenideElement codeNameInput = $x("//div[@class= 'x-form-field x-form-field--input x-form-field--codename x-form-field--required ember-view']//label[text()='Codename']/following-sibling::input");
    //endregion

    //region Project Angles Form
    public SelenideElement addNewAngle = $x("//div[text() = 'Add New Angle']");
    public SelenideElement seeSummary = $x("//button[contains(text(), 'See Summary')]");
    public SelenideElement angleTypeInput = $x("//span[text() = 'Select Angle Type']");
    public SelenideElement angleTypeSelector = $("input.ember-power-select-search-input");
    public SelenideElement angleNameInput = $x("//input[@placeholder= 'New Angle']");
    public SelenideElement assignTeamMembers = $x("//input[@placeholder= 'Search for Team Members']");
    projectDetailsPage ProjectDetailsPage = new projectDetailsPage();

    //endregion
    //region
    //endregion

    public projectCreationPage() throws IOException, ParseException {
    }
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

    public projectCreationPage clickAnchorCompanies()
    {
        $(addAnchorCompanies).shouldBe(editable);
        addAnchorCompanies.click();
        return this;
    }

    public projectCreationPage clickInvestmentTargets()
    {
        $(addInvestmentTargets).shouldBe(editable);
        addInvestmentTargets.click();

        return this;
    }

    public projectCreationPage enterAlphaCompany(String company)
    {
        $(alphaCompaniesSearchSidebar).shouldBe(editable);
        alphaCompaniesSearch.sendKeys(company + ENTER);
        $(alphaCompanyAddedConfirmationMsg).shouldHave(partialText(company + " was added"));
        $(alphaCompaniesClosingIcon).shouldBe(enabled);
        alphaCompaniesClosingIcon.click();
        $(projectMatchesDetectedModal).shouldBe(visible);
        projectMatchesModalClose.click();
        return this;
    }

    public projectCreationPage enterFirstAngle(String type, String name)
    {
        $(angleTypeInput).shouldBe(editable);
        angleTypeInput.click();
        angleTypeSelector.sendKeys(type);
        selectFirstOptionEmberDropdown(type);
        $(angleNameInput).shouldBe(editable);
        angleNameInput.sendKeys(name);
        return this;
    }

    public projectCreationPage focusOnProjectOverviewForm()
    {
        $(projectOverviewForm).should(editable, Duration.ofSeconds(10));
        projectOverviewForm.click();
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
        selectFirstOptionEmberDropdown(clientEntity);
        $(selectedClientEntity).shouldHave(partialText(clientEntity));
        return this;
    }

    public projectCreationPage enterClientCategory()
    {
        $(clientCategorySelector).shouldBe(editable);
        clientCategorySelector.click();
        selectFirstOptionEmberDropdown(clientCategory);
        $(selectedClientCategory).shouldHave(partialText(clientCategory));
        return this;
    }

    public projectCreationPage enterClientContact()
    {
        $(clientContactSelector).shouldBe(editable);
        clientContactSelector.click();
        selectFirstOptionEmberDropdown(clientContact);
        $(selectedClientContact).shouldHave(partialText(clientContact));
        return this;
    }

    public projectCreationPage enterAccount()
    {
        //Client Account is pre-populated, but we are editing it
        $(clientAccountSelector).shouldBe(editable);
        clientAccountSelector.click();
        selectFirstOptionEmberDropdown(clientAccount);
        $(selectedClientAccount).shouldHave(partialText(clientAccount));

        return this;
    }


    public projectCreationPage selectNPS(NPSOptions npsOption)
    {
        $(allNPSoptions).shouldBe(editable);
        actions().moveToElement(allNPSoptions);
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

    public projectCreationPage selectExternalDescription(ExternalDescriptions extDesc)
    {
        $(externalDescriptions).shouldBe(editable);
        actions().moveToElement(externalDescriptions);
        switch(extDesc)
        {
            case COMMERCIAL:
                $(extDescCommercial).shouldBe(editable);
                extDescCommercial.click();
                break;
            default:
                System.out.println("Invalid External Description entered.");


        }

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
    
    public projectCreationPage selectFirstOptionEmberDropdown(String inputVal)
    {
        $(emberInput.last()).shouldBe(editable);
        emberInput.get(0).click();
        emberInput.get(0).clear();
        emberInput.get(0).sendKeys(inputVal);
        $(emberDropdown).should(editable, Duration.ofSeconds(5));
        if (inputVal == clientEntity) {
            $(firstEmberSelectionOption).shouldNotBe(partialText("Searching for Entities..."), Duration.ofSeconds(5));
        }
        if (inputVal == clientContact) {
            $(firstEmberSelectionOption).shouldNotBe(partialText("Searching for contacts..."), Duration.ofSeconds(5));
        }
        firstEmberSelectionOption.click();
        return this;
    }

    public projectCreationPage pressTab(int numberOfTimes)
    {
        int i = 0;

        while(i > numberOfTimes)
        {
            actions().sendKeys(TAB);
            i++;
        }
        return this;
    }

    public projectCreationPage createProject(NPSOptions NPSOption, String Company, String angleType, String angleName)
    {
        clickAnchorCompanies();
        enterAlphaCompany(Company);
        focusOnProjectOverviewForm();
        enterClientInstructions();
        enterClientEntity();
        enterClientCategory();
        enterClientContact();
        enterAccount();
        selectNPS(NPSOption);
        enterExternalTitle();
        selectExternalDescription(COMMERCIAL);
        enterExcludedCompanies();
        enterCodeName();
        clickProjectAngles();
        enterFirstAngle(angleType, angleName);
        return this;
    }

    public projectCreationPage verifyProjectCreated()
    {
        $(ProjectDetailsPage.projectExternalTitle).shouldHave(text(externalTitleVal));
        return this;
    }


    //endregion

}