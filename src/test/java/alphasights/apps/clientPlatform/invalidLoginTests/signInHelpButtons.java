package alphasights.apps.clientPlatform.invalidLoginTests;

import alphasights.apps.clientPlatform.pages.signInPage;
import com.codeborne.selenide.Condition;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.enabled;

public class signInHelpButtons extends baseInvalidLogin{
    signInPage SignInPage = new signInPage();

    @DataProvider(name = "userName")
    public Object[][] dpMethod(){
        return new Object[][] {{"abc@def.com"}};
    }

    @Test(groups = {"ClientPlatform", "SignInHelpers"})
    public void clickHelpFromEmailEntry(){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .verifyUsernameInputAvailable()
                .clickHelp();
    }

    @Test(groups = {"ClientPlatform", "SignInHelpers"}, dataProvider = "userName")
    public void clickHelpFromPasswordEntry(String username){
        //Verifying contact tech support url is correct.  Will eventually test actual email creation via API Tool
        SignInPage
                .enterUserName(username)
                .clickHelp();
    }

    @Test(groups = {"ClientPlatform", "SignInHelpers"}, dependsOnMethods = {"clickHelpFromPasswordEntry"})
    public void forgotPasswordPromptsCheckYourInbox(){
        SignInPage
                .clickForgotPassword()
                .verifyCheckYourInbox();
    }

    @AfterMethod
    public void returnToSignIn(){
        if(SignInPage.backfromHelp.is(enabled))
        {
            SignInPage
                    .clickBackFromHelp();
        }
        else if (SignInPage.returnToSignIn.is(enabled)) {
            SignInPage
                    .clickReturnToSignIn();
        }
        else {
            System.out.println("No need to navigate back.  Testing will continue...");
        }
    }


    public signInHelpButtons() throws IOException, ParseException {

    }
}
