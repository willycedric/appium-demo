package sarenza.account;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateAccountFullBase extends BaseComponent {

    private final By _firstNameField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.EditText");
    private final By _lastNameField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.EditText");
    private final By _dateOfBirthField  = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[2]");
    private final By _addressField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.EditText");
    private final By _firstComplementaryAddress = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[2]/android.widget.EditText");
    private final By _secondComplementaryAddress = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[3]/android.widget.EditText");
    private final By _companyField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[4]/android.widget.EditText");
    private final By _countryField = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Spinner[1]/android.widget.TextView");
    private final By _postCode = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[3]/android.widget.EditText");
    private final By _citySpinner=MobileBy.id("city_spinner");
    private final By _userAsInvoiceAddress = MobileBy.id("use_as_invoice_address");
    private final By _cellPhoneField = MobileBy.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.EditText");
    private final By _submitButton = MobileBy.id("ctaButton");
    private final By _okDatePicker = formatNativeResourceId("button1");

    public CreateAccountFullBase(AndroidDriver driver){
        super(driver, "Failed to find elements on the CreateAccountFull screee");
    }

    public   CreateAccountFullBase fillUserBirthInfo(String firstName, String lastName) throws InterruptedException {
        enterValue(this._firstNameField, firstName);
        enterValue(this._lastNameField, lastName);
        click(this._dateOfBirthField);
        List<WebElement> dateElements  =  _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.xpath("//android.widget.EditText"))
        );
        Point day = new Point(dateElements.get(0).getLocation().getX(), dateElements.get(0).getLocation().getY());
        Point month = new Point(dateElements.get(1).getLocation().getX(), dateElements.get(1).getLocation().getY());
        Point year = new Point(dateElements.get(2).getLocation().getX(), dateElements.get(2).getLocation().getY());

        //Day
        List<KeyEvent> days = new ArrayList();
        days.add(new KeyEvent(AndroidKey.DIGIT_2));
        days.add(new KeyEvent(AndroidKey.DIGIT_2));
        //Month
        List<KeyEvent> months = new ArrayList();
        months.add(new KeyEvent(AndroidKey.J));
        months.add(new KeyEvent(AndroidKey.A));
        //Year
        List<KeyEvent> years = new ArrayList();
        years.add(new KeyEvent(AndroidKey.DIGIT_2));
        years.add(new KeyEvent(AndroidKey.DIGIT_0));
        years.add(new KeyEvent(AndroidKey.DIGIT_1));
        years.add(new KeyEvent(AndroidKey.DIGIT_0));
        //day
        longPress(day);
        for (KeyEvent key:days) {
            _driver.pressKey(key);
        }
        _driver.hideKeyboard();
        //month
        longPress(month);
        for (KeyEvent key:months) {
            _driver.pressKey(key);
        }
       _driver.hideKeyboard();
        //year
        longPress(year);
        for (KeyEvent key:years) {
            _driver.pressKey(key);
        }
        _driver.hideKeyboard();
        click(this._okDatePicker);
        return this;
    }

    public CreateAccountFullBase fillUserAdress(String address){
        enterValue(this._addressField, address);
        return  this;
    }

    public CreateAccountFullBase fillFistComplementaryAddress(String address){
        enterValue(this._firstComplementaryAddress, address);
        return this;
    }

    public CreateAccountFullBase fillSecondComplementaryAddress(String address){
        enterValue(this._secondComplementaryAddress, address);
        return this;
    }

    public CreateAccountFullBase fillCompanyName(String company){
        enterValue(this._companyField, company);
        return this;
    }

    public CreateAccountFullBase fillDeliveryAddress(String address){
        WebElement addressField = _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.xpath("//android.widget.EditText"))
        ).get(2);

      addressField.click();
        List<KeyEvent> months = new ArrayList();
        months.add(new KeyEvent(AndroidKey.DIGIT_1));
        months.add(new KeyEvent(AndroidKey.DIGIT_0));
        months.add(new KeyEvent(AndroidKey.SPACE));
        months.add(new KeyEvent(AndroidKey.R));
        months.add(new KeyEvent(AndroidKey.U));
        months.add(new KeyEvent(AndroidKey.E));
        for (KeyEvent key:months) {
            _driver.pressKey(key);
        }
        _driver.hideKeyboard();
        return this;
    }

    public CreateAccountFullBase fillPostCode(String postCode){

        _waiter.until(
                verticalScrollDownUntilElementVisible(this._postCode)
        ).click();
       // enterValue(this._postCode, postCode );

        List<KeyEvent> months = new ArrayList();
        months.add(new KeyEvent(AndroidKey.DIGIT_9));
        months.add(new KeyEvent(AndroidKey.DIGIT_5));
        months.add(new KeyEvent(AndroidKey.DIGIT_1));
        months.add(new KeyEvent(AndroidKey.DIGIT_1));
        months.add(new KeyEvent(AndroidKey.DIGIT_0));
        for (KeyEvent key:months) {
            _driver.pressKey(key);
        }
        _driver.hideKeyboard();
        return this;
    }
    public  CreateAccountFullBase fillCity(String city){
        _waiter.until(
                verticalScrollDownUntilElementVisible(this._citySpinner)
        );
        enterValue(this._citySpinner, city);
        return this;
    }
    public CreateAccountFullBase toggleInvoiceAddress(){
        click(this._userAsInvoiceAddress);
        return this;
    }
    public CreateAccountFullBase fillCellPhone(String cellPhone){
        _waiter.until(
                verticalScrollDownUntilElementVisible(this._cellPhoneField)
        );
        enterValue(this._cellPhoneField, cellPhone);
        return this;
    }

    public void submitForm(){
        click(this._submitButton);
    }

}
