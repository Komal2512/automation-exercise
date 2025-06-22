package StepDefs;

import com.Driver.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static com.Driver.DriverHelper.driver;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductDetailsStepDef {

    public static String recommendedProductName = "";


    @When("User clicks on Products button")
    public void userClicksOnProductsButton() {
        WebElement productsButton = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
        productsButton.click();
    }

    @Then("User should be navigated to ALL PRODUCTS page successfully")
    public void userShouldBeNavigatedToALLPRODUCTSPageSuccessfully() {
        WebElement allProductPage = driver.findElement(By.xpath("//h2[contains(text(),'All Products')]"));
        assertTrue("All PRODUCTS page should not displayed", allProductPage.isDisplayed());
    }

    @And("products list should be visible")
    public void productsListShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productItem = driver.findElement(By.cssSelector(".features_items .col-sm-4"));
        assertTrue("Products list is not visible", productItem.isDisplayed());
    }

    @When("User clicks on View Product of the first product")
    public void userClicksOnViewProductOfTheFirstProduct() {
        WebElement viewProduct = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
        viewProduct.click();
    }

    @Then("User should be navigated to product detail page")
    public void userShouldBeNavigatedToProductDetailPage() {
        WebElement prductDetlPage = driver.findElement(By.cssSelector(".product-information"));
        assertTrue("Products detail page is not visible", prductDetlPage.isDisplayed());
    }

    @And("User product details like name, category, price, availability, condition, and brand should be visible")
    public void userProductDetailsLikeNameCategoryPriceAvailabilityConditionAndBrandShouldBeVisible() {

        WebElement name = driver.findElement(By.xpath("//div[@class='product-information']//h2"));
        WebElement category = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]"));
        WebElement price = driver.findElement(By.xpath("//div[@class='product-information']//span/span"));
        WebElement availability = driver.findElement(By.xpath("//div[@class='product-information']//p[.//b[contains(text(),'Availability')]]"));
        WebElement condition = driver.findElement(By.xpath("//div[@class='product-information']//p[.//b[contains(text(),'Condition')]]"));
        WebElement brand = driver.findElement(By.xpath("//div[@class='product-information']//p[.//b[contains(text(),'Brand')]]"));

        assertTrue("Product name is not visible", name.isDisplayed());
        assertTrue("Product category is not visible", category.isDisplayed());
        assertTrue("Product price is not visible", price.isDisplayed());
        assertTrue("Product availability is not visible", availability.isDisplayed());
        assertTrue("Product condition is not visible", condition.isDisplayed());
        assertTrue("Product brand is not visible", brand.isDisplayed());
    }

    @When("User enters {string} in the search input and clicks the search button")
    public void userEntersInTheSearchInputAndClicksTheSearchButton(String productName) {
        WebElement searchField = driver.findElement(By.cssSelector("input[id='search_product']"));
        searchField.clear();
        searchField.sendKeys(productName);
        WebElement searchButton = driver.findElement(By.cssSelector("button[id='submit_search']"));
        searchButton.click();
    }

    @Then("Verify SEARCHED PRODUCTS should be visible")
    public void verifySEARCHEDPRODUCTSShouldBeVisible() {
        WebElement searchProductsHdr = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]"));
        assertTrue("SEARCHED PRODUCTS section is not visible", searchProductsHdr.isDisplayed());
    }

    @And("Validate the all products related to {string} should be visible")
    public void validateTheAllProductsRelatedToShouldBeVisible(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".features_items .productinfo p")
        ));
        assertFalse("No products found on the page.", productElements.isEmpty());

        boolean foundMatch = false;

        for (WebElement product : productElements) {
            String productTitle = product.getText().toLowerCase();
            System.out.println("🔍 Product found: " + productTitle);

            if (productTitle.contains(productName.toLowerCase())) {
                foundMatch = true;
            } else {
                System.out.println("⚠️ Not matching: " + productTitle);
            }
        }

        assertTrue("No products matched the search keyword: " + productName, foundMatch);
    }

    @Then("Verify Searched products should be visible in the cart")
    public void verifySearchedProductsShouldBeVisibleInTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the cart page to be loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_info")));

        // Try different selector if needed
        List<WebElement> productNames = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart_description h4"))
        );

        // Assert the cart is not empty
        Assert.assertFalse("No products found in cart!", productNames.isEmpty());

        for (WebElement product : productNames) {
            String name = product.getText().trim();
            System.out.println("Product in cart: " + name);
            Assert.assertFalse("Empty product name!", name.isEmpty());
        }
    }

    @When("User navigates back to Cart page")
    public void userNavigatesBackToCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
        cartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_info")));
    }

    @Then("Validate previously added searched products should still be visible in the cart")
    public void validatePreviouslyAddedSearchedProductsShouldStillBeVisibleInTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.cssSelector("a[href='/view_cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_info")));
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart_description h4 a"))
        );
        Assert.assertFalse("No products visible in the cart", products.isEmpty());
        for (WebElement product : products) {
            System.out.println("Cart contains product: " + product.getText());
            Assert.assertFalse("Product name is empty", product.getText().isEmpty());
        }
    }


    @When("User adds one searched products to cart")
    public void userAddsOneSearchedProductsToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> products = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(".features_items .product-image-wrapper")));

        if (products.isEmpty()) {
            throw new AssertionError("No products found to add to cart.");
        }

        WebElement firstProduct = products.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProduct).perform();
        WebElement addToCartBtn = firstProduct.findElement(By.cssSelector(".overlay-content .add-to-cart"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        try {
            WebElement continueBtn = wait.until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector(".btn.btn-success.close-modal")));
            continueBtn.click();
        } catch (TimeoutException e) {
            System.out.println("Modal did not appear or Continue Shopping button not found.");
        }
    }

    @Then("Verify RECOMMENDED ITEMS section should be visible")
    public void verifyRECOMMENDEDITEMSSectionShouldBeVisible() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[(text()='recommended items')]")));
        assertTrue("Recommended section not displayed", section.isDisplayed());
    }

    @When("User clicks on Add To Cart button of a first recommended product")
    public void userClicksOnAddToCartButtonOfAFirstRecommendedProduct() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#recommended-item-carousel .item.active .productinfo.text-center p")));
        recommendedProductName = productElement.getText().trim().toLowerCase();
        System.out.println("Captured recommended product: " + recommendedProductName);
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#recommended-item-carousel .item.active .productinfo.text-center .add-to-cart")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
        WebElement viewCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/view_cart']")));
        viewCartBtn.click();
    }

