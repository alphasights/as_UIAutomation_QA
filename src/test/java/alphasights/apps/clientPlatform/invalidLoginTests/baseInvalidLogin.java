package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.utilities.googleAuth;
import com.codeborne.selenide.Selenide;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;

import java.io.IOException;

public class baseInvalidLogin extends googleAuth{

    @AfterTest
    public void endSession()
    {
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

    public baseInvalidLogin() throws IOException, ParseException {
    }
}
