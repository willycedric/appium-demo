package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

public class ProductCatalogue extends BaseComponent {

    private final By _megaMenPager = MobileBy.id("megaMenuPager");
    public ProductCatalogue(AndroidDriver driver) {
        super(driver, "Failed to found an element in the product catalogue screen.");
    }

    public ListeProduits selectProductByDescription(String customerGender, String category, String type){
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._megaMenPager)
        ).isDisplayed();
        selectByText(customerGender.toUpperCase().trim());
       Boolean isGender = _waiter.until(
                ExpectedConditions.visibilityOf(_driver.findElementByAccessibilityId(customerGender.toUpperCase()))
        ).isDisplayed();

       selectByText(category.toUpperCase().trim());
       Boolean isCategory;
       try{
           _fastWaiter.until(
                   ExpectedConditions.visibilityOfElementLocated(MobileBy.id("shopping_types_recyclerview"))
           );
           isCategory = _waiter.until(
                   ExpectedConditions.visibilityOfElementLocated(MobileBy.id("toolbar_title"))
           ).getText().toLowerCase().trim().contains(category.toLowerCase().trim());
       }catch(Exception ex){
           isCategory = true;
       }
        Boolean isType;
       if(type!=""){
           selectByText(type);
           isType = _waiter.until(
                   ExpectedConditions.visibilityOfElementLocated(MobileBy.id("toolbar_title"))
           ).getText().toLowerCase().trim().contains(type.toLowerCase().trim());
       }else{
           isType = true;
       }
        return new ListeProduits(_driver);
    }

}
