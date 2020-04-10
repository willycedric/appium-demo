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

class CountrySelection extends BaseComponent {

    final private By _listOfCountries = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout");

    CountrySelection(AndroidDriver driver){
       super(driver);
    }

    public Boolean selectCountry(String country){

        List<WebElement> countries = _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(_listOfCountries)
        );

        for(int index =1; index<countries.size();index++){
            WebElement elt = _driver.findElement(MobileBy.xpath(String.format("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[%s]/android.widget.LinearLayout/android.widget.TextView", index)));
            String text = elt.getText();
            if(text.toLowerCase().contains(country.toLowerCase()))
            {
                elt.click();
                return true;
            }
        }
        return false;
    }
}