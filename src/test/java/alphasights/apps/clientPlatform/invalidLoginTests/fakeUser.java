package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.general.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class fakeUser extends googleAuth{
    signInPage SignInPage = new signInPage();

    @Test(groups = {"Client Platform", "Not Real User"})
    public void clientPlatformBadLoginFakeUser(){
        try {
            SignInPage
                    .clearUsername()
                    .enterUserName("abc@def.com")
                    .enterPassword("password123")
                    .verifyLoginFailedForUser();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"Client Platform", "Not Real User"}, dependsOnMethods = {"clientPlatformBadLoginFakeUser"})
    public void clientPlatformReplaceUsername(){
        SignInPage
                .editUserName()
                .verifyUsernameInputAvailable();
    }

    public fakeUser() throws IOException, ParseException {
    }
}
