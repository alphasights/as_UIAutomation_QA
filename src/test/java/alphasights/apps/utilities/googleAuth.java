package alphasights.apps.utilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static alphasights.apps.utilities.alphasightsEnvironments.*;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.*;

public class googleAuth {

    //region Variables
    public URL testingURL;
    //region Setup google userDetails

    //region Username
    private final String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject) obj;
    public String googleUserName = (String) jsonObject.get("googleUser");
    public String redeliveryUsername = (String) jsonObject.get("redeliveryUsername");
    public String redeliveryPassword = (String) jsonObject.get("redeliveryPassword");
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
    public URL companiesDeliveryUrl = new URL("https://companies-delivery.alphasights.com/");
    public URL stagingNewProjectUrl = new URL("https://delivery-eng-playground.alphasights.com/projects/new");
    //endregion

    //region Production
    public URL prodPistachioURL = new URL("https://secure.alphasights.com/projects");
    public URL prodDeliveryURL = new URL("https://delivery.alphasights.com/dashboard");
    public URL prodClientPlatformURL = new URL("https://portal.alphasights.com/sign-in");
    //endregion


    //endregion

    //region Locators
    public SelenideElement googleUserNameInput = $("#identifierId");
    public SelenideElement googleNextBtn = $x("//span[text()='Next']");
    public SelenideElement googlePasswordInput = $x("//input[@name ='password']");

    
    public googleAuth environmentSelector(alphasightsEnvironments asEnvironment)
    {
        switch(asEnvironment)
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
            case STAGING_REDELIVERY_NEW_PROJS:
                testingURL = stagingNewProjectUrl;
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
            case COMPANIES_DELIVERY:
                testingURL = companiesDeliveryUrl;
                break;
        }
        return this;
    }
    public void enterUserEmail(String GoogleEmail)
    {
        $(googleUserNameInput).shouldBe(editable);
        googleUserNameInput.sendKeys(GoogleEmail);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
    }

    public void enterUserPassword(String GooglePW)
    {
        $(googlePasswordInput).shouldBe(editable);
        googlePasswordInput.click();
        googlePasswordInput.sendKeys(GooglePW);
        $(googleNextBtn).shouldBe(editable);
        googleNextBtn.click();
    }

    @BeforeTest(groups = "setup")
    @Parameters("browser")
    public void config(@Optional String browser)
    {
        if(browser == null)
        {
            System.out.println("No Browser chosen.  Defaulting to Chrome.");
            Configuration.browser = "chrome";
        } else if (browser.equalsIgnoreCase("chrome")){

            Configuration.browser = "chrome";
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
            Configuration.browser = "firefox";
        } else if (browser.equalsIgnoreCase("safari")) {
            Configuration.browser = "safari";
        } else if (browser.equalsIgnoreCase("edge")) {
            Configuration.browser = "edge";
        } else if (browser.equalsIgnoreCase("ie")) {
            Configuration.browser = "ie";
        }
        else{
            System.out.println("Invalid Browser chosen.  Defaulting to Chrome.");
            Configuration.browser = "chrome";
        }

        Configuration.browserSize ="1366x768";
        Configuration.timeout = 20000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeGroups(groups = {"Pistachio", "ptoSetup", "setup"})
    public void setupPistachio() {
        environmentSelector(QA_PISTACHIO);
        open(testingURL);
        enterUserEmail(googleUserName);
        enterUserPassword(googlePassword);
    }


    @BeforeGroups(groups = {"ClientPlatform", "clientPlatformSetup", "setup"})
    public void setUpClientPlatform() {
        environmentSelector(QA_CLIENT_PLATFORM);
        open(testingURL);
        enterUserEmail(googleUserName);
        enterUserPassword(googlePassword);
        System.out.println("Client Platform is setup.");
    }



    @BeforeGroups(groups = {"Delivery", "deliverySetup", "setup"})
    public void setUpDelivery() {
        environmentSelector(QA_DELIVERY);
        open(testingURL);
        enterUserEmail(googleUserName);
        enterUserPassword(googlePassword);
    }

    @BeforeGroups(groups = {"New Proj Creation", "setup"})
    public void setUpNewProjCreation(){
        environmentSelector(STAGING_REDELIVERY_NEW_PROJS);
        open(testingURL);
        enterUserEmail(googleUserName);
        enterUserPassword(googlePassword);
    }

    @AfterSuite
    public void endSession()
    {
            Selenide.clearBrowserCookies();
            Selenide.closeWebDriver();
    }

    public googleAuth() throws IOException, ParseException {
    }
}

