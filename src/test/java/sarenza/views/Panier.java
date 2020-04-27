package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.account.CreateAccountFullBase;
import sarenza.base.BaseComponent;
import sarenza.login.LoginComponent;

import java.util.List;

public class Panier extends BaseComponent {

    final private By _buttonClose = MobileBy.id("toolbar_text_left");
    final private By _title = MobileBy.id("toolbar_title");
    final private By _reductionField = MobileBy.id("basketcoupon_add_edittext");
    final private By _submitReductionButton = MobileBy.id("basketcoupon_add_ok_button");
    final private By _submitBasketButton = MobileBy.id("ctaButton");
    final private By _freeReturnsTextView = MobileBy.id("free_returns_text_view");
    final private By _hundredDaysTextView = MobileBy.id("hundred_days_text_view");
    final private By _productViewGroup = MobileBy.className("android.view.ViewGroup");
    final private By _basketItemPrice = MobileBy.id("basket_item_price");
    final private By _basketItemQuantity = MobileBy.id("text1");
    final private By _basketProductModel = MobileBy.id("basket_item_model");
    final private By _basketItemBrand = MobileBy.id("basket_item_brand");
    final private By _basketItemColor = MobileBy.id("basket_item_color");
    final private By _basketItemSize = MobileBy.id("basket_item_size");
    final private By _basketActiveDiscount = MobileBy.id("sticker_active_discount");
    final private By _basketSubTotalValue = MobileBy.id("subtotal_value");
    final private By _basketSubTotalLabel = MobileBy.id("subtotal_label");

    public Panier(AndroidDriver driver){
        super(driver, "failed to found element from the panier");
    }


    public void closeBasket(){
       click(this._buttonClose);
    }

    public String getTitle(){
       return getText(this._title);
    }

    public void enterReductionCode(String code){
        enterValue(this._reductionField, code);
    }

    public void submitReduction()
    {
       click(this._submitReductionButton);
    }

    public PaiementInformation submitBasket(){
       click(this._submitBasketButton);

       return new PaiementInformation(_driver);
    }

    public CreateAccountFullBase submitBasketWithLightAccount(){
        click(this._submitBasketButton);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(MobileBy.id("title_label"))
        );
        return new CreateAccountFullBase(_driver);
    }

    public String getItemBrand(int index){
        return getTextAt(this._basketItemBrand, index);
    }
    public String getItemBrand(){
        return getTextAt(this._basketItemBrand, 0);
    }

    public String getItemModel(int index){
        return getTextAt(this._basketProductModel, index);
    }

    public String getItemModel(){
        return getTextAt(this._basketProductModel, 0);
    }
    public String getItemQuantity(int index){
        return getTextAt(this._basketItemQuantity, index);
    }
    public String getItemQuantity(){
        return getTextAt(this._basketItemQuantity, 0);
    }

    public String getItemSize(int index){
        return getTextAt(this._basketItemSize, index);
    }
    public String getItemSize(){
        return getTextAt(this._basketItemSize, 0);
    }

    public  String getItemColor(int index){
        return getTextAt(this._basketItemColor, index).split(":")[1];
    }
    public  String getItemColor(){
        return getTextAt(this._basketItemColor, 0).split(":")[1];
    }


    public String getItemPrice(int index){
        return getTextAt(this._basketItemPrice, index);
    }
    public String getItemPrice(){
        return getTextAt(this._basketItemPrice, 0);
    }
    public String getFreeReturnsText(){

        return getText(this._freeReturnsTextView);
    }

    public String getHundredDaysText(){
        return getText(this._hundredDaysTextView);
    }

    public String getBasketActiveDiscount(){
        return getText(this._basketActiveDiscount);
    }

    public String getBasketSubTotal(){

        return getText(this._basketSubTotalValue);
    }

    public  String getBasketSubTotalLabel(){
        return getText(this._basketSubTotalLabel);
    }

    private List<WebElement> getProductView(){
        return _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._productViewGroup)
        );
    }

    public Boolean isPanierDisplayed(){
      return  _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._title)
        ).isDisplayed();
    }
}
