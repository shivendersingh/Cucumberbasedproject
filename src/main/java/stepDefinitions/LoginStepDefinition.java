package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class LoginStepDefinition {
	static WebDriver driver;

	@Given("user is already on Login Page")
	public void user_is_already_on_Login_Page() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://qa-fake.herokuapp.com/");
		driver.manage().window().maximize();
	}

	@When("title of login page is QA Fake")
	public void title_of_login_page_is_QA_Fake() {
		String ExpectedTitle = "QA Fake";
		String ActualTitle = driver.getTitle();
		assertEquals(ExpectedTitle, ActualTitle);

	}

	@Then("^user enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_username_and_password(String username, String password) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@Then("user checked rember me")
	public void user_checked_rember_me() {
		String ExpectedTitle = "remember me";
		String ActualTitle = driver.findElement(By.xpath("//*[contains(text(),'remember me')]")).getText();
		assertEquals(ExpectedTitle, ActualTitle);
		driver.findElement(By.xpath("//*[@id=\"rememberme\"]")).click();
	}

	@Then("user clicks on login button")
	public void user_clicks_on_login_button() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/button")).click();
	}

	@Then("user get the secret string on home screen")
	public void user_get_the_secret_string_on_home_screen() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("VAlue"+driver.findElement(By.xpath("//*[@id=\"result\"]")).getText());
		Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"result\"]")).getText());
	}

	@Then("Close the browser")
	public void Close_the_browser() {
		driver.quit();
	}
}
