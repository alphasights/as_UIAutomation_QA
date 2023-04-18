package alphasights.apps.clientPlatform.validLoginTests;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.utilities.googleAuth;
import com.codeborne.selenide.Selenide;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.FileReader;
import java.io.IOException;

public class baseValidLogin extends googleAuth{


    public baseValidLogin() throws IOException, ParseException {
    }
}