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
    final private By _sizeSelector =formatResourceId("product_size_select");
    final private By _sizeItem = formatResourceId("size_item_layout");
    final private  By _sizeItemCheck = MobileBy.id("size_item_check");
    final private By _productDetailsButton = MobileBy.id("product_characteristics_button");
    final private By _addToBasketButton = MobileBy.id("ctaButton");
    final private By _productColorVariant = MobileBy.id("product_color_variant_dialog_button");
    final private By _productShare = MobileBy.id("product_share_button");
    final private By _customerReviewDetails = MobileBy.id("product_reviews_block");
    final private By _sarenzaAdvantageSection = MobileBy.id("product_advantages_block");
    final private By _productOtherColorSection = MobileBy.id("colored_products_recyclerview");
    final private By _productPushSection = MobileBy.id("other_models_container");
    final private By _listeProductTrait = MobileBy.id("shop_multi_view");
    final private By _goToBasket = MobileBy.id("toolbar_side_right");
    final private By _basketTrait = MobileBy.id("ctaButton");
    final  private By _basketBadge = MobileBy.id("toolbar_basket_badge");
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

    public  FicheProduitDetails addToBasket(){
        _waiter.until(
                verticalScrollDownUntilElementVisible(this._sizeSelector)
        );
        toggleSizeSelector();
        //Select the first available item
        _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._sizeItem)
        ).get(0).click();
        //Wait until the check is displayed
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._sizeItemCheck)
        );
        click(this._addToBasketButton);
        return this;
    }

    public Panier viewBasket(){
        click(this._goToBasket);
        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(this._basketTrait)
        );
        return new Panier(_driver);
    }

    public String getBasketCount(){
        return getText(this._basketBadge);
    }
}
