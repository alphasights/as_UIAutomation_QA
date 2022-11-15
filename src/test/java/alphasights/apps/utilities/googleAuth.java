package alphasights.apps.utilities;

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

import java.io.*;
import java.net.*;


import static alphasights.apps.utilities.alphasightsEnvironments.*;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class googleAuth {

    //region Variables
    public URL testingURL;
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

    //region Environment URLs
    //region QA
    public URL qaPistachioURL = new URL("https://qa-pistachio.alphasights.com/");
    public URL qaDeliveryUrl = new URL("https://qa-delivery.alphasights.com/");
    public URL qaClientPlatformUrl = new URL("https://qa-portal-staging.alphasights.com/sign-in");

    //endregion

    //region Staging
    public URL stagingPistachioUrl = new URL("https://eng-playground.alphasights.com/client/accounts");
    public URL stagingDeliveryUrl = new URL("https://delivery-eng-playground.alphasights.com/dashboard");
    public URL stagingClientPlatformUrl = new URL("https://portal-staging.alphasights.com/sign-in");
    //endregion

    //region Production
    public URL prodPistachioURL = new URL("https://secure.alphasights.com/projects");
    public URL prodDeliveryURL = new URL("https://delivery.alphasights.com/dashboard");
    public URL prodClientPlatformURL = new URL("https://portal.alphasights.com/sign-in");
    //endregion


    //endregion

    //region Locators
    public SelenideElement googleUserNameInput = $(By.id("identifierId"));
    public SelenideElement googleNextBtn = $x("//span[text()='Next']");
    public SelenideElement googlePasswordInput = $(By.name("password"));

    
    public googleAuth environmentSelector(alphasightsEnvironments asEnvironement)
    {
        switch(asEnvironement)
        {
            case QA_PISTACHIO:
                testingURL = qaPistachioURL;
                break;
            case QA_DELIVERY:
                testingURL = qaDeliveryUrl;
                break;
            case QA_CLIENT_PLATFORM:
                testingURL = qaClientPlatformUrl;
                break;
            case STAGING_PISTACHIO:
                testingURL = stagingPistachioUrl;
                break;
            case STAGING_DELIVERY:
                testingURL = stagingDeliveryUrl;
                break;
            case STAGING_CLIENT_PLATFORM:
                testingURL = stagingClientPlatformUrl;
                break;
            case PROD_PISTACHIO:
                testingURL = prodPistachioURL;
                break;
            case PROD_DELIVERY:
                testingURL = prodDeliveryURL;
                break;
            case PROD_CLIENT_PLATFORM:
                testingURL = prodClientPlatformURL;
                break;
        }
        return this;
    }

    @BeforeGroups(groups = {"Pistachio"})
    public void setUpQAPistachio() throws InterruptedException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        environmentSelector(QA_PISTACHIO);
        open(testingURL);
        Configuration.browserSize = "1080x1920";
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
        environmentSelector(QA_CLIENT_PLATFORM);
        open(testingURL);
        Configuration.browserSize = "1080x1920";
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
        environmentSelector(QA_DELIVERY);
        open(testingURL);
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
        if(Selenide.sessionId() != null){
            Selenide.clearBrowserCookies();
            Selenide.closeWebDriver();
        }
    }

    public googleAuth() throws IOException, ParseException {
    }



        public void googleAuth() throws IOException, ParseException {
        }
}

