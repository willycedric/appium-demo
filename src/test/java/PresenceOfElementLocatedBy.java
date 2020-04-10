import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

class PresenceOfElementLocatedBy implements ExpectedCondition<Boolean> {
    private final By locator;
    public PresenceOfElementLocatedBy(By locator){
        this.locator = locator;
    }
    @NullableDecl
    @Override
    public Boolean apply(@NullableDecl WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Returns false because the element is not present in DOM. The
            // try block checks if the element is present and is visible.
            return true;
        } catch (StaleElementReferenceException e) {
            // Returns true because stale element reference implies that element
            // is no longer visible.
            return true;
        }
    }
    @Override
    public String toString(){
        return "visibility of element located by " + locator;
    }
}