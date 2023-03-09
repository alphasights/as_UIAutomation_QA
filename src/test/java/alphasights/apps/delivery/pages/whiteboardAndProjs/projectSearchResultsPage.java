package alphasights.apps.delivery.pages.whiteboardAndProjs;

import alphasights.apps.delivery.pages.deliveryBasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.$;

public class projectSearchResultsPage extends deliveryBasePage {
    //region Variable
    //endregion

    //region Locator
    public SelenideElement firstProjectSearchResult = $("ul.project-search-compact-results.ember-view > div.ember-view:nth-child(2)");
    //endregion

    //region Methods
    public projectSearchResultsPage clickFirstProjectResult()
    {
        $(firstProjectSearchResult).shouldBe(editable);
        firstProjectSearchResult.click();
        return this;
    }
    //endregion
}
