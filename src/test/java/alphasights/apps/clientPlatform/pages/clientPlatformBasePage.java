package alphasights.apps.clientPlatform.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class clientPlatformBasePage {

    //region Variables
    //endregion

    //region Locators
    public SelenideElement contentLink = $(By.linkText("Content"));
    public SelenideElement alphaNowLink = $(By.linkText("AlphaNow"));
    public SelenideElement projectsLink = $x("//p[contains(text(),'Projects')]");
    public SelenideElement scheduleLink = $(By.linkText("Schedule"));
    public SelenideElement servicesLink = $(By.linkText("Services"));
    public SelenideElement informationIcon = $("div.sc-cxabCf.bIoByO > svg > path");
    public SelenideElement userNavDropdown = $("div.sc-cxabCf.bDqsfj");
    public SelenideElement preferences = $("div.sc-cxabCf.hVSpvX > div.sc-cxabCf.gmkJTb:nth-child(1) > p.sc-eKBdFk.hOVSvm");
    public SelenideElement signOut = $("div.sc-cxabCf.hVSpvX > div.sc-cxabCf.gmkJTb:nth-child(2) > p.sc-eKBdFk.hOVSvm");


    //endregion

    //region Methods

    public clientPlatformBasePage clickSubNavLink(clientPlatformPages linkName)
    {
        switch(linkName){
            case CONTENT:
                $(contentLink).shouldBe(editable);
                contentLink.click();
                break;
            case ALPHANOW:
                $(alphaNowLink).shouldBe(editable);
                alphaNowLink.click();
                break;
            case PROJECTS:
                $(projectsLink).shouldBe(editable);
                projectsLink.click();
                break;
            case SCHEDULE:
                $(scheduleLink).shouldBe(editable);
                scheduleLink.click();
                break;
            case SERVICES:
                $(servicesLink).shouldBe(editable);
                servicesLink.click();
                break;
            default:
                System.out.println("An invalid link name was entered.");
                break;
        }
        return this;
    }

    public clientPlatformBasePage clickInformationIcon()
    {
        $(informationIcon).shouldBe(editable);
        informationIcon.click();
        return this;
    }

    public clientPlatformBasePage clickUserNavDropdown()
    {
        $(userNavDropdown).shouldBe(editable);
        userNavDropdown.click();
        return this;
    }

    public clientPlatformBasePage clickPreferences()
    {
        $(preferences).shouldBe(editable);
        preferences.click();
        return this;
    }

    public clientPlatformBasePage clickSignOut()
    {
        $(signOut).shouldBe(editable);
        signOut.click();
        return this;
    }

    //endregion
}
