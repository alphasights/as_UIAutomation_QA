package alphasights.apps.delivery.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Collection;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class projectCreationPage extends basePage {
    //region Locators
    public SelenideElement briefOverviewTab = $(By.id("overview-tab"));
    public SelenideElement projectAnglesTab = $(By.id("angle-tab"));
    public SelenideElement cancelBtn = $x("//button[contains(text() , 'Cancel')]");
    public SelenideElement createProject = $x("//button[text()='Create Project']");
    public SelenideElement addAnchorCompanies = $("i.aui-icon-plus");
    public SelenideElement clientInstructionsTextArea = $(By.id("xform-textarea-0.11924718445708882"));
    public SelenideElement internalDeliveryGuidelines = $(By.id("xform-textarea-0.1684637397750257"));
    public SelenideElement clientEntitySelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-basic-dropdown-trigger--left");
    public SelenideElement clientEntityInput = $("input.ember-power-select-search-input");
    public SelenideElement categorySelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-view");
    public SelenideElement categoryInput = $("input.ember-power-select-search-input");
    public SelenideElement categorySelectionOption = $("li.ember-power-select-option");
    public SelenideElement contactMethodNewSpeak = $x("//button[text()='New Speak']");
    public SelenideElement contactMethodConference = $x("//button[text()='Conference']");
    public SelenideElement contactMethodCustom = $x("//button[text()='Custom']");
    public SelenideElement conferenceTypeSelector = $("div.ember-power-select-trigger.x-form-field__element.ember-basic-dropdown-trigger.ember-basic-dropdown-trigger--in-place.ember-basic-dropdown-trigger--left.ember-basic-dropdown-trigger--below.ember-view");
    public SelenideElement externalTitle = $x("//input[@placeholder= 'Will be visible to Advisors and Clients']");
    public SelenideElement lastExternalDescriptor = $("div.project-form-group__checkbox.project-form-group__checkbox--perspectives:last-child");
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

    public projectCreationPage enterClientInstructions(String clientInstructions)
    {
        $(clientInstructionsTextArea).shouldBe(editable);
        clientInstructionsTextArea.sendKeys(clientInstructions);
        return this;
    }

    public projectCreationPage enterClientEntity(String clientEntity)
    {
        $(clientEntitySelector).shouldBe(editable);
        clientEntitySelector.click();
        clientEntityInput.sendKeys(clientEntity);
        ElementsCollection clientEntityOption = $$("input.ember-power-select-option");
        clientEntityOption.get(1).click();
        return this;
    }

    public projectCreationPage enterExternalTitle(String ExternalTitle)
    {
        $(externalTitle).shouldBe(editable);
        externalTitle.sendKeys(ExternalTitle);
        return this;
    }

    public projectCreationPage selectExternalDescriptor(String descriptor)
    {
        $(lastExternalDescriptor).shouldBe(editable);
        SelenideElement chossenDescriptor = $x("//label[text()=['" + descriptor + "']");
        chossenDescriptor.click();
        return this;
    }

    public projectCreationPage enterExcludedCompanies(String ExcludedCompanies)
    {
        //$(excludedCompanies).shouldBe(editable);
        //excludedCompanies.sendKeys(ExcludedCompanies);
        return this;
    }

    //endregion

}