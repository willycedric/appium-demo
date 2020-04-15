package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

import java.util.List;

public class Favoris extends BaseComponent {
    final private By _headerTitle = MobileBy.id("header_title");
    final private By _title = MobileBy.id("toolbar_title");
    final private By _productViewGroup = MobileBy.className("android.view.ViewGroup");
    final private By _itemSizeSelect = MobileBy.id("favorite_product_list_item_size_select");
    final private By _itemDeleteButton = formatResourceId("favorite_product_list_item_delete");
    final private By _addItemToBasket = MobileBy.id("ctaText");
    final private By _basketButton = MobileBy.id("toolbar_basket");
    final private By _productItemBrand = formatResourceId("product_list_item_brand");
    final private By _productItemModel = formatResourceId("product_list_item_model");
    final private By _productItemPrice = formatResourceId("product_list_item_price");
    final private By _activeDiscount = formatResourceId("sticker_active_discount");
    public Favoris(AndroidDriver driver){
        super(driver, "Failed to find element on screen favoris.");
    }

    public String getHeaderTitle(){
        return getText(this._headerTitle);
    }

    public String getTitle(){
        return getText(this._title);
    }
    private List<WebElement> getProductListGroup(){
        return _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._productViewGroup)
        );
    }

    public void toggleSizeSelect(){
        click(this._itemSizeSelect);
    }

    public  void deleteFavorisAt(int index){
        clickAt(this._itemDeleteButton, index);
    }

    public  void deleteFavoris(){
        click(this._itemDeleteButton);
    }

    public void addItemAtToBasket(int index){
        clickAt(this._addItemToBasket, index);
    }

    public void addItemToBasket(){
        click(this._addItemToBasket);
    }

    public void goToBasket(){
        click(this._basketButton);
    }

    public  String getProductBrand(int index){
        return getTextAt(this._productItemBrand, index);
    }

    public String getProductBrand(){
        return getTextAt(this._productItemBrand, 0);
    }

    public String getProductModel(int index ){
        return getTextAt(this._productItemModel, index);
    }

    public String getProductModel(){
        return getTextAt(this._productItemModel, 0);
    }

    public String getProductPrice(int index ){
        return getTextAt(this._productItemPrice, index);
    }
    public String getProductPrice( ){
        return getTextAt(this._productItemPrice, 0);
    }

    public String getActiveDiscount(){
        return getText(this._activeDiscount);
    }

    public Panier AddItemAtToBasket(int index){
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._productViewGroup)
        ).get(index).findElement(this._itemSizeSelect).click();
        ProductSizeDialog dialog = new ProductSizeDialog(_driver);
        dialog.selectFirstAvailableSize();
        return dialog.subMit();
    }
    public Panier AddItemToBasket(){
       return AddItemAtToBasket(0);
    }
}
