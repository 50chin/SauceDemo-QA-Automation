import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;



public class LoginTest {

   @BeforeAll
   public static void setUp() {
      Configuration.browser = "chrome";
      Configuration.holdBrowserOpen = true;
   }

   @Test
   public void testSuccessfulLogin() {
      open("https://www.saucedemo.com/");

      $("#user-name").setValue("standard_user");
      $("#password").setValue("secret_sauce");
      $("#login-button").click();

      $(".title").shouldHave(text("Products"));
   }

   @Test
   public void testInvalidLogin() {
      open("https://www.saucedemo.com/");

      $("#user-name").setValue("standard_user");
      $("#password").setValue("wrong_password");
      $("#login-button").click();

      $("h3[data-test=\"error\"]").shouldHave(text("Epic sadface: Username and password do not match any user in this service"));
   }
}


