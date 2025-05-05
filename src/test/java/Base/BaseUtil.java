package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // Import correto para v5
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUtil {

    public static WebDriver Driver;
    public static ExtentReports extent;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    public static String reportLocation = System.getProperty("user.dir") + "/reports";

    public void InitializeTest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        Driver = new ChromeDriver(options);
        Driver.manage().window().maximize();
    }

    public void InitializeReport() {
        extent = new ExtentReports();
        extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Browser", "Chrome");
        String reportPath = reportLocation + "/TestReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath); // Inicialização correta para v5
        extent.attachReporter(sparkReporter);
        // Inicialize 'features' aqui, logo após inicializar 'extent'
        features = extent.createTest("Nome das Features");
    }

    public void CreateTestReport(String testName) {
        scenarioDef = extent.createTest(testName);
    }

    public void CloseBrowser() {
        if (Driver != null) {
            Driver.quit();
        }
    }

    public void SaveReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
