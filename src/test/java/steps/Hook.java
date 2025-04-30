package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Scenario;

public class Hook {

    public static WebDriver driver;

    @Before
    public void InitializeTest(Scenario scenario) {
        System.out.println("On test start");

        // Define o caminho correto do ChromeDriver instalado no Raspberry Pi
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Instancia o ChromeDriver
        try {
            driver = new ChromeDriver();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        System.out.println("On test " + (scenario.isFailed() ? "failure" : "success"));
        
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
