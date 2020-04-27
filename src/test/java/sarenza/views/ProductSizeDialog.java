package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

import java.util.stream.Collectors;

class ProductSizeDialog extends BaseComponent {
    final private By _dialogHeader = MobileBy.id("product_size_dialog_header");
    final private By _submitButton = MobileBy.id("ctaButton");
    final private By _itemSize = MobileBy.AccessibilityId("size_item_size_txt2");
    final private By _basketTrait = MobileBy.id("basketcoupon_add_ok_button");
    final private By _sizeAlertLink = formatResourceId("_countryField");
      public ProductSizeDialog(AndroidDriver driver){
        super(driver, "failed to find element on product size dialog");
    }

     public String getHeader(){
        return getText(this._dialogHeader);
    }

     public Panier subMit(){
        click(this._submitButton);
        //wait until the basket screen is displayed
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._basketTrait)
        );
        return new Panier(_driver);
    }

     public void selectFirstAvailableSize(){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._itemSize)
        ).stream().filter(elt -> {
           try{
               return ! elt.findElement(this._sizeAlertLink).isDisplayed();
           }catch(Exception ex){
               return true;
           }
        }
        ).collect(Collectors.toList()).get(0).click();
    }

}
