package step_definitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import step_definitions.ActionStep;

public class CarregamentoStepDefinition {

	WebDriver driver;
	ActionStep act;

	@Given("que realize login no New TPS Web")
	public void is_logged_TPS() {
		act = new ActionStep();
		act.validate_login_TPS();
	}

	@When("acesso a pagina de carregamento de dados")
	public void data_loading_page() {
		act.access_carregamento_page();
	}

	@And("selecionar arquivo D_Clientes.txt na tabela D_Clientes")
	public void select_txt_file() {
		act.importFileTxt_cliente();
	}
	
	@And("realizar pré-visualização de dados do arquivo D_Clientes.txt")
	public void preVisualTxt() {
		act.preVisualData();
	}

	@And("clicar em Proximo")
	public void clickNext() {
		act.clickOnNext();
	}

	@And("selecionar Carregar na pagina de upload")
	public void selectUpload() {
		act.uploadData();
	}

	@And("voltar a pagina de carregamento de dados")
	public void back_to_carregamento_page() {
		act.close_update_page();
	}
	@And("selecionar arquivo excel na tabela D_Clientes")
	public void select_excel_file() {
		act.importFileExcel_cliente();
	}
	@And("realizar pré-visualização de dados do arquivo D_Clientes excel")
	public void preVisualExcel() {
		act.preVisualData();
	}
	@And("clicar em Proximo")
	public void clickNext2() {
		act.clickOnNext();
	}
	@And("selecionar Carregar na pagina de upload")
	public void selectUpload2() {
		act.uploadData();
	}
	@Then("os arquivos devem entrar em processamento")
	public void validate_status_processing() {
		act.data_is_processing();
	}
	@And("repetir passos com outras tabelas (Fornecedores, Produtos, Vendas Itens, Compras, Inventario Carga")
	public void repeat_steps_tables() {
		act.repeat_steps_each_table();
	}
}
