package alphasights.apps.clientPlatform.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;

import org.json.simple.parser.*;

import java.io.IOException;

// page_url = https://qa-portal-staging.alphasights.com/sign-in
public class signInPage {

    //region Variables
    public String emailtoClientSupportUrl = "mailto:clientsupport@alphasights.com?subject=Help%20with%20AlphaSights%20Portal"; //Will create seperate API Tests for actual email testing
    //endregion

    //region Locators
    public SelenideElement signInModal = $("div.sc-cxabCf.cHPQrW");
    public SelenideElement emailInput = $x("//input[@placeholder='Email']");
    public SelenideElement nextBtn = $("button.sc-iJkHyd.iwOkOj");
    public SelenideElement editEmail = $("div.sc-cxabCf.cKtFqp");
    public SelenideElement passwordInput = $x("//input[@placeholder='Password']");
    public SelenideElement signinBtn = $(("button.sc-iJkHyd.iwOkOj"));
    public SelenideElement rememberMe = $("div.checkbox-icon");
    public SelenideElement forgotPassword = $x(("//p[text()='Forgot password?']"));
    public SelenideElement sendSignInLink = $x("//p[text()='Send Sign In Link']");
    public SelenideElement helpLink = $x("//p[text()='Help']");
    public SelenideElement alphaSightsLimitationsOfUse = $x("//p[text() = 'Limitations of Use']");
    public SelenideElement signUpLink = $x("//p[text() = 'Sign Up']");
    public SelenideElement usernameInvalidEmailNotice = $x("//span[text()='Enter a valid email format.']");
    public SelenideElement invalidLoginNotice = $x("//span[text()='Your email and/or password is incorrect.']");
    public SelenideElement checkYourInbox = $("div.LoginTransition-enter-done");
    public SelenideElement resendPasswordReset = $x("//p[text()='Didn’t receive the email? Resend.']");
    public SelenideElement backfromHelp = $x("//p[text()= 'Back']");
    public SelenideElement returnToSignIn = $x("//p[text()= 'Return to Sign In']");



    //endregion

    public signInPage() throws IOException, ParseException {
    }

    clientPlatformBasePage clientPlatformBasePage = new clientPlatformBasePage();
    projectsPage ProjectsPage = new projectsPage();


    //region Actions
    public signInPage enterUserName(String email)
    {
        $(emailInput).shouldBe(editable);
        emailInput.sendKeys(email);
        clickNextButton();
        return this;
    }

    public signInPage clickNextButton()
    {
        $(nextBtn).shouldBe(enabled);
        nextBtn.click();
        return this;
    }

    public signInPage enterUserNameWithoutNext(String email)
    {
        $(emailInput).shouldBe(editable);
        emailInput.click();
        emailInput.sendKeys(email);
        return this;
    }

    public signInPage clearUsername()
    {
        $(emailInput).shouldBe(editable);
        if(!emailInput.getValue().isEmpty()) {
            emailInput.sendKeys(COMMAND + "A");
            emailInput.sendKeys(DELETE);
            System.out.println("Username has been cleared out.");
        }
        else
        {
            System.out.println("Username was already blank.");
        }
        return this;
    }

    public signInPage editUserName()
    {
        $(editEmail).shouldBe(editable);
        editEmail.click();
        return this;
    }

    public signInPage enterPassword(String password) throws InterruptedException {
        $(passwordInput).shouldBe(editable);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        $(signinBtn).shouldBe(editable);
        signinBtn.click();
        return this;
    }

    public signInPage clickRememberMe(){
        $(rememberMe).shouldBe(enabled);
        rememberMe.click();
        return this;
    }

    public signInPage clickForgotPassword(){
        $(forgotPassword).shouldBe(visible);
        forgotPassword.click();
        return this;
    }

    public signInPage clickSignIn(){
        $(signinBtn).shouldBe(enabled);
        signinBtn.click();
        return this;
    }

    public signInPage clickSendSignInLink(){
        $(sendSignInLink).shouldBe(enabled);
        sendSignInLink.click();
        return this;
    }

    public signInPage clickHelp(){
        $(helpLink).shouldBe(enabled);
        helpLink.click();
        return this;
    }

    public signInPage clickLimitationsOfUse(){
        $(alphaSightsLimitationsOfUse).shouldBe(enabled);
        alphaSightsLimitationsOfUse.click();
        return this;
    }

    public signInPage clickResendPasswordReset(){
        $(resendPasswordReset).shouldBe(editable);
        resendPasswordReset.click();
        return this;
    }

    public signInPage clickBackFromHelp(){
        $(backfromHelp).shouldBe(editable);
        backfromHelp.click();
        return this;
    }

    public signInPage clickReturnToSignIn(){
        $(returnToSignIn).shouldBe(editable);
        returnToSignIn.click();
        return this;
    }

    public signInPage refreshPage(){
        //timeout = 5000;
        Selenide.refresh();
        $("body").shouldBe(visible);
        Selenide.switchTo().activeElement();
        return this;
    }

    public signInPage closeWindow(){
        actions().sendKeys(COMMAND + "W");
        return this;
    }

    public signInPage navigateBack(){
        actions().sendKeys(BACK_SPACE);
        Selenide.switchTo().defaultContent();
        return this;
    }

    //endregion

    //region Assertions
    public signInPage verifyUsernameInputAvailable(){
        $(emailInput).shouldBe(editable);
        return this;
    }

    public signInPage verifyLoginStandard() throws InterruptedException {

        $(clientPlatformBasePage.projectsLink).shouldBe(editable);
        $(ProjectsPage.lastProject).shouldBe(visible);
        System.out.println(WebDriverRunner.url());
        return this;
    }

    public signInPage verifyInvalidEmailNotice()
    {
        emailInput.sendKeys(TAB);
        $(usernameInvalidEmailNotice).shouldBe(visible);
        return this;
    }

    public signInPage verifyLoginFailedForUser()
    {
        $(signinBtn).shouldBe(disabled);
        invalidLoginNotice.shouldBe(visible);
        return this;
    }

    public signInPage verifyCheckYourInbox()
    {
        $(checkYourInbox).shouldBe(editable);
        return this;
    }

    public signInPage waitForSignInPageToLoad()
    {
        emailInput.exists();
        return this;
    }
    //endregion
}
