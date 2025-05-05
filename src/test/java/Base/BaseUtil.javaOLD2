package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUtil {

    // Instância do WebDriver (estático para acesso global)
    public static WebDriver Driver;

    // Instância do ExtentReports para geração de relatórios
    public ExtentReports extent;

    // Instâncias de teste para relatório
    public static ExtentTest scenarioDef;
    public static ExtentTest features;

    // Caminho para salvar relatórios (dentro do diretório do projeto)
    public static String reportLocation = System.getProperty("user.dir") + "/reports";

    // Inicializa o WebDriver
    public void InitializeTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        Driver = new ChromeDriver(options);
        Driver.manage().window().maximize();
    }

    // Configura o ExtentReports
    public void InitializeReport() {
        extent = new ExtentReports();

        extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Browser", "Chrome");

        String reportPath = reportLocation + "/TestReport.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        extent.attachReporter(htmlReporter);
    }

    // Cria um relatório para um cenário específico
    public void CreateTestReport(String testName) {
        scenarioDef = extent.createTest(testName);
    }

    // Fecha o navegador
    public void CloseBrowser() {
        if (Driver != null) {
            Driver.quit();
        }
    }

    // Salva o relatório após os testes
    public void SaveReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

