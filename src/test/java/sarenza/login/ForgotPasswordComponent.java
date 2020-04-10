package sarenza.login;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sarenza.base.BaseComponent;

public class ForgotPasswordComponent extends BaseComponent {
    public  ForgotPasswordComponent(AndroidDriver driver){
            super(driver);
    }
}
