package step_definitions;

import step_definitions.ActionStep;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition {

	WebDriver driver;
	ActionStep act;

	@Given("user is already on Login Page$")
	public void user_already_on_login_page() {
		act = new ActionStep();
		act.set_up_New_TPS_Web();
	}

	@When("login title is Login NEW TPS WEB$")
	public void login_title_is_page() {
		act.verify_login_page_title();
	}

	@Then("user enters Username and Password$")
	public void enter_username_password() {
		act.insert_username_passoword();
	}

	@And("user enters image code$")
	public void enter_image_code() {
		act.image_code();
	}

	@And("user clicks on login button$")
	public void click_LoginButton() {
		act.login_button();
	}
	@Then("user selects data bases$")
	public void select_data_base() {
		act.data_base();
	}

	@And("user clicks on connect button$")
	public void click_ConnectButton() {
		act.connect_TPS();
	}

	@And("user is on home page$")
	public void validate_login_done() {
		act.verify_login();
		act.quit_process_browser();
	}

}
