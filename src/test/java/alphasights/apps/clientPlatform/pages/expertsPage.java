package alphasights.apps.clientPlatform.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.COMMAND;
import static org.openqa.selenium.Keys.DELETE;

public class expertsPage extends clientPlatformBasePage{

    //region Variables
    //#endregion

    //region Locators
    public SelenideElement expertSearch = $x("//input[@placeholder='Search']");
    public ElementsCollection mainFilterButtons = $$("div.sc-afnQL.jzvvXA > button.sc-iJkHyd.cLeFme");
        public SelenideElement statusFilterButton = mainFilterButtons.get(0);
            public ElementsCollection statusOptions = $$("span.sc-kngDgl.sc-itUGML.lnVWFR.pdotP");
                public SelenideElement statusAvailable = statusOptions.get(0);
                public SelenideElement statusRequested = statusOptions.get(1);
                public SelenideElement statusScheduled = statusOptions.get(2);
                public SelenideElement statusCompleted = statusOptions.get(3);
            public SelenideElement statusClear = $x("//span[text()='Clear']");
            public SelenideElement statusSave = $("button.sc-iJkHyd.kxmOmO");
        public SelenideElement expertAngleButton = mainFilterButtons.get(1);
        public SelenideElement recencyButton = mainFilterButtons.get(2);
        public SelenideElement companyButton = mainFilterButtons.get(3);
        public SelenideElement dateAddedButton = mainFilterButtons.get(4);
        public SelenideElement commentsButton = mainFilterButtons.get(5);
        public SelenideElement moreFiltersButton = mainFilterButtons.get(6);
    public ElementsCollection starExpert = $$("div.aui-space-x-4.aui-inline-flex.aui-items-center >button.sc-iJkHyd.fPkpsD > span.sc-kngDgl.bIqfrF > svg");
    public ElementsCollection hideExpert = $$("div.aui-space-x-4.aui-inline-flex.aui-items-center > div > button.sc-iJkHyd.fPkpsD > span.sc-kngDgl.bIqfrF > svg");
    public ElementsCollection expertNames = $$("span.sc-kngDgl.dskGzw.aui-text-primary-1");

    //endregion

    //region Methods
    public expertsPage searchExperts(String query)
    {
        $(expertSearch).shouldBe(editable);
        expertSearch.sendKeys(query + Keys.ENTER);
        return this;
    }

    public expertsPage clearExpertsSearch()
    {
        $(expertSearch).shouldBe(editable);
        if(!expertSearch.getValue().isEmpty()) {
            expertSearch.sendKeys(COMMAND + "A");
            expertSearch.sendKeys(DELETE);
            System.out.println("Expert search has been cleared out.");
        }
        else
        {
            System.out.println("Expert search was already blank.");
        }
        return this;
    }
    public expertsPage clickStatusButton()
    {
        $(statusFilterButton).is(enabled);
        statusFilterButton.click();
        return this;
    }

    public expertsPage selectStatusOption(String statusFilter)
    {
        switch (statusFilter)
        {
            case "available":
                $(statusAvailable).shouldBe(interactable);
                statusAvailable.click();
                break;
            case "requested":
                $(statusRequested).shouldBe(interactable);
                statusRequested.click();
                break;
            case "scheduled":
                $(statusScheduled).shouldBe(interactable);
                statusScheduled.click();
                break;
            case "completed":
                $(statusCompleted).shouldBe(interactable);
                statusCompleted.click();
                break;
            case "clear":
                $(statusClear).shouldBe(interactable);
                statusClear.click();
                break;
            case "save":
                $(statusSave).shouldBe(interactable);
                statusSave.click();
                break;
        }
        return this;
    }

    public expertsPage clickExpertName(int expertNum)
    {
        SelenideElement desiredExpertname = expertNames.get(expertNum);
        $(desiredExpertname).shouldBe(enabled);
        desiredExpertname.click();
        return this;
    }

    public expertsPage refreshBrowser()
    {
        Selenide.refresh();
        $("body").shouldBe(visible);
        Selenide.switchTo().activeElement();
        return this;
    }



    //endregion


}
