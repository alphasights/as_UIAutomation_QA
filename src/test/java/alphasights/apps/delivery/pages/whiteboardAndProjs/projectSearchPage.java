package alphasights.apps.delivery.pages.whiteboardAndProjs;

import alphasights.apps.delivery.pages.deliveryBasePage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class projectSearchPage extends deliveryBasePage {
    //region Variable
    //endregion

    //region Locator
    public SelenideElement projectSearch = $("div.project-search");
    public SelenideElement projectSearchInput = $("input.project-search-advance-search-box__input.project-search-bar__keyword-input");
    public SelenideElement searchProjectBtn = $x("//button[contains(text(),'Search')]");
    //endregion

    //region Methods
    public projectSearchPage pageLoad()
    {
        $(projectSearch).shouldBe(editable);
        projectSearch.click();
        return this;
    }

    public projectSearchPage clickProjectSearchInput()
    {
        $(projectSearchInput).shouldBe(visible);
        projectSearchInput.click();
        return this;
    }

    public projectSearchPage enterValueInProjectSearch(String searchVal)
    {
        $(projectSearchInput).shouldBe(editable);
        System.out.println("This is the Project: " + searchVal);
        projectSearchInput.sendKeys(searchVal);
        return this;
    }

    public projectSearchPage clickSearchBtn()
    {
        $(searchProjectBtn).shouldBe(editable);
        searchProjectBtn.click();
        return this;
    }


    //endregion
}
