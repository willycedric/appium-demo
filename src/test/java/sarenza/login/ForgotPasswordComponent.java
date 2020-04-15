package sarenza.login;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

public class ForgotPasswordComponent extends BaseComponent {
    //Mobile element
    final private By _forgottenEmailField = MobileBy.id("field");
    final private By _sendForgottenEmailButton = MobileBy.id("ctaButton");
    final private By _alertMessage= MobileBy.id("alert_message");
    final private By _acceptAlertMessage = MobileBy.id("alert_right_button");
    final private By _hintMessage = MobileBy.id("hint");
    final private By _backButton = MobileBy.id("image_back");
    final private By _title = MobileBy.id("toolbar_title");

    //Methods
    public  ForgotPasswordComponent(AndroidDriver driver){
            super(driver, "Failed to found element in Forgot password screen.");
    }

    public void fillEmail(String email){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._forgottenEmailField)
        ).sendKeys(email);
    }

    public void sendEmail(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._sendForgottenEmailButton)
        ).click();
    }

    public String getAlertMessage(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._alertMessage)
        ).getText();
    }

    public String getHint(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._hintMessage)
        ).getText();
    }
    public String getTitle(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._title)
        ).getText();
    }
    public void acceptAlertMessage(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._acceptAlertMessage)
        ).click();
    }

    public LoginComponent goBackToLogin(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._backButton)
        ).click();
        return new LoginComponent(_driver);
    }


}
