package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

public class Filter extends BaseComponent {
    final private By _title = MobileBy.id("toolbar_title");
    final private By _buttonClose = MobileBy.id("image_back");
    final private By _listProductTrait = MobileBy.id("shop_multi_view");
    final private By _priceBar= MobileBy.id("filter_price_rangebar");
    final private By _switcher = formatResourceId("switcher");
    public  Filter(AndroidDriver driver){
        super(driver, "failed to find element from the filter screen");
    }

    public ListeProduits closeFilter(){
        click(this._buttonClose);

        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(_listProductTrait)
        );
        return  new ListeProduits(_driver);
    }

    public String getTitle(){
        return getText(this._title);
    }

    public void swipe() throws InterruptedException {
        swipeScreen(
              this._switcher
        );
    }


    public void scrollToSwitch() {
        AndroidElement switchElement = (AndroidElement)_waiter.until(
                ExpectedConditions.visibilityOfElementLocated(
                        this._switcher
                )
        );
       /* MobileElement item = (MobileElement) _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()).scrollIntoView("+
                                        "new UiSelector().description(\"DEMARQUE SOLDES\"));"

                        )
                )
        );*/
        MobileElement item = (MobileElement) _driver.findElement(
                MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView("+
                                "new UiSelector().index(6));"

                )
        );

    }
}
