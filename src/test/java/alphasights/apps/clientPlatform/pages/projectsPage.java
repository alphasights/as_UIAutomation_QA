package alphasights.apps.clientPlatform.pages;

import alphasights.apps.pistachio.pages.pistachioBasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class projectsPage extends pistachioBasePage {

    //region Variables
    public String projectsUrl = "https://qa-portal-staging.alphasights.com/my-projects";
    //endregion


    //region Locators

    public SelenideElement lastProject = $(By.cssSelector("div:last-child > div:last-child > div.aui-flex-1.aui-flex.aui-flex-col.aui-justify-between.aui-px-6.aui-pt-6.aui-border-0.aui-border-solid.aui-border-black.aui-space-y-5.aui-relative.aui-bg-white.aui-rounded-sm.aui-border-t-2"));


    //endregion

    //region Methods


    //endregion
}
