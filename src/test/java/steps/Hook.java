package steps;

import Base.BaseUtil;
import io.cucumber.java.*;

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.InitializeReport(); // Inicialize o relatório AQUI
        base.features = base.extent.createTest("Nome das Features"); // Inicialize 'features' aqui
        base.scenarioDef = base.features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        base.Driver = new ChromeDriver(chromeOptions);
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

    @AfterAll
    public static void TearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
