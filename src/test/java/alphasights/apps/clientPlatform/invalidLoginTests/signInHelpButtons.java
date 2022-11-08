package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.signInPage;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class signInHelpButtons extends googleAuth{
    signInPage SignInPage = new signInPage();

    @Test(groups = {"Client Platform", "Sign In Helpers"})
    public void contactTechSupportFromEmailEntry(){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .verifyUsernameInputAvailable()
                .verifyContactSupportEmailIsRightURL();
    }

    @Test(groups = {"Client Platform", "Sign In Helpers"})
    public void contactTechSupportFromPasswordEntry(){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .enterUserName("abc@def.com")
                .verifyContactSupportEmailIsRightURL();
    }

    @Test(groups = {"Client Platform", "Sign In Helpers"}, dependsOnMethods = {"contactTechSupportFromPasswordEntry"})
    public void forgotPasswordPromptsCheckYourInbox(){
        SignInPage
                .clickForgotPassword()
                .verifyCheckYourInbox();
    }

    @Test(groups = {"Client Platform","Sign In Helpers"}, dependsOnMethods = {"forgotPasswordPromptsCheckYourInbox"})
    public void forgotPasswordReturnToSignIn(){
        SignInPage
                .clickReturnToSignIn()
                .verifyUsernameInputAvailable();
    }


    public signInHelpButtons() throws IOException, ParseException {

    }
}
