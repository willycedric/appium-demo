package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

public class FicheProduitDetails extends BaseComponent {
    final private  By _imageSlider = MobileBy.id("image_slider");
    final private By _buttonBack = MobileBy.id("image_back");
    final private By _productBrand = MobileBy.id("product_brand_textview");
    final private By _productModel = MobileBy.id("product_model_textview");
    final private By _sizeSelector = MobileBy.id("product_size_select");
    final private By _productDetailsButton = MobileBy.id("product_characteristics_button");
    final private By _addToBasketButton = MobileBy.id("ctaButton");
    final private By _customerReviewDetails = MobileBy.id("product_reviews_block");
    final private By _sarenzaAdvantageSection = MobileBy.id("product_advantages_block");
    final private By _productOtherColorSection = MobileBy.id("colored_products_recyclerview");
    final private By _productPushSection = MobileBy.id("other_models_container");
    final private By _listeProductTrait = MobileBy.id("shop_multi_view");

    public FicheProduitDetails(AndroidDriver driver){
        super(driver, "Failed to find element from the fiche produit detaillés.");
    }

    public void clickOnSlider(){
       click(this._imageSlider);
    }

    public ListeProduits  back(){

        click(this._buttonBack);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._listeProductTrait)
        );
        return new ListeProduits(_driver);
    }

    public  String getProductBrand(){
      return getText(this._productBrand);
    }

    public String getProductModel(){
       return getText(this._productModel);
    }

    public void toggleSizeSelector(){
        click(this._sizeSelector);
    }

    public  void viewProductDetails(){
        click(this._productDetailsButton);
    }

    public  void viewCustomerReviews(){
        click(this._customerReviewDetails);
    }

    public  void addToBasket(){
        click(this._addToBasketButton);
    }


}
