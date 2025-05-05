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
        if (!new File("/usr/bin/chromium-browser").exists() && !new File("/usr/bin/chrome").exists())

