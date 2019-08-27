package step_definitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ParametersConfig;

public class ActionStep {

	WebDriver driver;
	ParametersConfig config = new ParametersConfig();
	String propertyBrowser = ParametersConfig.browser;
	String url = ParametersConfig.url;
	String username = ParametersConfig.user;
	String password = ParametersConfig.password;
	String code = ParametersConfig.image_code;
	String databases = ParametersConfig.input_data_bases;
	String name_databases = ParametersConfig.name_data_bases;
	String file_cliente_txt = ParametersConfig.file_d_cliente_text;
	String file_cliente_excel = ParametersConfig.file_d_cliente_excel;
	String file_fornecedor_txt = ParametersConfig.file_d_fornecedor_txt;
	String file_fornecedor_excel = ParametersConfig.file_d_fornecedor_excel;
	String file_compras_txt = ParametersConfig.file_d_compras_txt;
	String file_compras_excel = ParametersConfig.file_d_compras_excel;
	String file_vendas_txt = ParametersConfig.file_d_vendas_txt;
	String file_vendas_excel = ParametersConfig.file_d_vendas_excel;
	String file_produto_txt = ParametersConfig.file_d_produto_txt;
	String file_produto_excel = ParametersConfig.file_d_produto_excel;
	String file_inventario_txt = ParametersConfig.file_d_inventario_txt;
	String file_inventario_excel = ParametersConfig.file_d_inventario_excel;
	String inv_prod = ParametersConfig.d_inv_prod;
	String inv_data = ParametersConfig.d_inv_data;
	String prod_description = ParametersConfig.d_prod_description;
	String prod_cod = ParametersConfig.d_prod_cod;
	String ven_cod_cliente = ParametersConfig.d_ven_cod_cliente;
	String ven_num_fiscal = ParametersConfig.d_ven_num_fiscal;
	String comp_cod_produto = ParametersConfig.d_comp_cod_produto;
	String comp_cod_divisao = ParametersConfig.d_com_cod_divisao;
	String forn_cod_fornecedor = ParametersConfig.d_forn_cod_fornecedor;
	String forn_name_fornecedor = ParametersConfig.d_forn_name_fornecedor;
	String cli_cod_cliente = ParametersConfig.d_cli_cod_cliente;
	String cli_name_cliente = ParametersConfig.d_cli_name_cliente;

	// Setup NEW TPS WEB
	public void set_up_New_TPS_Web() {
		System.setProperty("webdriver.chrome.driver", propertyBrowser);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	// quit browser after scenario
	public void quit_process_browser() {
		driver.quit();
	}

	// login methods
	public void verify_login_page_title() {
		String title = driver.getTitle();
		Assert.assertEquals("NEW TPS WEB", title);
	}

	public void insert_username_passoword() {
		driver.findElement(By.id("txtusername")).sendKeys(username);
		driver.findElement(By.id("txtpassword")).sendKeys(password);
	}

	public void image_code() {
		driver.findElement(By.id("CaptchaInputText")).sendKeys(code);
	}

	public void login_button() {
		driver.findElement(By.xpath("//input[@id='login-submit']")).click();
		driver.findElement(By.cssSelector("body:nth-child(2)")).sendKeys(Keys.PAGE_DOWN);
	}

	public void data_base() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#s2id_ddldatabases > a > span.select2-chosen")));
		element.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#select2-drop > div > input")).sendKeys(databases);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void connect_TPS() {
		driver.findElement(By.id("connect-submit")).click();
	}

