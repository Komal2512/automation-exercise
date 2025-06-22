package StepDefs;

import com.Driver.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.Driver.DriverHelper.driver;
import static org.junit.Assert.assertTrue;

public class RegisterStepDef {

    @Given("User launches the browser")
    public void userLaunchesTheBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }



    @When("User navigates to {string}")
    public void userNavigatesTo(String url) {
        url = "https://automationexercise.com";
        driver.get(url);
    }

    @Then("Verify that home page should be visible successfully")
    public void verifyThatHomePageShouldBeVisibleSuccessfully() {
            WebElement homeIcon = driver.findElement(By.cssSelector(".fa.fa-home"));
            assertTrue("Home Page is not visible", homeIcon.isDisplayed());

    }

    @And("User clicks on Signup or Login button")
    public void userClicksOnSignupOrLoginButton() {
        WebElement signupLoginBtn = driver.findElement(By.cssSelector(".fa.fa-lock"));
        signupLoginBtn.click();
    }

    @Then("Verify New User Signup! section should be visible")
    public void verifyNewUserSignupSectionShouldBeVisible() {
        WebElement newUserSignup = driver.findElement(By.xpath("//h2[contains(text(), 'New User Signup!')]"));
        assertTrue("New User Signup! section is not visible", newUserSignup.isDisplayed());
    }

    @When("User enters name {string} and email address {string}")
    public void userEntersNameAndEmailAddress(String namePrefix, String emailPrefix) {
            WebElement nameField = driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
            WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));

            int randomNumber = new Random().nextInt(10000); 
            String randomName = namePrefix + randomNumber;
            String randomEmail = emailPrefix + randomNumber + "@example.com";

            nameField.sendKeys(randomName);
            emailField.sendKeys(randomEmail);
    }

    @And("User click on Signup button")
    public void userClickOnSignupButton() {
        WebElement signupBtn = driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
        signupBtn.click();
    }


    @Then("Verify ENTER ACCOUNT INFORMATION section should be visible")
    public void verifyENTERACCOUNTINFORMATIONSectionShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accInfoSctnHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='title text-center']/b[contains(text(),'Enter Account Information')]")
        ));
        assertTrue("ENTER ACCOUNT INFORMATION heading is not visible", accInfoSctnHeading.isDisplayed());
    }

    @When("User enters account details: {string}, {string}, {string}, {string}, {string}")
    public void userEntersAccountDetails(String title, String password, String day, String month, String year) {
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(By.id("id_gender1")).click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            driver.findElement(By.id("id_gender2")).click();
        }
        driver.findElement(By.id("password")).sendKeys(password);
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dayDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("days")));
        Select selectDay = new Select(dayDropdown);
        selectDay.selectByVisibleText(day);

        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("months")));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText(month);

        WebElement yearDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("years")));
        Select selectYear = new Select(yearDropdown);
        selectYear.selectByVisibleText(year);
    }

    @And("Selects checkbox Sign up for our newsletter!")
    public void selectsCheckboxSignUpForOurNewsletter() {
        WebElement signUpCheckbox = driver.findElement(By.cssSelector("input#newsletter"));
        if (!signUpCheckbox.isSelected()) {
            signUpCheckbox.click();
        }
    }

    @And("Selects checkbox Receive special offers from our partners!")
    public void selectsCheckboxReceiveSpecialOffersFromOurPartners() {
        WebElement offersCheckbox = driver.findElement(By.cssSelector("input#optin"));
        if (!offersCheckbox.isSelected()) {
            offersCheckbox.click();
        }
    }

    @And("Fills in address details: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void fillsInAddressDetails(String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipCode, String mobileNumber) {
        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys(lastName);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(address1);
        driver.findElement(By.id("address2")).sendKeys(address2);
        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText(country);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipCode);
        driver.findElement(By.id("mobile_number")).sendKeys(mobileNumber);
    }


    @And("Clicks on Create Account button")
    public void clicksOnCreateAccountButton() {
        WebElement createAccBtn = driver.findElement(By.cssSelector("button[data-qa='create-account']"));
        createAccBtn.click();
    }

    @Then("Verify ACCOUNT CREATED! message should be visible")
    public void verifyACCOUNTCREATEDMessageShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accCreateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='title text-center']/b[contains(text(),'Account Created!')]")
        ));
        assertTrue("Account Created heading is not visible", accCreateMsg.isDisplayed());
    }

    @When("User clicks on Continue button")
    public void userClicksOnContinueButton() {
        WebElement continueBtn = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
        continueBtn.click();
    }

    @Then("Logged in as Name should be visible")
    public void loggedInAsNameShouldBeVisible() {
        WebElement logInNameVisbl = driver.findElement(By.cssSelector(".fa.fa-user"));
        assertTrue("Home Page is not visible", logInNameVisbl.isDisplayed());
        
    }

    @When("User clicks on Delete Account button")
    public void userClicksOnDeleteAccountButton() {
        WebElement clickOnDelete = driver.findElement(By.cssSelector(".fa.fa-trash-o"));
        clickOnDelete.click();
    }

    @Then("Verify ACCOUNT DELETED! message should be visible")
    public void verifyACCOUNTDELETEDMessageShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accDeleteMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='title text-center']/b[contains(text(),'Account Deleted!')]")
        ));
        assertTrue("Account Created heading is not visible", accDeleteMsg.isDisplayed());


    }

    @And("user close the window")
    public void userCloseTheWindow() {
        driver.quit();
    }

    @When("User enter name and already registered email address")
    public void userEnterNameAndAlreadyRegisteredEmailAddress() {
            WebElement nameField = driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
            WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
            String alreadyRegisteredEmail = "komal@testmail.com";
            nameField.sendKeys("Komal Goel");
            emailField.sendKeys(alreadyRegisteredEmail);
    }

    @Then("Verify error Email Address already exist! is visible")
    public void verifyErrorEmailAddressAlreadyExistIsVisible() {
        WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Email Address already exist!')]"));
        assertTrue("Error message not displayed", errorMsg.isDisplayed());
    }

    @And("User clicks on Register or Login button")
    public void userClicksOnRegisterOrLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerLoginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='checkoutModal']//a[normalize-space()='Register / Login']")));
        registerLoginBtn.click();
    }
}
