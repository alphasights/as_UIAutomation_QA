package alphasights.apps.delivery.pages.whiteboardAndProjs;

import alphasights.apps.delivery.pages.deliveryBasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class projectResearchPage  extends deliveryBasePage {
    //region Variable
    //endregion

    //region Locators
    public SelenideElement projectName = $("a.project-research-summary__project-name");
    //endregion

    //region Method
    public projectResearchPage clickProjectName()
    {
        $(projectName).shouldBe(enabled);
        projectName.click();
        return this;
    }
    //endregion
}
