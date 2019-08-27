@CD_All_Feature
Feature: Carregamento de dados 

Background: 
	Given que realize login no New TPS Web 
	When acesso a pagina de carregamento de dados 
	
@CD_01 
Scenario: carregar dados D_Clientes com arquivo. 
	And selecionar arquivo D_Clientes.txt na tabela D_Clientes 
	And realizar pr�-visualiza��o de dados do arquivo D_Clientes.txt 
	And clicar em Proximo 
	And selecionar Carregar na pagina de upload 
	And voltar a pagina de carregamento de dados 
	And selecionar arquivo excel na tabela D_Clientes 
	And realizar pr�-visualiza��o de dados do arquivo D_Clientes excel
	And clicar em Proximo 
	And selecionar Carregar na pagina de upload 
	Then os arquivos devem entrar em processamento 
	And repetir passos com outras tabelas (Fornecedores, Produtos, Vendas Itens, Compras, Inventario Carga) 
	
#@CD_02 
#Scenario: Administrar visualiza��o de tabelas 
#	And clicar em Admin visualiza��o arquivos 
#	And selecionar tabelas n�o carreg�veis 
#	And aplicar para area de tabelas carregaveis 
#	And desfazer a aplica��o anterior 
#	And clicar em Salvar e Sair 
#	Then deve ser salvas as configura��es feitas 
#	
#@CD_03 
#Scenario: Apagar arquivos carregados das tabelas 
#	And clicar no bot�o de apagar arquivos da tabela D_Clientes 
#	And selecionar arquivo a ser apagado na 
#	And clicar em apagar 
#	And confirmar que deseja apagar arquivo 
#	Then deve retornar uma mensagem de finaliza��o de processo 
#	And repetir passos com outras tabelas (Produtos, Vendas Itens, Compras, Inventario Carga) 
#	
#@CD_04 
#Scenario: Alterar formato de tabelas - Adicionar/Excluir 
#	Given que realize login no New TPS Web 
#	When acessar a pagina de carregamento de dados 
#	And clicar no bot�o de alterar formato da tabela D_Clientes 
#	And selecionar coluna a ser adicionada com a descri��o 
#	And clicar em Adicionar 
#	Then deve ser adicionado na tabela 
#	And clicar no bot�o excluir da variavel desejada 
#	And confirmar exclus�o de variavel 
#	Then a variavel deve ser deletada da tabela 
#	And repetir passos na tabela D_Produtos 
#	And repetir passos na tabela D_Inventario_Carga 
#	And repetir passos na tabela D_Compras 
#	And repetir passos na tabela D_Vendas_Itens 
#	
	
	
	
	
	
	
		