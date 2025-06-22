package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static com.Driver.DriverHelper.driver;

public class CheckoutAddressInvoicsStepDef {

    @Then("Verify Address Details and Review Order")
    public void verifyAddressDetailsAndReviewOrder() {
        WebElement addressSection = driver.findElement(By.id("address_delivery"));
        WebElement orderReview = driver.findElement(By.xpath("//h2[contains(text(),'Review Your Order')]"));
        Assert.assertTrue("Address section not visible", addressSection.isDisplayed());
        Assert.assertTrue("Review Order section not visible", orderReview.isDisplayed());
    }

    @When("User enters {string} and clicks on Place Order")
    public void userEntersCommentAndClicksPlaceOrder(String comment) {
        WebElement commentBox = driver.findElement(By.name("message"));
        commentBox.sendKeys(comment);

        WebElement placeOrderButton = driver.findElement(By.xpath("//a[@href='/payment']"));
        placeOrderButton.click();
    }

    @And("User enters payment details with {string} on Card, {string}, {string}, {string}")
    public void userEntersPaymentDetailsWithOnCard(String name, String cardNumber, String cvc, String expiration) {
        driver.findElement(By.name("name_on_card")).sendKeys(name);
        driver.findElement(By.name("card_number")).sendKeys(cardNumber);
        driver.findElement(By.name("cvc")).sendKeys(cvc);

        String[] expiry = expiration.split("/");
        driver.findElement(By.name("expiry_month")).sendKeys(expiry[0]);
        driver.findElement(By.name("expiry_year")).sendKeys(expiry[1]);
    }

    @And("User clicks on Pay and Confirm Order")
    public void userClicksOnPayAndConfirmOrder() {
        WebElement payButton = driver.findElement(By.id("submit"));
        payButton.click();
    }

    @Then("Validate the success message Congratulations! Your order has been confirmed! should be visible")
    public void validateOrderSuccessMessage() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Congratulations! Your order has been confirmed!')]")));
                Assert.assertTrue("Success message not displayed", successMessage.isDisplayed());
    }


        @When("User clicks on Download Invoice button")
    public void userClicksOnDownloadInvoiceButton() {
        WebElement invoiceBtn = driver.findElement(By.xpath("//a[contains(text(),'Download Invoice')]"));
        invoiceBtn.click();
    }

    @Then("Invoice should be downloaded successfully")
    public void invoiceShouldBeDownloadedSuccessfully() {
        String downloadPath = "/Users/komalgoel/Downloads";  // macOS default
        String expectedFileName = "invoice.txt";  // or .pdf based on site

        File downloadedFile = new File(downloadPath + "/" + expectedFileName);
        boolean isDownloaded = false;

        for (int i = 0; i < 10; i++) {
            if (downloadedFile.exists()) {
                isDownloaded = true;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        Assert.assertTrue("Invoice not downloaded successfully", isDownloaded);
    }

    @When("User clicks on the arrow button at bottom right to scroll up")
    public void userClicksOnScrollUpArrow() {

        WebElement scrollUpBtn = driver.findElement(
                By.id("scrollUp"));

        scrollUpBtn.click();
    }

    @When("User manually scrolls up to the top of the page")
    public void userManuallyScrollsToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }


    @Then("Validate Full-Fledged practice website for Automation Engineers text should be visible")
    public void validateHomePageBannerTextIsVisible() {

        WebElement bannerText = driver.findElement(
                By.xpath("//*[contains(text(),'Full-Fledged practice website for Automation Engineers')]"));

        Assert.assertTrue("Banner text not visible", bannerText.isDisplayed());
    }


}
