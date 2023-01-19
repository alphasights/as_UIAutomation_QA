package alphasights.apps.redelivery.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class quickLaunchNewProj {
    //region Variables
    //endregion

    //region Locators
    public SelenideElement nextStepBtn =  $x("//span[text()='Next Step']");
    //endregion

    //region Methods
    public quickLaunchNewProj clickNextBtn()
    {
        $(nextStepBtn).shouldBe(enabled, Duration.ofSeconds(30));
        nextStepBtn.click();
        return this;
    }
    //endregion

}
