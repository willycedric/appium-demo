package sarenza.login;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sarenza.base.BaseComponent;

import java.time.Duration;

public class LoginComponent extends BaseComponent {
    final private By _inputFields=  MobileBy.className("android.widget.EditText");
    final private By _loginButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[3]/android.widget.RelativeLayout");
    final private By _accountCreationButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout");
    final private By _startToShoppingButton = MobileBy.id("home_page_text");
    final private By _gotoLoginButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout");
    final private By _passwordForgotten = MobileBy.id("forget_password");

    public LoginComponent(AndroidDriver driver) {
       super(driver);
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
    public void gotoAccountCreationScreen(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._accountCreationButton)
        ).click();
    }

    public void goDefaultProductView(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._startToShoppingButton)
        ).click();
    }

    public Boolean startToShopping(String country){
        _waiter.until(ExpectedConditions.visibilityOfElementLocated(this._startToShoppingButton)).click();
        CountrySelection selectCountry = new CountrySelection(_driver);
        return selectCountry.selectCountry(country);
    }

    public void gotoForgotPasswordForm(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordForgotten)
        ).click();
    }


}