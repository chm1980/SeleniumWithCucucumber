package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class Hook extends BaseUtil {

    private final BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.InitializeReport();
        base.features = base.extent.createTest("Nome das Features");
        base.scenarioDef = base.features.createNode(scenario.getName());

        // Verifica se o Chromium está instalado
        File chromium = new File("/usr/bin/chromium-browser");
        if (!chromium.exists()) {
            throw new RuntimeException("Chromium browser não encontrado em /usr/bin/chromium-browser. Verifique se está instalado.");
        }

        // Verifica se o ChromeDriver está instalado e tem permissão de execução
        File chromedriver = new File("/usr/bin/chromedriver");
        if (!chromedriver.exists()) {
            throw new RuntimeException("ChromeDriver não encontrado em /usr/bin/chromedriver.");
        }
        chromedriver.setExecutable(true); // Garante permissão de execução

        // Define o caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        System.setProperty("webdriver.chrome.verboseLogging", "true"); // Logs detalhados

        // Configurações para execução headless em ARM/Linux
        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromium.getAbsolutePath()); // Usa o caminho do Chromium
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");
        options.addArguments("--remote-allow-origins=*");

        try {
            base.Driver = new ChromeDriver(options);
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao iniciar o ChromeDriver. Detalhes: " + e.getMessage(), e);
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Cenário falhou: " + scenario.getName());
        }
        if (base.Driver != null) {
            base.Driver.quit();
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Executando step: " + scenario.getName());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) {
        // Pode ser usado para capturar screenshot/log por step
    }

    @AfterAll
    public static void TearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

