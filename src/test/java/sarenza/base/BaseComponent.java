package sarenza.base;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//android.widget.TextView[@text='%s']",text)))
        ).click();
        return  true;
        /*for(WebElement elt:genders){
            if(elt.getText().toLowerCase().trim().contains(text.toLowerCase().trim())){
                elt.click();
                return true;
            }
        }
       return false;*/
    }

    protected By formatResourceId(String id){
        return  MobileBy.id(String.format("%s/%s", baseId, id));
    }
    protected By formatNativeResourceId(String id){
        return MobileBy.id(String.format("android:id/%s", id));
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
    //Demo only
    private void test() {
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
    }
    protected ExpectedCondition<WebElement> horizontalScrollUntilElementMatches(WebElement from, String description ){

        By locator = By.xpath(String.format("//android.widget.TextView[@text='%s']",description));
        int x =0;
        int y = from.getLocation().getY();
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    TouchAction action = new TouchAction(_driver);
                    int z=0;
                    while(z<y){
                        if(elementIfVisible(locator)){
                            return _driver.findElement(locator);
                        }else{
                            z=z+50;
                            action.
                                    press(PointOption.point(x,y))
                                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                                    .moveTo(PointOption.point(x+z,y))
                                    .release()
                                    .perform();
                        }
                    }

                } catch (StaleElementReferenceException e) {
                    return null;
                }
                catch (NoSuchElementException e){
                    return  null;
                }
                return null;
            }



            @Override
            public String toString() {
                return "scroll horizontally until visibility of element located by "+locator;
            }
        };
    }
    protected ExpectedCondition<WebElement> horizontalScrollUntilElementContains(WebElement from, String description ){

        By locator = By.xpath(String.format("//android.widget.TextView[contains(@text,'%s')]",description));
        int x =0;
        int y = from.getLocation().getY();
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    TouchAction action = new TouchAction(_driver);
                    int z=0;
                    while(z<y){
                        if(elementIfVisible(locator)){
                            return _driver.findElement(locator);
                        }else{
                            z=z+50;
                            action.
                                    press(PointOption.point(x,y))
                                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                                    .moveTo(PointOption.point(x+z,y))
                                    .release()
                                    .perform();
                        }
                    }

                } catch (StaleElementReferenceException e) {
                    return null;
                }
                catch (NoSuchElementException e){
                    return  null;
                }
                return null;
            }



            @Override
            public String toString() {
                return "scroll horizontally until visibility of element located by "+locator;
            }
        };
    }
    protected   ExpectedCondition<WebElement> verticalScrollDownUntilElementVisible(final By locator){
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {

                    int x = _driver.manage().window().getPosition().getX();
                    int y = _driver.manage().window().getSize().height;
                    TouchAction action = new TouchAction(_driver);
                    int z=0;
                    while(z<y){
                        if(elementIfVisible(locator)){
                            return _driver.findElement(locator);
                        }else{
                            action.
                                    press(PointOption.point(520,1530))
                                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                                    .moveTo(PointOption.point(520,490+z))
                                    .release()
                                    .perform();
                        }
                        z=z-50;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "scroll vertically until visibility of element located by " + locator;
            }
        };
    }

    protected   ExpectedCondition<WebElement> verticalScrollUpUntilElementVisible(final By locator){
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {

                    int x = _driver.manage().window().getPosition().getX();
                    int y = _driver.manage().window().getSize().height;
                    TouchAction action = new TouchAction(_driver);
                    int z=0;
                    while(z<y){
                        if(elementIfVisible(locator)){
                            return _driver.findElement(locator);
                        }else{
                            action.
                                    press(PointOption.point(520,490+z))
                                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                                    .moveTo(PointOption.point(520,1530))
                                    .release()
                                .perform();
                        }
                        z=z-50;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "scroll vertically until visibility of element located by " + locator;
            }
        };
    }

    private Boolean elementIfVisible(By locator){
        try{
            return _driver.findElement(locator).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }

    protected void longPress(Point point){
        TouchAction action = new TouchAction(this._driver);
        action.longPress(PointOption.point(point.getX(),point.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                .perform();
    }

    protected void tap(Point point){
        TouchAction action = new TouchAction(this._driver);
        action.tap(PointOption.point(point.getX(),point.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                .perform();
    }



}
