package sarenza.views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sarenza.base.BaseComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter extends BaseComponent {
    final private By _title = MobileBy.id("toolbar_title");
    final private By _buttonClose = MobileBy.id("image_back");
    final private By _listProductTrait = MobileBy.id("shop_multi_view");
    final private By _priceBar= MobileBy.id("filter_price_rangebar");
    final private By _switcher = formatResourceId("switcher");
    final private By _tagLabel = formatResourceId("tagLabel");
    public  Filter(AndroidDriver driver){
        super(driver, "failed to find element from the filter screen");
    }

    public ListeProduits closeFilter(){
        click(this._buttonClose);

        _waiter.until(
                ExpectedConditions.visibilityOfElementLocated(_listProductTrait)
        );
        return  new ListeProduits(_driver);
    }

    public String getTitle(){
        return getText(this._title);
    }


    /**
     * Select a color by her description
     * @param descriptions
     * @return
     */
    public Filter selectColorByDescription(String ...descriptions)  {
        for(String description:descriptions){
            List<WebElement> elements = _driver.findElements(formatResourceId("filter_color_label"));
            WebElement color = _waiter.until(
                    horizontalScrollUntilElementMatches(elements.get(0), description)
            );
            TouchAction action = new TouchAction(_driver);
            int x = color.getLocation().getX();
            int y = color.getLocation().getY();
            action.tap(PointOption.point(x,y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                    .perform();
        }
        _waiter.until(
                verticalScrollUpUntilElementVisible(this._tagLabel)
        );
        return this;
    }

    public Filter selectProductSizeByDescription(String ...descriptions){
        _waiter.until(
                verticalScrollDownUntilElementVisible(MobileBy.xpath(String.format("//android.widget.TextView[@text='%s']", "Toutes les tailles")))
        );
        List<WebElement> elements = _driver.findElements(this._switcher);
        for(String description:descriptions){
            WebElement color = _waiter.until(
                    horizontalScrollUntilElementMatches(elements.get(1).findElements(formatResourceId("filter_sort_label")).get(0), description)
            );
            TouchAction action = new TouchAction(_driver);
            int x = color.getLocation().getX();
            int y = color.getLocation().getY();
            action.tap(PointOption.point(x,y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                    .perform();
        }
        _waiter.until(
                verticalScrollUpUntilElementVisible(this._tagLabel)
        );
        return this;
    }
    public Filter selectProductHighHeelByDescription(String ...descriptions){
        _waiter.until(
                verticalScrollDownUntilElementVisible(MobileBy.xpath(String.format("//android.widget.TextView[@text='%s']", "Toutes les hauteurs de talon")))
        );
        List<WebElement> elements = _driver.findElements(this._switcher);
        for(String description:descriptions){
            WebElement color = _waiter.until(
                    horizontalScrollUntilElementMatches(elements.get(3).findElements(formatResourceId("filter_sort_label")).get(0), description)
            );
            TouchAction action = new TouchAction(_driver);
            int x = color.getLocation().getX();
            int y = color.getLocation().getY();
            action.tap(PointOption.point(x,y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                    .perform();
        }
        _waiter.until(
                verticalScrollUpUntilElementVisible(this._tagLabel)
        );
        return this;
    }

    public  Filter selectProductReductionDescription(String ...descriptions){
        _waiter.until(
                verticalScrollDownUntilElementVisible(MobileBy.xpath(String.format("//android.widget.TextView[@text='%s']", "Toutes les démarques et soldes")))
        );
        List<WebElement> elements = _driver.findElements(this._switcher);
        for(String description:descriptions){
            WebElement color = _waiter.until(
                    horizontalScrollUntilElementContains(elements.get(2).findElements(formatResourceId("filter_sort_label")).get(0), description)
            );
            TouchAction action = new TouchAction(_driver);
            int x = color.getLocation().getX();
            int y = color.getLocation().getY();
            action.tap(PointOption.point(x,y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
                    .perform();
        }
        _waiter.until(
                verticalScrollUpUntilElementVisible(this._tagLabel)
        );
        return this;
    }
    /**
     * Remove the list of values passed as parameters from the tag list
     * @param tags
     * @return
     */
    public Filter removeSelectedTag(String ...tags){
        _waiter.until(
                verticalScrollUpUntilElementVisible(this._tagLabel)
        );
       for(String tag:tags){
          List<WebElement> elts = _waiter.until(
                   ExpectedConditions.visibilityOfAllElementsLocatedBy(this._tagLabel)
           );
         for(WebElement elt:elts){
              if(elt.getText().toLowerCase().trim().contains(tag.trim().toLowerCase())){
                  elt.click();
                  return this;
              }
          }
       }
        return this;
    }

    /**
     * Fetch all the tag's value
     * @return
     */
    private List<String> getFiltertags(){
       return  _waiter.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(this._tagLabel)
        ).stream().map(entry -> entry.getText().trim()).collect(Collectors.toList());

    }

    /**
     * Check if the values passed as parameters are contained in the list of tag
     * @param tags
     * @return
     */
      public  Boolean checkTagForValues(String ...tags){
            List<String> items = new ArrayList();
          _waiter.until(
                  verticalScrollUpUntilElementVisible(this._tagLabel)
          );
        for(String s:tags){

            items.add(s.trim());
        }
        List<String> entries = getFiltertags();

        Boolean isContained = entries.containsAll(items);
        return isContained;
    }

    public void scrollToSwitch() {
        AndroidElement switchElement = (AndroidElement)_waiter.until(
                ExpectedConditions.visibilityOfElementLocated(
                        this._switcher
                )
        );

    }
}
