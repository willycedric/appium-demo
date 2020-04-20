package sarenza.base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BaseComponent {
    final protected AndroidDriver _driver;
    final protected WebDriverWait _waiter;
    final protected String baseId ="com.sarenza.dev:id";
    final protected  WebDriverWait _fastWaiter;
    final private By _menuSelector = MobileBy.className("android.widget.LinearLayout");
    final private By _bottomBar = MobileBy.id("bottombar");
    private By menuSelector = new ByChained(this._bottomBar, this._menuSelector);
    public BaseComponent(AndroidDriver driver, String msg ) {
        _driver = driver;
        _waiter = (WebDriverWait)new WebDriverWait(_driver, 20)
                .withMessage(msg)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(
                        Duration.ofMillis(50)
                );

        _fastWaiter = (WebDriverWait)new WebDriverWait(_driver, 5)
                .withMessage(msg)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(
                        Duration.ofMillis(50)
                );
    }

    protected Boolean selectByText(String text) {
        List<WebElement> genders = _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("android.widget.TextView"))
        );
        for(WebElement elt:genders){
            if(elt.getText().toLowerCase().trim().contains(text.toLowerCase().trim())){
                elt.click();
                return true;
            }
        }
       return false;
    }

    protected By formatResourceId(String id){
        return  MobileBy.id(String.format("%s/%s", baseId, id));
    }

    public void enCeMoment(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector)
        ).get(1).click();

    }

    public void ventePrivee(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector)
        ).get(3).click();
    }
    public void favorite(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector)
        ).get(4).click();
    }
    public void profil(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector)
        ).get(5).click();
    }
    public void shopping(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector)
        ).get(2).click();
    }

    //Utilities
    protected String getText(By locator){
        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    protected  void click(By locator){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).click();
    }

    protected void clickAt(By locator, int index) {
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        ).get(index).click();
    }

    protected void enterValue(By locator, String value){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).sendKeys(value);
    }

    protected String getTextAt(By locator, int index){
        return _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        ).get(index).getText();
    }

    protected void swipeScreen(By el) throws InterruptedException {
        WebElement Panel = _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(el)
        );
        int x = Panel.getLocation().getX();
        int y = Panel.getLocation().getY();
        TouchAction action = new TouchAction(_driver);
        action.
        press(PointOption.point(x,y))
        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
        .moveTo(PointOption.point(x+290,y))
        .release()
        .perform();
       /* List<WebElement> Panels = _waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(el));
        WebElement Panel = Panels.get(Panels.size() - 1);
        Dimension dimension = Panel.getSize();
        int Anchor = Panel.getSize().getHeight()/2;
        Double ScreenWidthStart = dimension.getWidth() * 0.8;
        int scrollStart = ScreenWidthStart.intValue();
       // _driver.manage().window().getPosition();
        Double ScreenWidthEnd = dimension.getWidth() * 0.2;
        int scrollEnd = ScreenWidthEnd.intValue();
        System.out.println(String.format("start %s end %s", scrollStart, scrollEnd));
        new TouchAction((PerformsTouchActions) _driver)
                .press(PointOption.point(0, 0))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(Panel.getLocation().getX(), Panel.getLocation().getY()))
                .release().perform();
        Thread.sleep(3000);*/

    }
    public void test() {
       // WebDriverWait wait = new WebDriverWait(_driver, 10);

       // WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("List Demo")));
       // screen.click();

        //wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Altocumulus")));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520, 490);
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());


        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);
        _driver.perform(Arrays.asList(swipe));

       // _driver.findElement(MobileBy.AccessibilityId("Stratus"));
    }


}