	public void verify_login() {
		WebElement not = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.elementToBeClickable(By.id("btnFullscreenNO")));
		not.click();
	}

	public void validate_login_TPS() {
		set_up_New_TPS_Web();
		insert_username_passoword();
		image_code();
		login_button();
		data_base();
		connect_TPS();
		verify_login();
		Assert.assertEquals(name_databases, driver.findElement(By.id("bduse")).getText());
	}

	// pagina carregamento methods
	
	public void access_carregamento_page() {
		driver.findElement(By.id("HeaderMenu_T0G1I1")).click();
		WebElement title = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.elementToBeClickable(By.id("lblTituloDadosOriginais")));
		String expected_result = title.getText();
		Assert.assertEquals("Carregamento", expected_result);
	}

	// Metodos de Clientes

	public void importFileTxt_cliente() {
		driver.findElement(
				By.xpath("//div[@id='divPartial_Principal']//div[1]//table[1]//tbody[1]//tr[2]//td[2]//input[2]"))
				.click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_cliente_txt);
	}

	public void importFileExcel_cliente() {
		driver.findElement(
				By.xpath("//div[@id='divPartial_Principal']//div[1]//table[1]//tbody[1]//tr[2]//td[2]//input[2]"))
				.click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).sendKeys(file_cliente_excel);
	}

	public void verify_data_on_cliente_file() {

		String columm_cod_cliente = driver.findElement(By.xpath("//th[contains(text(),'Código do Cliente')]"))
				.getText();
		Assert.assertTrue(columm_cod_cliente, true);

		String columm_nome_cliente = driver.findElement(By.xpath("//th[contains(text(),'Nome do Cliente')]")).getText();
		Assert.assertTrue(columm_nome_cliente, true);

		WebElement cod_cliente = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[1]")));
		String expected_Codcliente = cod_cliente.getText();
		Assert.assertEquals(cli_cod_cliente, expected_Codcliente);

		WebElement name_cliente = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[2]")));
		String expected_NameCliente = name_cliente.getText();
		Assert.assertEquals(cli_name_cliente, expected_NameCliente);

		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();
	}

	// Metodos de Fornecedor

	public void repeatWith_Data_Fornecedor() {
		driver.findElement(By.xpath("//div[@class='container']//div[2]//table[1]//tbody[1]//tr[2]//td[2]//input[2]"))
				.click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_fornecedor_txt); // selecionar
																														// arquivo
																														// txt
		driver.findElement(By.xpath("//table[@id='tblArquivo_D_Fornecedores']//td[1]//input[1]")).click(); // visualizar
																											// dados do
																											// arquivo
		preVisualData();
		verify_data_on_fornecedor_file();
		clickOnNext();
		uploadData();
		driver.findElement(By.xpath("//div[@class='container']//div[2]//table[1]//tbody[1]//tr[2]//td[2]//input[2]"))
				.click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]"))
				.sendKeys(file_fornecedor_excel); // selecionar arquivo excel
		driver.findElement(By.xpath("//table[@id='tblArquivo_D_Fornecedores']//td[1]//input[1]")).click(); // visualizar
																											// dados do
																											// arquivo
		preVisualData();
		verify_data_on_fornecedor_file();
		clickOnNext();
		uploadData();
	}

	public void verify_data_on_fornecedor_file() {

		String columm_cod_fornecedor = driver.findElement(By.xpath("//th[contains(text(),'Código do Fornecedor')]"))
				.getText();
		Assert.assertTrue(columm_cod_fornecedor, true);

		String columm_nome_fornecedor = driver.findElement(By.xpath("//th[contains(text(),'Nome Fornecedor')]"))
				.getText();
		Assert.assertTrue(columm_nome_fornecedor, true);

		WebElement cod_fornecedor = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[1]")));
		String expected_cod_fornecedor = cod_fornecedor.getText();
		Assert.assertEquals(forn_cod_fornecedor, expected_cod_fornecedor);

		WebElement name_fornecedor = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[2]")));
		String expected_name_fornecedor = name_fornecedor.getText();
		Assert.assertEquals(forn_name_fornecedor, expected_name_fornecedor);

		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();

	}

	// Metodos de Compras

	public void repeatWith_Data_Compras() {
		driver.findElement(By.xpath("//div[5]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_compras_txt); // selecionar
																														// arquivo
																														// txt
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_compras_file();
		clickOnNext();
		uploadData();
		driver.findElement(By.xpath("//div[5]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).sendKeys(file_compras_excel); // selecionar
																														// arquivo
																														// excel
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_compras_file();
		clickOnNext();
		uploadData();
	}

	public void verify_data_on_compras_file() {

		String columm_cod_prod_compras = driver.findElement(By.xpath("//th[contains(text(),'Código do Produto')]"))
				.getText();
		Assert.assertTrue(columm_cod_prod_compras, true);

		String columm_cod_div_compras = driver.findElement(By.xpath("//th[contains(text(),'Código da Divisão')]"))
				.getText();
		Assert.assertTrue(columm_cod_div_compras, true);

		WebElement cod_produto_compras = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[1]")));
		String expected_compras = cod_produto_compras.getText();
		Assert.assertEquals(comp_cod_produto, expected_compras);
		WebElement cod_divisao_compras = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("body.dx-adaptive-tablet.dx-adaptive-landscape.dx-adaptive.modal-open:nth-child(2) "
						+ "table.dxsplControl_MetropolisBlue:nth-child(3) td.dxsplPane_MetropolisBlue.mainContentPane "
						+ "div.dxsplLCC div.container:nth-child(5) table.table.table-bordered div.modal.fade.in:nth-child(4) "
						+ "div.modal-dialog div.modal-content div.modal-body div:nth-child(2) table.table.table-bordered tbody:nth-child(2) "
						+ "tr:nth-child(1) > td.ColunaGenericaMedia:nth-child(2)")));
		String expected_compras_2 = cod_divisao_compras.getText();
		Assert.assertEquals(comp_cod_divisao, expected_compras_2);
		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();

	}

	// Metodos de Vendas

	public void repeatWith_Data_Vendas() {
		driver.findElement(By.xpath("//div[6]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_vendas_txt); // selecionar
																													// arquivo
																													// txt
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_vendas_file();
		clickOnNext();
		uploadData();
		driver.findElement(By.xpath("//div[6]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).sendKeys(file_vendas_excel); // selecionar
																														// arquivo
																														// excel
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_vendas_file();
		clickOnNext();
		uploadData();

	}

	public void verify_data_on_vendas_file() {

		String columm_cod_cliente_vendas = driver.findElement(By.xpath("//th[contains(text(),'Código do Cliente')]"))
				.getText();
		Assert.assertTrue(columm_cod_cliente_vendas, true);
		String coumm_num_fiscal = driver.findElement(By.xpath("//th[contains(text(),'Número Nota Fiscal')]")).getText();
		Assert.assertTrue(coumm_num_fiscal, true);

		WebElement cod_cliente_venda = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
						"body.modal-open.dx-adaptive-tablet.dx-adaptive-landscape.dx-adaptive:nth-child(2) table.dxsplControl_MetropolisBlue:nth-child(3) "
								+ "td.dxsplPane_MetropolisBlue.mainContentPane div.dxsplLCC div.container:nth-child(5) "
								+ "table.table.table-bordered div.modal.fade.in:nth-child(4) div.modal-dialog div.modal-content "
								+ "div.modal-body div:nth-child(2) table.table.table-bordered tbody:nth-child(2) tr:nth-child(1) > "
								+ "td.ColunaGenericaMedia:nth-child(1)")));
		String expected_venda = cod_cliente_venda.getText();
		Assert.assertEquals(ven_cod_cliente, expected_venda);

		WebElement num_nota_fiscal = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("body.modal-open.dx-adaptive-tablet.dx-adaptive-landscape.dx-adaptive:nth-child(2) "
						+ "table.dxsplControl_MetropolisBlue:nth-child(3) td.dxsplPane_MetropolisBlue.mainContentPane div.dxsplLCC "
						+ "div.container:nth-child(5) table.table.table-bordered div.modal.fade.in:nth-child(4) "
						+ "div.modal-dialog div.modal-content div.modal-body div:nth-child(2) "
						+ "table.table.table-bordered tbody:nth-child(2) tr:nth-child(1) > td.ColunaGenericaMedia:nth-child(2)")));
		String expected_num_fiscal = num_nota_fiscal.getText();
		Assert.assertEquals(ven_num_fiscal, expected_num_fiscal);

		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();
	}

	// Metodos de Produtos

	public void repeatWith_Data_Produtos() {
		driver.findElement(By.xpath("//div[3]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_produto_txt); // selecionar
																														// arquivo
																														// txt
		driver.findElement(By.xpath("//div[@id='divPartial_Principal']//td//td[1]//input[1]")).click(); // visualizar
																										// dados do
																										// arquivo
		preVisualData();
		verify_data_on_produtos_file();
		clickOnNext();
		uploadData();
		driver.findElement(By.xpath("//div[3]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).sendKeys(file_produto_excel); // selecionar
																														// arquivo
																														// excel
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_produtos_file();
		clickOnNext();
		uploadData();
	}

	public void verify_data_on_produtos_file() {

		String columm_cod_produto = driver.findElement(By.xpath("//th[contains(text(),'Código do Produto')]"))
				.getText();
		Assert.assertTrue(columm_cod_produto, true);

		String columm_desc_produto = driver.findElement(By.xpath("//th[contains(text(),'Descrição do Produto')]"))
				.getText();
		Assert.assertTrue(columm_desc_produto, true);

		WebElement cod_produto = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[1]")));
		String expected_cod_produto = cod_produto.getText();
		Assert.assertEquals(prod_cod, expected_cod_produto);

		WebElement descricao_produto = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[2]\r\n" + "")));
		String expected_desc_produto = descricao_produto.getText();
		Assert.assertEquals(prod_description, expected_desc_produto);
		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();
	}

	// Metodos de Inventario

	public void repeatWith_Data_Inventario() {
		driver.findElement(By.xpath("//div[4]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[1]")).sendKeys(file_inventario_txt); // selecionar
																														// arquivo
																														// txt
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_inventarios_file();
		clickOnNext();
		uploadData();
		driver.findElement(By.xpath("//div[4]//table[1]//tbody[1]//tr[2]//td[2]//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]")).click();
		driver.findElement(By.xpath("//div[@id='divPartial_ModalPrincipal']//input[2]"))
				.sendKeys(file_inventario_excel); // selecionar arquivo excel
		driver.findElement(By.xpath("//td[@id='MainSplitter_1']//td//td[1]//input[1]")).click(); // visualizar dados do
																									// arquivo
		preVisualData();
		verify_data_on_inventarios_file();
		clickOnNext();
		uploadData();
	}

	public void verify_data_on_inventarios_file() {

		String columm_cod_prod_inventario = driver.findElement(By.xpath("//th[contains(text(),'Código do Produto')]"))
				.getText();
		Assert.assertTrue(columm_cod_prod_inventario, true);

		String columm_data_inventario = driver.findElement(By.xpath("//th[contains(text(),'Data Inventário')]"))
				.getText();
		Assert.assertTrue(columm_data_inventario, true);

		WebElement inventario_produto = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[1]")));
		String expected_inventario = inventario_produto.getText();
		Assert.assertEquals(inv_prod, expected_inventario);
		WebElement inventario_data = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@id='divPartial_ModalPrincipal']//div//tr[1]//td[2]")));
		String expected_inventario_2 = inventario_data.getText();
		Assert.assertEquals(inv_data, expected_inventario_2); // formato data
		driver.findElement(By.id("btnFecharEnviarArquivoCarga")).click();
	}

	// OUTROS

	public void preVisualData() {
		driver.findElement(By.xpath("//button[contains(text(),'Visualizar')]")).click();
	}

	public void clickOnNext() {
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default pull-right')]")).click();
	}

	public void uploadData() {
		driver.findElement(By.xpath("//button[contains(text(),'Carregar')]")).click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		WebElement status = driver.findElement(By.cssSelector(
				"body.dx-adaptive-tablet.dx-adaptive-landscape.dx-adaptive.modal-open:nth-child(2) table.dxsplControl_MetropolisBlue:nth-child(3) td.dxsplPane_MetropolisBlue.mainContentPane div.dxsplLCC div.container:nth-child(5) table.table.table-bordered div.modal.fade.in:nth-child(4) div.modal-dialog div.modal-content div.modal-body div:nth-child(2) table.table.table-bordered tbody:nth-child(2) tr.arquivoCarregado:nth-child(1) > td.ColunaGenerica.Status:nth-child(7)"));
		String element_processing = status.getText();
		Assert.assertEquals("Aguardando Processamento", element_processing);
		close_update_page();
	}

	public void data_is_processing() {
		// TODO Auto-generated method stub

	}

	public void page_container_carregamento_down() {
		driver.findElement(By.cssSelector("")).sendKeys(Keys.PAGE_DOWN);
	}

	public void repeat_steps_each_table() {
		repeatWith_Data_Fornecedor();
		repeatWith_Data_Compras();
		repeatWith_Data_Vendas();
		repeatWith_Data_Produtos();
		repeatWith_Data_Inventario();
	}

	public void close_update_page() {
		driver.findElement(By.xpath("//button[@id='btnControleUploadFechar']")).click();
	}
}
