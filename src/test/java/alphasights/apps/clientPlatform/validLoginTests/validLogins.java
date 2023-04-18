package alphasights.apps.clientPlatform.validLoginTests;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;

public class validLogins extends baseValidLogin {

    //region Variables
    //Setup clientPlatform userDetails
    private String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject)obj;

    String clientPlatformUsername = (String)jsonObject.get("clientPlatformUsername");
    String clientPlatformPassword = (String)jsonObject.get("clientPlatformPassword");

    signInPage SignInPage = new signInPage();
    clientPlatformBasePage ClientPlatformBasePage = new clientPlatformBasePage();

    @BeforeGroups(groups = {"ClientPlatform"})
    public void clientPlatformLogin() throws InterruptedException {
        SignInPage
                .enterUserName(clientPlatformUsername)
                .enterPassword(clientPlatformPassword)
                .verifyLoginStandard();
    }

    //endregion

    @AfterTest()
    public void clientPlatformLogout(){
        ClientPlatformBasePage
                .clickUserNavDropdown()
                .clickSignOut();
    }


    public validLogins() throws IOException, ParseException {
    }

}
