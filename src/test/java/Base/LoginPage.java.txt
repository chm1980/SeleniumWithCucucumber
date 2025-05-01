package pages;

import Base.BaseClass;
import org.openqa.selenium.By;

public class LoginPage {

    private final By txtUsername = By.name("username");
    private final By txtPassword = By.name("password");
    private final By btnLogin = By.name("login");

    public void goToLoginPage() {
        BaseClass.driver.get("http://executeautomation.com/demosite/Login.html");
    }

    public void login(String username, String password) {
        BaseClass.driver.findElement(txtUsername).sendKeys(username);
        BaseClass.driver.findElement(txtPassword).sendKeys(password);
    }

    public void clickLogin() {
        BaseClass.driver.findElement(btnLogin).click();
    }

    public boolean isOnUserFormPage() {
        return BaseClass.driver.getTitle().contains("Execute Automation"); // ou outro critério
    }
}
