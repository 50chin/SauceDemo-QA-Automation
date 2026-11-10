import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CartTest {

   @BeforeAll
   public static void setUp() {
      Configuration.browser = "chrome";
      Configuration.holdBrowserOpen = true;
   }

   public void loginAsStandardUser() {
      open("https://www.saucedemo.com/");
      $("#user-name").setValue("standard_user");
      $("#password").setValue("secret_sauce");
      $("#login-button").click();
   }

   @Test
   public void testAddToCart() {
      loginAsStandardUser();

      $(".shopping_cart_badge").shouldNot(exist);

      $$(".btn_inventory").first().click();

      $(".shopping_cart_badge").shouldHave(text("1"));

      $$(".inventory_item").first().$(".btn_inventory").shouldHave(text("Remove"));
   }

   @Test
   public void testRemoveFromCart() {
      loginAsStandardUser();

      $(".shopping_cart_badge").shouldNot(exist);

      $$(".btn_inventory").first().click();
      $(".shopping_cart_badge").shouldHave(text("1"));

      $$(".inventory_item").first().$(".btn_inventory").shouldHave(text("Remove"));

      $$(".btn_inventory").first().click();
      $(".shopping_cart_badge").shouldNot(exist);
      $$(".inventory_item").first().$(".btn_inventory").shouldHave(text("Add to cart"));
   }

   @Test
   public void testCheckoutProcess() {
      loginAsStandardUser();
      $(".shopping_cart_badge").shouldNot(exist);
      $$(".btn_inventory").first().click();
      $(".shopping_cart_badge").shouldHave(text("1"));

      $$(".inventory_item").first().$(".btn_inventory").shouldHave(text("Remove"));
      $(".shopping_cart_link").click();
      $("#checkout").click();

      $("#first-name").setValue("Ivan");
      $("#last-name").setValue("Ivanov");
      $("#postal-code").setValue("190999");
      $("#continue").click();
      $("#finish").click();

      $(".complete-header").shouldHave(text("Thank you for your order!"));
   }
}
