package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.basePage;
import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;

public class invalidLogin extends googleAuth {


    public class invalidLogins{

        signInPage SignInPage = new signInPage();
        basePage BasePage = new basePage();

        public invalidLogins() throws IOException, ParseException {
        }

        @BeforeMethod(groups = {"False Email", "Invalid Login"})
        public void clearUsername(){
            SignInPage
                    .clearUsername();
        }

        @Test(groups = {"Client Platform", "False Email", "Invalid Login"})
        @Parameters({"username"})
        public void clientPlatformBadLoginFalseEmail(String username){
            SignInPage
                    .enterUserNameWithoutNext(username)
                    .verifyInvalidEmailNotice();
        }
        @AfterMethod(groups = {"Invalid Login", "Not Real User"})
        public void refreshBrowser(){
            SignInPage
                    .refreshPage();
        }



    }

    public invalidLogin() throws IOException, ParseException {
    }

}
