package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Página de login.
 */
public class LoginPage {

    private WebDriver driver;
    
    // Espera explícita para elementos
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "UserName")
    public WebElement txtUserName;

    @FindBy(how = How.NAME, using = "Password")
    public WebElement txtPassword;

    @FindBy(how = How.NAME, using = "Login")
    public WebElement btnLogin;

    /**
     * Preenche os campos de login.
     */
    public void login(String userName, String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtUserName)).sendKeys(userName);
            wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(password);
        } catch (Exception e) {
            System.out.println("Erro ao preencher os campos de login: " + e.getMessage());
        }
    }

    /**
     * Clica no botão de login.
     */
    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
        } catch (Exception e) {
            System.out.println("Erro ao clicar no botão de login: " + e.getMessage());
        }
    }
}

