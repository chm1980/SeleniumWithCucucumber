package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        System.out.println("On test start");

        base.scenarioDef = base.features.createNode(scenario.getName());

        try {
            // Setup do WebDriver com Chrome headless (necessário para Raspberry Pi ou servidores sem GUI)
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");

            base.Driver = new ChromeDriver(options);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o WebDriver: " + e.getMessage());
            e.printStackTrace();
            base.Driver = null;
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        System.out.println("On test failure");

        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            // Aqui você pode adicionar lógica para captura de screenshot
        }

        System.out.println("Closing the browser");

        if (base.Driver != null) {
            base.Driver.quit();
        } else {
            System.out.println("Driver was not initialized, skipping quit()");
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before step: " + scenario.getName());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) {
        System.out.println("After step: " + scenario.getName());
    }
}
