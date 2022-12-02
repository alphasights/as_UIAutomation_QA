package alphasights.apps.delivery.pages;


import alphasights.apps.pistachio.pages.pistachioBasePage;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class dashboardPage extends pistachioBasePage {
    //region Variable
    //endregion

    //region Locators
    public SelenideElement newProjectButton = $x("//div[@class='dashboard__header-content']//a[contains(text(),'New Project')]");
    //endregion

    //region Methods
    public dashboardPage pageLoad()
    {
        $("div.interactions-to-schedule > div.content").shouldBe(editable);
        return this;
    }

    public dashboardPage clickNewProject()
    {
        $(newProjectButton).should(enabled, Duration.ofSeconds(10));
        newProjectButton.click();
        return this;
    }
    //endregion


}