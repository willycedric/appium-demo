package sarenza.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent {
    final protected AndroidDriver _driver;
    final protected WebDriverWait _waiter;

    public BaseComponent(AndroidDriver driver) {
        _driver = driver;
        _waiter = (WebDriverWait)new WebDriverWait(_driver, 10)
                .withMessage("Failed to fetch element on the login screen")
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(
                        Duration.ofMillis(50)
                );
    }
}
