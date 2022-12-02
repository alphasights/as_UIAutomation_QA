package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class invalidLogins extends googleAuth {


        signInPage SignInPage = new signInPage();
        clientPlatformBasePage BasePage = new clientPlatformBasePage();

        @DataProvider (name = "userName")
        public Object[][] dpMethod(){
            return new Object[][] {{"abc"}, {"abc@def"}, {"abc.com"}, {"abc@def.g"}};
        }


        @BeforeMethod(groups = {"ClientPlatform", "False Email", "clientInvalidLogin"})
        public void clearUsername(){
            SignInPage
                    .clearUsername();
        }

        @Test(groups = {"ClientPlatform", "False Email", "clientInvalidLogin"}, dataProvider = "userName")
        public void clientPlatformBadLoginFalseEmail(String username){
            SignInPage
                    .enterUserNameWithoutNext(username)
                    .verifyInvalidEmailNotice();
        }
        @AfterMethod(groups = {"ClientPlatform", "clientInvalidLogin", "Not Real User"})
        public void refreshBrowser(){
            SignInPage
                    .refreshPage()
                    .verifyUsernameInputAvailable();
        }

    public invalidLogins() throws IOException, ParseException {
    }

}
