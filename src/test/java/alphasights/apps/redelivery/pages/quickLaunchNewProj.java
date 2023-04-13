package alphasights.apps.redelivery.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class quickLaunchNewProj {
    //region Variables
    //endregion

    //region Locators
    public SelenideElement nextStepBtn =  $x("//span[text()='Next Step']");
    public SelenideElement accountSearch = $x("//div[@data-testid ='clientAccountId-select']");
    public SelenideElement accountSearchInput = $("input.sc-tAExr.dNVJYh");
    public SelenideElement accountChoice = $x("//li[text()='AlphaTest Inc. (for testing) 1']");
    public SelenideElement categorySearch = $x("//div[@data-testid = 'category-select']");
    //endregion

    //region Methods
    public quickLaunchNewProj clickNextBtn()
    {
        $(nextStepBtn).shouldBe(enabled);
        nextStepBtn.click();
        return this;
    }

    public quickLaunchNewProj enterAccountName(String accountName)
    {
        $(accountSearch).shouldBe(editable);
        accountSearch.click();
        $(accountSearchInput).shouldBe(editable);
        accountSearchInput.sendKeys(accountName);
        return this;
    }

    public quickLaunchNewProj selectAccountName(String accountName)
    {
        $(accountChoice).shouldBe(editable);
        accountChoice.click();
        return this;
    }

    public quickLaunchNewProj accountSelection(String accountName)
    {
        enterAccountName(accountName);
        selectAccountName(accountName);
        return this;
    }
    //endregion

}
