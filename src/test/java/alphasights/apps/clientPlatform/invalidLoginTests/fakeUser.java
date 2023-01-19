package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.signInPage;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class fakeUser extends baseInvalidLogin{
    signInPage SignInPage = new signInPage();

    @DataProvider(name = "userName")
    public Object[][] dpMethod(){
        return new Object[][] {{"abc@def.com"}};
    }

    @Test(groups = {"ClientPlatform", "clientInvalidLogin", "Not Real User"}, dataProvider = "userName")
    public void clientPlatformBadLoginFakeUser(String username){
        try {
            SignInPage
                    .clearUsername()
                    .enterUserName(username)
                    .enterPassword("password123")
                    .verifyLoginFailedForUser();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"ClientPlatform", "clientInvalidLogin", "Not Real User"}, dependsOnMethods = {"clientPlatformBadLoginFakeUser"})
    public void clientPlatformReplaceUsername(){
        SignInPage
                .editUserName()
                .verifyUsernameInputAvailable();
    }


    public fakeUser() throws IOException, ParseException {
    }
}
