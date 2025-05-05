package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver; // Import ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions; // Import ChromeOptions
import org.openqa.selenium.edge.EdgeDriver; // Esta importação já estava presente

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.scenarioDef = base.features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup(); // Agora será encontrado
        ChromeOptions chromeOptions = new ChromeOptions(); // Agora será encontrado
        chromeOptions.addArguments("--headless");
        base.Driver = new ChromeDriver(chromeOptions); // Agora será encontrado
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        base.Driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        //System.out.println("Before every step " + stepTestStep.getId());
    }
}
