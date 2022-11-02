package alphasights.apps.pistachio.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class clientEntitiesPage extends basePage{
    //region Variables
    public String clientEntitiesUrl = "https://qa-pistachio.alphasights.com/client/entities";
    //endregion

    //region Locators
    //region Main
    public SelenideElement newEntities = $("a.new_client_entity");
    public SelenideElement entitiesKeywordSearch = $(By.id("keywords"));
    public SelenideElement submitEntitiesKeywordSearch = $(By.cssSelector("button.search.button"));
    //endregion

    //region New Client Contact

    //endregion
    //endregion
}