package alphasights.apps.delivery.pages;


import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class dashboardPage extends deliveryBasePage {
    //region Variable
    //endregion

    //region Locators
    public SelenideElement newProjectBtn = $("a.dashboard__header-button.dashboard__header-button--new-project.ember-view");
    //endregion

    //region Methods
    public dashboardPage pageLoad()
    {
        $("div.interactions-to-schedule > div.content").shouldBe(editable);
        return this;
    }

    public dashboardPage clickNewProjectBtn()
    {
        $(newProjectBtn).should(enabled, Duration.ofSeconds(10));
        newProjectBtn.click();
        return this;
    }
    //endregion


}