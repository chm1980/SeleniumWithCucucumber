package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Construtor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método que clica no botão de login
    public void ClickLogin() {
        driver.findElement(By.name("Login")).click();
    }

    // Método que preenche usuário e senha e submete
    public void Login(String username, String password) {
        driver.findElement(By.name("UserName")).sendKeys(username);
        driver.findElement(By.name("Password")).sendKeys(password);
        ClickLogin(); // reutiliza o método acima
    }
}

