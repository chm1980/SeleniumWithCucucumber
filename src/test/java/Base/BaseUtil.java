package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUtil {

    // Instância do WebDriver
    public static WebDriver Driver;

    // Instância do ExtentReports para gerar relatórios
    public ExtentReports extent;

    // Instâncias do ExtentTest para cenário e feature
    public static ExtentTest scenarioDef;
    public static ExtentTest features;

    // Localização do relatório
    public static String reportLocation = "/path/to/your/report/directory";

    // Método para inicializar o WebDriver
    public void InitializeTest() {
        // Gerenciar a versão do ChromeDriver automaticamente
        WebDriverManager.chromedriver().setup();

        // Configurações do ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Executar no modo headless (sem interface gráfica)
        options.addArguments("--no-sandbox");  // Para o Chromium rodar sem problemas em containers
        options.addArguments("--disable-dev-shm-usage");  // Evitar problemas em ambientes com recursos limitados

        // Inicializar o WebDriver
        Driver = new ChromeDriver(options);
        Driver.manage().window().maximize();  // Maximizar a janela
    }

    // Método para configurar o ExtentReports
    public void InitializeReport() {
        // Criar um novo relatório Extent
        extent = new ExtentReports();
        // Configurar o local de saída do relatório
        extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Browser", "Chrome");

        // Configuração do local do relatório, usando a variável reportLocation
        String reportPath = reportLocation + "/TestReport.html";
        com.aventstack.extentreports.reporter.ExtentHtmlReporter htmlReporter = new com.aventstack.extentreports.reporter.ExtentHtmlReporter(reportPath);
        extent.attachReporter(htmlReporter);
    }

    // Método para gerar relatório de cada cenário
    public void CreateTestReport(String testName) {
        scenarioDef = extent.createTest(testName);
    }

    // Método para fechar o WebDriver
    public void CloseBrowser() {
        if (Driver != null) {
            Driver.quit();  // Fecha o navegador
        }
    }

    // Método para salvar o relatório após a execução
    public void SaveReport() {
        extent.flush();  // Salva os resultados do ExtentReport
    }
}
