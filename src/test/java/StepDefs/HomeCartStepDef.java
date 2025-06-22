package StepDefs;

import com.Driver.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.Driver.DriverHelper.driver;
import static org.junit.Assert.assertTrue;

public class HomeCartStepDef {

    @And("User scrolls down to footer")
    public void userScrollsDownToFooter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("footer")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        assertTrue("Footer is not visible after scroll", footer.isDisplayed());
    }

    @Then("User should see text SUBSCRIPTION")
    public void userShouldSeeTextSUBSCRIPTION() {
        WebElement subscriptionText = driver.findElement(By.xpath("//h2[normalize-space()='Subscription']"));
        assertTrue("SUBSCRIPTION text is not visible", subscriptionText.isDisplayed());
    }

    @When("User enters email {string} in the subscription input and clicks the arrow button")
    public void userEntersEmailInTheSubscriptionInputAndClicksTheArrowButton(String email) {
        WebElement emailInput = driver.findElement(
                By.id("susbscribe_email"));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement submitButton = driver.findElement(By.id("subscribe"));
        submitButton.click();
    }

    @Then("A success message You have been successfully subscribed! should be visible")
    public void aSuccessMessageYouHaveBeenSuccessfullySubscribedShouldBeVisible() {
        WebElement successMsg = driver.findElement(
                By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
        assertTrue("Success message is not displayed", successMsg.isDisplayed());
    }

    @And("User clicks on Cart button")
    public void userClicksOnCartButton() {
            WebElement cartBtn = driver.findElement(
                    By.xpath("//a[@href='/view_cart']"));
            cartBtn.click();
    }
}
