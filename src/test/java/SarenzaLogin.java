import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sarenza.account.CreateAccountBase;
import sarenza.login.CountrySelection;
import sarenza.login.LoginComponent;
import sarenza.views.FicheProduitDetails;
import sarenza.views.Filter;
import sarenza.views.ListeProduits;
import sarenza.views.ProductCatalogue;


public class SarenzaLogin {


    private AndroidDriver driver;
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private double max=100, min=1, x;
    private CreateAccountBase accountBase;
    private ProductCatalogue catalogue;
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.0");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("automationName", "UiAutomator1");
        caps.setCapability("appPackage","com.sarenza.dev");
        caps.setCapability("appActivity","com.sarenza.ui.home.HomeActivity_");
        caps.setCapability("newCommandTimeout","3000");
        driver = new AndroidDriver(new URL(APPIUM), caps);
        try { Thread.sleep(3000); } catch (Exception ign) {}
        LoginComponent home = new LoginComponent(driver);
        home.selectLaboC();
        CountrySelection country =  home.gotoAccountCreationScreen();
        country.selectCountry("France");
         x = (Math.random()*((max-min)+1))+min;
         accountBase = new CreateAccountBase(driver);
         accountBase.enterUserInfos(String.format("email%s@email.com", x),"Sarenza123","France","Homme");
         catalogue= accountBase.submit();
    }

    @Test
    public void login() {
        LoginComponent login = new LoginComponent(driver);
        login.fillUserInfo("ncornaglia-externe@sarenza.com","sarenza");
        driver.hideKeyboard();
        login.submit();
        WebElement displayed = new WebDriverWait(driver, 10)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(MobileBy.id("alert_message"))
                );
        System.out.println(displayed.getText());
        Assert.assertEquals(displayed.isDisplayed(), true);
    }

    @Test
    public void startShopping()  {
        LoginComponent login = new LoginComponent(driver);
        Boolean isShoppingStarted = login.startToShopping("Belgique");
        Assert.assertEquals(isShoppingStarted, true);
    }

    @Test
    public void createAccountAndFilterColor()  {
       ListeProduits listeProduits = catalogue.selectProductByDescription("Homme", "Chaussures","Baskets");
       Boolean filterMatches = listeProduits
               .filter()
               .selectColorByDescription("Jaune", "Vert","Beige")
               .removeSelectedTag("Beige")
               .checkTagForValues("Jaune","Vert");
       Assert.assertEquals(filterMatches, true);
    }

    @Test
    public void createAccountAndFilterSize() {
        ListeProduits listeProduits = catalogue.selectProductByDescription("Homme", "Chaussures","Baskets");
        Boolean filterMatches = listeProduits
                .filter()
                .selectProductSizeByDescription("43","37")
                .checkTagForValues("43","37");
        Assert.assertEquals(filterMatches, true);
    }

    @Test
    public void createAccountAndFilterByReduction() {
        ListeProduits listeProduits = catalogue.selectProductByDescription("Homme", "Chaussures","Baskets");
        Boolean filterMatches = listeProduits
                .filter()
                .selectColorByDescription("Blanc")
                .selectProductSizeByDescription("43","37")
                .selectProductReductionDescription("30%", "40%")
                .checkTagForValues("30%","43", "40%", "Blanc");
        Assert.assertEquals(filterMatches, true);
    }


    @Test
    public void swipe(){

    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
