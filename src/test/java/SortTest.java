import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class SortTest {

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
   public void testSortByPriceLowToHigh() {
      loginAsStandardUser();
      $(".product_sort_container").selectOption("Price (low to high)");


      List<Double> actualPrices = $$(".inventory_item_price").texts()
              .stream()
              .map(text -> text.replaceAll("[^0-9.]", ""))
              .map(Double::parseDouble)
              .toList();

      List<Double> expectedPrices = new ArrayList<>(actualPrices);
      Collections.sort(expectedPrices);

      Assertions.assertEquals(expectedPrices, actualPrices, "Цены на сайте не отсортированы по возрастанию!");
   }

   @Test
   public void testSortByPriceHighToLow() {
      loginAsStandardUser();
      $(".product_sort_container").selectOption("Price (high to low)");

      List<Double> actualPrices = $$(".inventory_item_price").texts()
              .stream()
              .map(text -> text.replaceAll("[^0-9.]", ""))
              .map(Double::parseDouble)
              .toList();

      List<Double> expectedPrices = new ArrayList<>(actualPrices);
      expectedPrices.sort(Collections.reverseOrder());

      Assertions.assertEquals(expectedPrices, actualPrices, "Цены на сайте не отсортированы по !");

   }
}
