package steps;

import Base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginSteps {

    LoginPage login = new LoginPage();

    @Before
    public void setUp() {
        BaseClass.initializeDriver();
    }

    @After
    public void tearDown() {
        BaseClass.quitDriver();
    }

    @Given("I navigate to the login page")
    public void i_navigate_to_the_login_page() {
        login.goToLoginPage();
    }

    @And("I enter the following for Login")
    public void i_enter_the_following_for_Login(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");
        login.login(username, password);
    }

    @And("I click login button")
    public void i_click_login_button() {
        login.clickLogin();
    }

    @Then("I should see the userform page")
    public void i_should_see_the_userform_page() {
        assertTrue("Userform page was not displayed!", login.isOnUserFormPage());
    }

    @Then("I should see the userform page wrongly")
    public void i_should_see_the_userform_page_wrongly() {
        fail("Forced failure for scenario demonstration.");
    }
}
