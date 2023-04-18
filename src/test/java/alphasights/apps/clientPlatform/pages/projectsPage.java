package alphasights.apps.clientPlatform.pages;

import alphasights.apps.pistachio.pages.pistachioBasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class projectsPage extends clientPlatformBasePage {

    //region Variables
    public String projectsUrl = "https://qa-portal-staging.alphasights.com/my-projects";
    public String projectName;
    //endregion

    //region Locators
    public SelenideElement lastProject = $(By.cssSelector("div:last-child > div:last-child > div.aui-flex-1.aui-flex.aui-flex-col.aui-justify-between.aui-px-6.aui-pt-6.aui-border-0.aui-border-solid.aui-border-black.aui-space-y-5.aui-relative.aui-bg-white.aui-rounded-sm.aui-border-t-2"));

    //endregion

    //region Methods
    public projectsPage clickProject(String projNm)
    {
        this.projectName = projNm;
        SelenideElement desiredProject = $x("//span[text()=" + '"' + projectName + '"' + "]");
        $(desiredProject).shouldBe(interactable);
        desiredProject.click();
        verifyProjectName(projNm);
        System.out.println("User has accessed the following project: " + projNm);
        return this;
    }

    public projectsPage verifyProjectName(String projNm)
    {
        SelenideElement desiredProject = $x("//span[text()=" + '"' + projectName + '"' + "]");
        Assert.assertEquals(projNm, desiredProject.text());
        return this;
    }

    //endregion
}
