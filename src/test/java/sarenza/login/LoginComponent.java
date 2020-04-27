package sarenza.login;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;


import java.util.ArrayList;
import java.util.List;

public class LoginComponent extends BaseComponent {
    final private By _inputFields=  MobileBy.className("android.widget.EditText");
    final private By _loginButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[3]/android.widget.RelativeLayout");
    final private By _accountCreationButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout");
    final private By _startToShoppingButton = MobileBy.id("home_page_text");
    final private By _gotoLoginButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout");
    final private By _passwordForgotten = MobileBy.id("forget_password");
    final private By _loginErrors = MobileBy.id("error");
    final private By _passwordHint = MobileBy.id("password_hint_button");
    final private By _logo = MobileBy.id("sarenza_logo");
    final private By _laboC = MobileBy.xpath(" /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
    //MobileBy.xpath("/android.widget.ListView/[contains(@class, \"android.widget.CheckedTextView\") and normalize-space(text()) = \"LaboC\"]");

    public LoginComponent(AndroidDriver driver) {
       super(driver, "Failed to found element in Login component.");
    }

    public  void selectLabo(String labo){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._logo)
        ).click();
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(String.format("//android.widget.CheckedTextView[@text='%s']", labo)))
        ).click();
    }
    /**
     * Fill user informations on the login form
     * @param userName
     * @param password
     */
    public void fillUserInfo(String userName, String password){

        this.goToLoginForm();
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._inputFields)
        ).get(0).sendKeys(userName);
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._inputFields)
        ).get(1).sendKeys(password);
    }

    /**
     * Submit user info
     */
    public  void submit()
    {
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._loginButton)
        ).click();
    }

    public void goToLoginForm(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._gotoLoginButton)
        ).click();

    }
    public void closeRegionAlert(){

    }
    public CountrySelection gotoAccountCreationScreen(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._accountCreationButton)
        ).click();
        return new CountrySelection(_driver);
    }

    public void goDefaultProductView(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._startToShoppingButton)
        ).click();
    }

    public Boolean startToShopping(String country)  {
        _waiter.until(ExpectedConditions.visibilityOfElementLocated(this._startToShoppingButton)).click();
        CountrySelection selectCountry = new CountrySelection(_driver);
        return selectCountry.selectCountry(country);
    }

    public void gotoForgotPasswordForm(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordForgotten)
        ).click();
    }

    public String getUserNameError(){
       String error = _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._loginErrors)
        ).getText();
       return error;
    }

    public String getPassword(){
        String error = _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._loginErrors)
        ).getText();
        return error;
    }

    public List<String> getUserNameAndPasswordErrors(){
        String userNameError = _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._loginErrors)
        ).get(0).getText();
        String passwordError =  _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._loginErrors)
        ).get(1).getText();
        List<String> errors =  new ArrayList<String>();
        errors.add(userNameError);
        errors.add(passwordError);
        return errors;
    }

    public void togglePasswordHint(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordHint)
        ).click();
    }
}