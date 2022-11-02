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
    public SelenideElement signInModal = $("div.ReactModal__Content");
    public SelenideElement emailInput = $(By.name("email"));
    public SelenideElement nextBtn = $x("//button[text()='Next']");
    public SelenideElement contactSupportLink = $(By.linkText("Contact technical support."));
    public SelenideElement editEmail = $("div.aui-inline-flex.aui-items-center.aui-space-x-2.aui-text-grey-4.aui-cursor-pointer");
    public SelenideElement passwordInput = $(By.name("password"));
    public SelenideElement signinBtn = $x(("//button[text()='Sign In']"));
    public SelenideElement rememberMe = $("i.aui-icon.aui-icon-checkbox");
    public SelenideElement forgotPassword = $x(("//a[text()='Forgot password?']"));
    public SelenideElement sendSignInLink = $x("//button[text()='Send Sign In Link']");
    public SelenideElement alphaSightsLimitationsOfUse = $(By.linkText("AlphaSights' Limitations of Use"));
    public SelenideElement usernameInvalidEmailNotice = $x("//div[text()='Invalid email address']");
    public SelenideElement invalidLoginNotice = $x("//div[text()='Your email or password does not match. If this account is not yet registered please contact technical support.']");
    public SelenideElement checkYourInbox = $("div.ReactModal__Content");
    public SelenideElement resendPasswordReset = $(By.linkText("Didn't work? Resend."));
    public SelenideElement returnToSignIn = $(By.linkText("Return to Sign In"));



    //endregion

    public signInPage() throws IOException, ParseException {
    }

    basePage BasePage = new basePage();
    projectsPage ProjectsPage = new projectsPage();


    //region Actions
    public signInPage enterUserName(String email)
    {
        timeout = 10000;
        $(emailInput).shouldBe(editable);
        emailInput.sendKeys(email);
        $(nextBtn).shouldBe(editable);
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
        timeout = 10000;
        $(emailInput).shouldBe(editable);
        emailInput.sendKeys(COMMAND + "A");
        emailInput.sendKeys(BACK_SPACE);
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
    public signInPage clickContactSupportLink()
    {
        timeout = 10000;
        $(contactSupportLink).shouldBe(editable);
        contactSupportLink.click();
        return this;
    }

    public signInPage clickForgotPassword(){
        timeout = 10000;
        $(forgotPassword).shouldBe(visible);
        forgotPassword.click();
        return this;
    }

    public signInPage clickResendPasswordReset(){
        $(resendPasswordReset).shouldBe(editable);
        resendPasswordReset.click();
        return this;
    }

    public signInPage clickReturnToSignIn(){
        timeout = 10000;
        $(returnToSignIn).shouldBe(editable);
        returnToSignIn.click();
        return this;
    }

    public signInPage refreshPage(){
        timeout = 5000;
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

        timeout = 10000;
        $(BasePage.projectsLink).shouldBe(editable);
        $(ProjectsPage.lastProject).shouldBe(visible);
        System.out.println(WebDriverRunner.url());
        return this;
    }

    public signInPage verifyInvalidEmailNotice()
    {
        timeout = 10000;
        emailInput.sendKeys(TAB);
        $(usernameInvalidEmailNotice).shouldBe(visible);
        return this;
    }

    public signInPage verifyLoginFailedForUser()
    {
        timeout = 10000;
        $(signinBtn).shouldBe(disabled);
        invalidLoginNotice.shouldBe(visible);
        return this;
    }


    public signInPage verifyContactSupportEmailIsRightURL()
    {
        $(contactSupportLink).shouldBe(editable);
        contactSupportLink.getAttribute("href").equals(emailtoClientSupportUrl);
        return this;
    }

    public signInPage verifyCheckYourInbox()
    {
        $(checkYourInbox).shouldBe(editable);
        return this;
    }
    //endregion
}
