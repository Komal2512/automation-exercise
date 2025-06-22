package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.Driver.DriverHelper.driver;

public class CategoryBrandstepDef {

    public void scrollToCategorySection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoryHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(text(),'Category')]")));

        new Actions(driver).moveToElement(categoryHeader).pause(Duration.ofMillis(500)).perform();
    }

    @Then("Verify the Categories should be visible on the left sidebar")
    public void verifyCategoriesVisible() {
        WebElement categorySection = driver.findElement(By.xpath("//div[@class='left-sidebar']//h2[text()='Category']"));
        Assert.assertTrue("Category section is not visible", categorySection.isDisplayed());
    }

    @When("User clicks on the Women category")
    public void userClicksOnWomenCategory() {
        scrollToCategorySection();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement womenCategory = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='#Women']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", womenCategory);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> ads = driver.findElements(By.id("aswift_3_host"));
        if (!ads.isEmpty()) {
            for (WebElement ad : ads) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", ad);
            }
        }
        WebElement clickableCategory = wait.until(ExpectedConditions.elementToBeClickable(womenCategory));
        Actions actions = new Actions(driver);
        actions.moveToElement(clickableCategory).click().perform();
    }


    @When("User selects a sub-category under Women")
    public void userSelectsSubCategoryUnderWomen() {
        WebElement subCategory = driver.findElement(
                By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", subCategory);
        subCategory.click();
    }

    @Then("Category page should be displayed with title WOMEN - DRESS PRODUCTS")
    public void verifyWomenDressCategoryPage() {
        WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Women - Dress Products')]")));
        Assert.assertTrue("Category page title incorrect", title.isDisplayed());
    }

    @When("User clicks a sub-category under Men")
    public void userClicksSubCategoryUnderMen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement menCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='#Men']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", menCategory);
        menCategory.click();

        WebElement subCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='Men']//a[contains(text(),'Tshirts')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", subCategory);
        subCategory.click();
    }

    @Then("User should be navigated to MEN - TSHIRTS PRODUCTS category page")
    public void verifyMenTshirtsCategoryPage() {
        WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Men - Tshirts Products')]")));
        Assert.assertTrue("MEN - TSHIRTS PRODUCTS page not displayed", title.isDisplayed());
    }

    @Then("Validate the Brand list should be visible on the left sidebar")
    public void validateBrandListVisible() {
        WebElement brandSection = driver.findElement(By.xpath("//div[@class='brands_products']//h2[contains(text(),'Brands')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", brandSection);
        Assert.assertTrue("Brand list not visible", brandSection.isDisplayed());
    }

    @When("User selects brand Polo")
    public void userSelectsBrandPolo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the brand filter section to be visible
        WebElement poloBrand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(., 'Polo')] | //a[contains(., 'Polo')] | //input[@value='Polo']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", poloBrand);
        poloBrand.click();
    }


    @Then("User should be navigated to the brand page showing products for Polo")
    public void verifyPoloBrandPage() {
        WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Brand - Polo Products')]")));
        Assert.assertTrue("Polo brand product page not visible", title.isDisplayed());
    }

    @When("User clicks on another brand H&M")
    public void userClicksBrandHM() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hmBrand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(., 'H&M')] | //a[contains(., 'H&M')] | //input[@value='H&M']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hmBrand);
        hmBrand.click();
    }

    @Then("User should be navigated to the brand page showing products for H&M")
    public void verifyHMBrandPage() {
        WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Brand - H&M Products')]")));
        Assert.assertTrue("H&M brand product page not visible", title.isDisplayed());
    }


}
