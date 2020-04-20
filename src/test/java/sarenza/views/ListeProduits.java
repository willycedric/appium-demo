package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;
import sarenza.login.LoginComponent;
import sun.rmi.runtime.Log;

public class ListeProduits extends BaseComponent {
    final private By _buttonMultiView = MobileBy.id("shop_multi_view");
    final private By _buttonFilter = formatResourceId("shop_filter_sort_layout");
    final private By _productViewGroup = formatResourceId("product_list_item_image");
    final private By _itemFavorite = formatResourceId("product_list_item_favorite");
    final private By _productBrand = formatResourceId("productlist_brandName");
    final private By _productModel = formatResourceId("productlist_modelName");
    final private By _productPrice = formatResourceId("product_list_item_price");
    final private By _loginTrait = MobileBy.id("field");
    final private By _articleCount = formatResourceId("shop_article_count");
    final private  By _filterTrait = formatResourceId("switcher");
    final private By _ficheProdutTrait = MobileBy.id("image_slider");
    public ListeProduits(AndroidDriver driver){
        super(driver,"Failed to found element on the fiche produit screen.");
    }

    public boolean isDisplayed() {

        return _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._buttonMultiView)
        ).isDisplayed();
    }

    public LoginComponent offLineAddItemAtToFavorite(int index){

        clickAt(this._itemFavorite, index);
        //wait until the light login screen is displayed
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._loginTrait)
        );
        return new LoginComponent(_driver);
    }

    public LoginComponent offLineAddItemToFavorite(){
       return offLineAddItemAtToFavorite(0);
    }

    public void onLineAddItemAtToFavorite(int index ){
        clickAt(this._itemFavorite, index);
    }

    public void onlineAddItemToFavorite(){
        onLineAddItemAtToFavorite(0 );
    }

    public String getProductBrandAt(int index){
        return getTextAt(this._productBrand, index);
    }

    public String getProductBrand(){
        return getTextAt(this._productBrand, 0);
    }

    public String getProductModelAt(int index){
        return getTextAt(this._productModel, index);
    }
    public String getProductModel(){
        return getTextAt(this._productModel, 0);
    }
    public String getProductPriceAt(int index){
        return getTextAt(this._productPrice, index);
    }

    public String getProductPrice(){
        return getTextAt(this._productPrice, 0);
    }

    public FicheProduitDetails selectProductAt(int index){
        clickAt(this._productViewGroup, index);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._ficheProdutTrait)
        );
        return new FicheProduitDetails(_driver);
    }

    public FicheProduitDetails selectProduct(){
        return selectProductAt(0);
    }

    public void toggleMultiView(){
        click(this._buttonMultiView);
    }

    public Filter filter(){
        click(this._buttonFilter);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._filterTrait)
        );
        return new Filter(_driver);

    }

    public String getArticleCount(){
        return getText(this._articleCount);
    }
}
