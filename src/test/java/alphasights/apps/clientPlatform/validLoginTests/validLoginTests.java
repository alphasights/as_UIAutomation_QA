package alphasights.apps.clientPlatform.validLoginTests;

import alphasights.apps.clientPlatform.pages.basePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.general.googleAuth;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;

import java.io.FileReader;
import java.io.IOException;

public class validLoginTests extends googleAuth {
    //region Variables
    //Setup clientPlatform userDetails
    private String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject)obj;


    String clientPlatformUsername = (String)jsonObject.get("clientPlatformUsername");
    String clientPlatformPassword = (String)jsonObject.get("clientPlatformPassword");
    //endregion
    //endregion

    signInPage SignInPage = new signInPage();
    basePage BasePage = new basePage();


    @BeforeGroups("Standard Login")
    public void clientPlatformLogin() throws InterruptedException {
        SignInPage
                .enterUserName(clientPlatformUsername)
                .enterPassword(clientPlatformPassword)
                .verifyLoginStandard();
    }


    @AfterSuite(groups = {"Standard Login"})
    public void clientPlatformLogout(){
        BasePage
                .clickUserNavDropdown()
                .clickSignOut();
    }


    public validLoginTests() throws IOException, ParseException {
    }

}
