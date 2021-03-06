package br.ce.wcaquino.runners;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "br.ce.wcaquino.steps",
		tags = {"@funcionais"},
		plugin = {"pretty", "html:target/report-html", "json:target/report.json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)
public class RunnerFuncionalTest {

	@BeforeClass
	public static void reset() {
		
		System.setProperty("webdriver.chrome.driver", "/home/gustavo/eclipse-workspace/SeleniumDrivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://srbarriga.herokuapp.com");
		driver.findElement(By.id("email")).sendKeys("teste@teste.com.br");
		driver.findElement(By.name("senha")).sendKeys("senha");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.quit();
	}
}