//    @And("User clicks on View Cart button")
//    public void userClicksOnViewCartButton() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement viewCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/view_cart']")));
//        viewCartBtn.click();
//    }

    @And("User clicks on View Cart button")
    public void userClicksOnViewCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'modal-content')]")));

        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[contains(text(),'View Cart')]")));
        viewCart.click();
    }


    @Then("Validate the recommended product should be displayed in the cart page")
    public void validateTheRecommendedProductShouldBeDisplayedInTheCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_items")));
        List<WebElement> cartProducts = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector("#cart_items td.cart_description h4 a")));
        boolean productFound = false;
        System.out.println("Expected: " + recommendedProductName);
        System.out.println("---- Products Found in Cart ----");
        for (WebElement product : cartProducts) {
            String productName = product.getText().trim().toLowerCase();
            System.out.println("Found in cart: " + productName);
            if (productName.equalsIgnoreCase(recommendedProductName)) {
                productFound = true;
                break;
            }
        }
        Assert.assertTrue("Recommended product should be visible in the cart", productFound);
    }

    @When("User clicks on View Product button of any product")
    public void userClicksOnViewProductButtonOfAnyProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewProductBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".product-image-wrapper .choose a[href*='/product_details/']")));
        viewProductBtn.click();
    }

    @Then("Validate Write Your Review section should be visible")
    public void validateWriteYourReviewSectionShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElement reviewForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#review-form")));
        Assert.assertTrue("Write Your Review form is not visible", reviewForm.isDisplayed());
    }

    @When("User enters {string}, {string}, and {string}")
    public void userEntersReviewDetails(String name, String email, String reviewText) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("review")).sendKeys(reviewText);
    }

    @Then("Validate the success message Thank you for your review. should be visible")
    public void validateSuccessMessageForReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Thank you for your review.')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", successMessage);
        Assert.assertTrue("Success message not visible", successMessage.isDisplayed());
    }

    @When("User clicks Proceed To Checkout")
    public void userClicksProceedToCheckout() {
        WebElement checkoutButton = driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));
        checkoutButton.click();
    }

    @Then("Validate the Delivery address should match the registration address")
    public void validateTheDeliveryAddressShouldMatchTheRegistrationAddress() {
        WebElement deliveryAddress = driver.findElement(By.xpath("//ul[@id='address_delivery']"));
        String deliveryText = deliveryAddress.getText();
        String[] lines = deliveryText.split("\n", 2);
        String cleanedDeliveryText = lines.length > 1 ? lines[1].trim() : "";
        String expectedAddress = "Mrs. Komal Goel\nABC Ltd\nStreet 1\nApt 202\nNew Delhi Delhi 110001\nIndia\n9999999999";
        Assert.assertEquals("Delivery address does not match", expectedAddress.trim(), cleanedDeliveryText);
    }


    @And("Validate the billing address should match the registration address")
    public void validateTheBillingAddressShouldMatchTheRegistrationAddress() {
        WebElement billingAddress = driver.findElement(By.xpath("//ul[@id='address_invoice']"));
        String billingText = billingAddress.getText();
        String[] lines = billingText.split("\n", 2);
        String cleanedBillingText = lines.length > 1 ? lines[1].trim() : "";
        String expectedAddress = "Mrs. Komal Goel\nABC Ltd\nStreet 1\nApt 202\nNew Delhi Delhi 110001\nIndia\n9999999999";
        Assert.assertEquals("Billing address does not match", expectedAddress.trim(), cleanedBillingText);
    }


    @And("user adds the first product to the cart")
    public void userAddsTheFirstProductToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='product-overlay' or @class='product-image-wrapper'])[1]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(firstProduct).sendKeys(Keys.PAGE_DOWN).perform();
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Add to cart')])[1]")));
        addToCartButton.click();
    }

    @And("user chooses to Continue Shopping")
    public void userChoosesToContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Continue Shopping')]")));
        continueShoppingButton.click();
    }

    @And("user adds the second product to the cart")
    public void userAddsTheSecondProductToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement specificProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'productinfo')]//p[contains(text(),'Stylish Dress')]/ancestor::div[@class='product-overlay' or @class='product-image-wrapper']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(specificProduct).perform();
        WebElement addToCartButton = specificProduct.findElement(
                By.xpath(".//a[contains(text(),'Add to cart')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    @Then("Verify the Both products should be present in the cart")
    public void verifyTheBothProductsShouldBePresentInTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Cart')]")));
        cartLink.click();
        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//tr[td[@class='cart_description']]")));

        Assert.assertTrue("Less than 2 products in the cart", cartItems.size() >= 2);
    }


    @And("Validate the each product's price, quantity and total should be correctly displayed")
    public void validateTheEachProductSPriceQuantityAndTotalShouldBeCorrectlyDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//tr[td[@class='cart_price']]")));

        for (WebElement row : rows) {
            String priceText = row.findElement(By.xpath(".//td[@class='cart_price']")).getText().replaceAll("[^\\d.]", "");
            String quantityText = row.findElement(By.xpath(".//td[@class='cart_quantity']")).getText().trim();

            String totalText = row.findElement(By.xpath(".//td[@class='cart_total']")).getText().replaceAll("[^\\d.]", "");

            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);
            double expectedTotal = price * quantity;
            double actualTotal = Double.parseDouble(totalText);

            Assert.assertEquals("Mismatch in total for a product", expectedTotal, actualTotal, 0.01);
        }
    }

    @When("user sets the quantity to {string}")
    public void userSetsTheQuantityTo(String quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement qtyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='quantity' or @id='quantity']")));
        qtyInput.clear();
        qtyInput.sendKeys(quantity);
    }


    @And("user adds the product to the cart")
    public void userAddsTheProductToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@class,'cart')])")));
        addToCartBtn.click();
    }


    @Then("user cart should reflect the product with quantity {string}")
    public void userCartShouldReflectTheProductWithQuantity(String expectedQty) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Cart')]")));
        cartLink.click();
        WebElement qtyCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[@class='cart_quantity']")));

        String actualQty = qtyCell.getText().trim();

        Assert.assertEquals("Quantity in cart does not match", expectedQty, actualQty);
    }

    @Then("verify the cart page should be visible")
    public void verifyTheCartPageShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Shopping Cart')]")));

        Assert.assertTrue("Cart page is not visible", cartHeader.isDisplayed());
    }

    @When("user removes the product from the cart")
    public void userRemovesTheProductFromTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'cart_quantity_delete')]")));

        deleteButton.click();
    }

    @Then("Validate the product should no longer be present in the cart")
    public void validateTheProductShouldNoLongerBePresentInTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isEmpty = wait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tr[td[@class='cart_description']]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cart is empty') or contains(text(),'empty')]"))
        )) != null;

        Assert.assertTrue("Cart is not empty after removing the product", isEmpty);
    }
}
