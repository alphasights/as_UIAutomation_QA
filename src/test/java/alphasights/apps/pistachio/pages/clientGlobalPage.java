package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class clientGlobalPage extends pistachioBasePage{
    //region Variables
    public String clientGlobalUrl = "https://qa-pistachio.alphasights.com/client/targets";
    //endregion

    //region Locators
    //region Global Clients
    public SelenideElement newGlobal = $(By.linkText("New Global"));
    public SelenideElement globalKeywordSearch = $(By.id("keywords"));
    public SelenideElement submitGlobalKeywordSearch = $(By.cssSelector("button.search.button"));
    //endregion
    //endregion

    //region Methods
    //region Actions
    //endregion

    //region Assertions
    //endregion
    //endregion
}