package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

public class MenuBar extends BaseComponent {

    final private By _menuSelector = MobileBy.className("android.widget.LinearLayout");
    public MenuBar(AndroidDriver driver){
        super(driver, "failed to find element from the MenuBar");
    }


}
