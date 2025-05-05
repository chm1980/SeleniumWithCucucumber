package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

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

        // Verificação de binário do Chromium para ARM
        if (!new File("/usr/bin/chromium-browser").exists() && !new File("/usr/bin/chrome").exists()) {
            throw new RuntimeException("Chromium/Chrome browser não encontrado. Certifique-se de que o navegador está instalado.");
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("/usr/bin/chromium-browser"); // Ajuste para Raspberry Pi se necessário
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--headless=new");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");

        try {
            base.Driver = new ChromeDriver(chromeOptions);
        } catch (WebDriverException e) {
            System.err.println("Erro ao iniciar o ChromeDriver: " + e.getMessage());
            throw new RuntimeException("Falha ao iniciar o ChromeDriver. Verifique compatibilidade e instalação do navegador.", e);
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        if (base.Driver != null) {
            base.Driver.quit();
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) {
        // Log por step, se necessário
    }

    @AfterAll
    public static void TearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

