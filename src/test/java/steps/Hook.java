package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager; // Importação do WebDriverManager
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

        // Certifique-se de que o chromedriver tenha permissões de execução
        File chromedriver = new File("/usr/bin/chromedriver");
        if (!chromedriver.exists()) {
            throw new RuntimeException("ChromeDriver não encontrado. Certifique-se de que o ChromeDriver está instalado corretamente.");
        }

        chromedriver.setExecutable(true); // Garante que o ChromeDriver tenha permissão de execução

        // Inicializando o WebDriverManager e configurando a versão do ChromeDriver
        WebDriverManager.chromedriver().setup(); // Inicializa o WebDriverManager

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("/usr/bin/chromium-browser"); // Ajuste para Raspberry Pi se necessário
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920x1080");  // Tamanho da janela para modo headless
        chromeOptions.addArguments("--remote-allow-origins=*");

        try {
            base.Driver = new ChromeDriver(chromeOptions);
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao iniciar o ChromeDriver. Detalhes: " + e.getMessage(), e);
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        if (base.Driver != null) {
            base.Driver.quit

