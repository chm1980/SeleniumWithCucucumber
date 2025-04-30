package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner for executing Cucumber tests with TestNG
 */

@CucumberOptions(
    features = {"src/test/java/features"},  // Caminho para os arquivos de feature
    glue = "steps",  // Pacote onde os steps definitions estão localizados
    plugin = {
        "pretty",  // Impressão de logs no console
        "json:target/cucumber.json",  // Relatório JSON para integração com o plugin de relatórios
        "html:target/cucumber-html-report"  // Relatório HTML para visualização rápida
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
