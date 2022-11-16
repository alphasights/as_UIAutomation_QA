package alphasights.apps.delivery.pages;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class projectDetailsPage extends basePage{

    //region Locators
    public SelenideElement projectExternalTitle = $("div.aui-text-section");

    public projectDetailsPage() throws IOException, ParseException {
    }
    //endregion

    //region Methods
    //endregion
}
