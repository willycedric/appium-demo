package sarenza.login;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sarenza.base.BaseComponent;

import java.time.Duration;
import java.util.List;

public class CountrySelection extends BaseComponent {

    final private By _listOfCountries = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout");

    CountrySelection(AndroidDriver driver){
       super(driver, "Failed to found element in the country selection screen.");
    }

    public Boolean selectCountry(String country)  {
      return  selectByText(country);
    }
}