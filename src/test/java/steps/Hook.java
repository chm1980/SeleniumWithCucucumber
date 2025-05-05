package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.InitializeReport();
        base.features = base.extent.createTest("Nome das Features");
        base.scenarioDef = base.features.createNode(scenario.getName());

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--headless=new"); // Headless para ambientes sem display
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");

        base.Driver = new ChromeDriver(chromeOptions);
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        if (base.Driver != null) {
            base.Driver.quit();
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        // Optional logging or reporting per step
    }

    @AfterAll
    public static void TearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

