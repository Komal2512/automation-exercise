package StepDefs;

import com.Driver.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class LoginLogoutStepDef {


    public void dismissChromePasswordPopup() throws Exception {
        Robot robot = new Robot();
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


    @Then("User Login to your account should be visible")
    public void userLoginToYourAccountShouldBeVisible() {
        WebElement loginAcc = DriverHelper.driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));
        assertTrue("Login to your account section is not visible", loginAcc.isDisplayed());

    }

    @When("User enters invalid email {string} and password {string}")
    public void userEntersInvalidEmailAndPassword(String wrongEmail, String wrongPswd) {
        WebElement emailField = DriverHelper.driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        WebElement pswdField = DriverHelper.driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        emailField.sendKeys(wrongEmail);
        pswdField.sendKeys(wrongPswd);
    }

    @Then("Error message {string} should be visible")
    public void errorMessageShouldBeVisible(String expectedMsg) {
        WebElement errMsg = DriverHelper.driver.findElement(By.xpath("//*[contains(text(),'" +expectedMsg + "')]"));
        assertTrue("Error message not displayed", errMsg.isDisplayed());
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() throws Exception {
        WebElement logInBtn = DriverHelper.driver.findElement(By.cssSelector("button[data-qa='login-button']"));
        logInBtn.click();
        dismissChromePasswordPopup();
    }

    @When("User enters valid email {string} and password {string}")
    public void userEntersValidEmailAndPassword(String Email, String Pswd) {
        WebElement emailField = DriverHelper.driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        WebElement pswdField = DriverHelper.driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        emailField.sendKeys(Email);
        pswdField.sendKeys(Pswd);
    }

    @When("User clicks on Logout button")
    public void userClicksOnLogoutButton() {
        WebElement clickOnLogOut = DriverHelper.driver.findElement(By.cssSelector(".fa.fa-lock"));
        clickOnLogOut.click();
    }

    @Then("User should be redirected to login page")
    public void userShouldBeRedirectedToLoginPage() {
        WebElement loginAcc = DriverHelper.driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));
        assertTrue("Login to your account section is not visible", loginAcc.isDisplayed());
    }
}
