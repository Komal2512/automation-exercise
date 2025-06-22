package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static com.Driver.DriverHelper.driver;
import static org.junit.Assert.*;

public class ContactTestCaseStepDef {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @And("User clicks on Contact Us button")
    public void userClicksOnContactUsButton() {
        WebElement contactUsBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Contact us')]")));
        contactUsBtn.click();
    }

    @Then("Verify GET IN TOUCH is visible")
    public void verifyGETINTOUCHIsVisible() {
        WebElement getInTouch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Get In Touch')]")
        ));
        assertTrue("GET IN TOUCH text is not visible", getInTouch.isDisplayed());
    }

    @When("User enters {string}, {string}, {string}, and {string}")
    public void userEntersAnd(String name, String email, String subject, String message) {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("subject")).sendKeys(subject);
        driver.findElement(By.name("message")).sendKeys(message);
    }

    @And("User uploads file from path {string}")
    public void userUploadsFileFromPath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = (classLoader.getResource(fileName));
        assertNotNull("File not found in resources: " + fileName, resource);
        File file = new File(resource.getFile());
        assertTrue(" File not found: " + file.getAbsolutePath(), file.exists());
        WebElement uploadInput = driver.findElement(By.name("upload_file"));
        uploadInput.sendKeys(file.getAbsolutePath());
    }

    @And("User clicks on Submit button")
    public void userClicksOnSubmitButton() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.name("submit")
        ));
        submitBtn.click();
    }

    @And("User clicks OK on the alert")
    public void userClicksOKOnTheAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (NoAlertPresentException e) {
            fail("Expected alert not present");
        }
    }

    @Then("Verify success message {string} is visible")
    public void verifySuccessMessageIsVisible(String expectedMessage) {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + expectedMessage + "')]")
        ));
        assertTrue("Success message not visible", successMsg.isDisplayed());
    }

    @When("User clicks on Home button")
    public void userClicksOnHomeButton() {
        WebElement homeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Home')]")
        ));
        homeBtn.click();
    }

    @When("User clicks on Test Cases button")
    public void userClicksOnTestCasesButton() {
        WebElement testCasesBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Test Cases')]")
        ));
        testCasesBtn.click();
    }

    @Then("Verify that user is navigated to test cases page successfully")
    public void verifyThatUserIsNavigatedToTestCasesPageSuccessfully() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[.//b[contains(text(),'Test Cases')]]")
        ));
        assertTrue("Test Cases page not visible", heading.isDisplayed());
    }

    @Then("Verify success message Success! Your details have been submitted successfully. is visible")
    public void verifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisible() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Success! Your details have been submitted successfully.')]")
        ));
        assertTrue("Success message not visible", successMsg.isDisplayed());
    }

    @And("User clicks on Review Submit button")
    public void userClicksOnReviewSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement reviewSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='review-form']//button[@type='submit']")));
            reviewSubmitButton.click();
    }
}

