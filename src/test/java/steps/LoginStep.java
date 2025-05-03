package steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import pages.LoginPage;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

/**
 * Created by Karthik on 31/01/2019.
 */
public class LoginStep extends BaseUtil {

    private BaseUtil base;

    public LoginStep(BaseUtil base) {
        this.base = base;
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public User convert(Map<String, String> entry) {
        return new User(
                entry.get("username"),
                entry.get("password").concat("$$$$$")
        );
    }

    @Then("^I should see the userform page$")
    public void iShouldSeeTheUserformPage() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the userform page");
        Assert.assertEquals(base.Driver.findElement(By.id("Initial")).isDisplayed(), true, "Its not displayed");
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Given"), "I navigate to the login page");
        base.Driver.navigate().to("http://www.executeautomation.com/demosite/Login.html");
    }

    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"), "I click login button");
        LoginPage page = new LoginPage(base.Driver);
        page.ClickLogin();
    }

    @And("^I enter the following for Login$")
    public void iEnterTheFollowingForLogin(List<User> table) throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"), "I enter the following for login");
        LoginPage page = new LoginPage(base.Driver);
        page.Login(table.get(0).username, table.get(0).password);
    }

    @And("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterUsernameAndPassword(String userName, String password) throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"), "I enter username and password");
        System.out.println("UserName is : " + userName);
        System.out.println("Password is : " + password);
    }

    @Then("^I should see the userform page wrongly$")
    public void iShouldSeeTheUserformPageWrongly() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the userform page wrongly");
        // Código comentado de propósito
    }

    public class User {
        public String username;
        public String password;

        public User(String userName, String passWord) {
            username = userName;
            password = passWord;
        }
    }
}

