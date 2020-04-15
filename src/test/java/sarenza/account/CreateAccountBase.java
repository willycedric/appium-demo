package sarenza.account;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;
import sarenza.views.ProductCatalogue;

import java.util.List;


public class CreateAccountBase extends BaseComponent {
    final private By _buttonBack = MobileBy.id("image_back");
    final private By _title = MobileBy.id("toolbar_title");
    final private By _titleLabel = MobileBy.id("title_label");
    final private By _submitButton = MobileBy.id("ctaText");
    final private By _newsLetterYes = MobileBy.id("yes_button");
    final private By _newsLetterNo = MobileBy.id("no_button");
    final private By _emailField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.EditText");
    final private By _passwordField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.EditText");
    final private By _countrySelectorDDL= MobileBy.id("country_spinner");
    final private By _genderSelectorDDL = MobileBy.id("gender_spinner");
    final private By _passwordHint = MobileBy.id("password_hint_button");
    final private By _basketIcon = MobileBy.className("android.widget.ImageView");
    private final By _megaMenPager = MobileBy.id("megaMenuPager");
    public CreateAccountBase(AndroidDriver driver) {
        super(driver, "Failed to found element in the account creation screen.");
    }

    public void goToHomeScreen(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._buttonBack)
        ).click();
    }

    public String getTitle(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._title)
        ).getText();
    }

    public String getTitleLabel(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._titleLabel)
        ).getText();
    }

    public void subscribeToNewsLetter(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._newsLetterYes)
        ).click();
    }

    public ProductCatalogue submit(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._submitButton)
        ).click();
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._megaMenPager)
        );
        return new ProductCatalogue(_driver);
    }

    public Boolean enterUserInfos(String email, String password, String targetedCountry, String targetedGender)  {
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._emailField)
        ).sendKeys(email);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordField)
        ).sendKeys(password);
        //Open Country DDL
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._countrySelectorDDL)
        ).click();
        Boolean isCountry = selectByText(targetedCountry);
        //open Gender DDL
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._genderSelectorDDL)
        ).click();
        Boolean isGender = selectByText(targetedGender);
        return isCountry && isGender;
    }

    public void tooglePasswordHint(){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordHint)
        ).click();
    }

    public String getPasswordHint(){
        String text =  _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._passwordHint)
        ).getText();
        return text;
    }

    public Boolean isHomeViewDisplayed(){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._basketIcon)
        ).isDisplayed();
    }
}
