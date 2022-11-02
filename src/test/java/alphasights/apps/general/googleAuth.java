package alphasights.apps.general;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import java.io.*;
import java.net.*;


import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class googleAuth {

    //region Variables
    //region Setup google userDetails

    //region Username
    private String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject) obj;
    public String googleUserName = (String) jsonObject.get("googleUser");
    //endregion

    //region Password
    InputStream qaUserInfo = new URL("https://qa-accounts.s3.amazonaws.com/c06134f5fda45ce05e26d8704235a7a1/qa-user-info.json").openStream();
    Object obj2 = jsonParser.parse(new InputStreamReader(qaUserInfo));
    JSONObject jsonObject2 = (JSONObject) obj2;
    public String googlePassword = (String) jsonObject2.get("ui-tester.3@alphasights.com");
    //endregion
    //endregion
    //endregion

    public URL clientPlatformUrl = new URL("https://qa-portal-staging.alphasights.com/sign-in");
    public URL pistachioUrl = new URL("https://qa-pistachio.alphasights.com/");
    public URL deliveryUrl = new URL("https://qa-delivery.alphasights.com/");

    //region Locators
    public SelenideElement googleUserNameInput = $(By.id("identifierId"));
    public SelenideElement googleNextBtn = $x("//span[text()='Next']");
    public SelenideElement googlePasswordInput = $(By.name("password"));


    @BeforeGroups(groups = {"Pistachio"})
    public void setUpPistachio() throws InterruptedException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(pistachioUrl);
        Configuration.browserSize = "1080 x 1920";
        $(googleUserNameInput).shouldBe(editable);
        googleUserNameInput.sendKeys(googleUserName);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
        $(googlePasswordInput).shouldBe(editable);
        System.out.println(googlePassword);
        googlePasswordInput.sendKeys(googlePassword);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
    }

    @BeforeGroups(groups = {"Client Platform"})
    public void setUpClientPlatform() throws InterruptedException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(clientPlatformUrl);
        Configuration.browserSize = "1080 x 1920";
        $(googleUserNameInput).shouldBe(editable);
        googleUserNameInput.sendKeys(googleUserName);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
        $(googlePasswordInput).shouldBe(editable);
        System.out.println(googlePassword);
        googlePasswordInput.sendKeys(googlePassword);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
    }

    @BeforeGroups(groups = {"Delivery"})
    public void setUpDelivery() throws InterruptedException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(deliveryUrl);
        Configuration.browserSize = "1080 x 1920";
        $(googleUserNameInput).shouldBe(editable);
        googleUserNameInput.sendKeys(googleUserName);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
        $(googlePasswordInput).shouldBe(editable);
        System.out.println(googlePassword);
        googlePasswordInput.sendKeys(googlePassword);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
    }

    @AfterSuite
    public void endSession()
    {
        if(!Selenide.sessionId().equals(null)){
            Selenide.clearBrowserCookies();
            Selenide.closeWebDriver();
        }
    }

    public googleAuth() throws IOException, ParseException {
    }



        public void googleAuth() throws IOException, ParseException {
        }
}

