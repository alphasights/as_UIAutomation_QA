package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.signInPage;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class signInHelpButtons extends baseInvalidLogin{
    signInPage SignInPage = new signInPage();

    @DataProvider(name = "userName")
    public Object[][] dpMethod(){
        return new Object[][] {{"abc@def.com"}};
    }

    @Test(groups = {"ClientPlatform", "Sign In Helpers"})
    public void contactTechSupportFromEmailEntry(){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .verifyUsernameInputAvailable()
                .verifyContactSupportEmailIsRightURL();
    }

    @Test(groups = {"ClientPlatform", "Sign In Helpers"}, dataProvider = "userName")
    public void contactTechSupportFromPasswordEntry(String username){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .enterUserName(username)
                .verifyContactSupportEmailIsRightURL();
    }

    @Test(groups = {"ClientPlatform", "Sign In Helpers"}, dependsOnMethods = {"contactTechSupportFromPasswordEntry"})
    public void forgotPasswordPromptsCheckYourInbox(){
        SignInPage
                .clickForgotPassword()
                .verifyCheckYourInbox();
    }

    @Test(groups = {"Client+Platform","Sign In Helpers"}, dependsOnMethods = {"forgotPasswordPromptsCheckYourInbox"})
    public void forgotPasswordReturnToSignIn(){
        SignInPage
                .clickReturnToSignIn()
                .verifyUsernameInputAvailable();
    }


    public signInHelpButtons() throws IOException, ParseException {

    }
}
