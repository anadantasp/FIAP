baseDados = "CJose dos Santos,42,Sao Paulo;CSandra Silva,36,Sao Jose do Rio Preto;CAugusto Soares,22,Sao Paulo;CVanderlei Azevedo,45,Santos;CVanessa Ferreira,27,Sao Paulo;PMouse,1,9.90;PTeclado,3,19.90;PMonitor,2,349.90;PHD SSD,2,199.90;PProcessador,1,350.00"

dados = []
clientes = []
produtos = []
posicao = 0

while(posicao != -1):
    posicao = baseDados.find(";")

    if(posicao == -1):
        dado = baseDados[:baseDados.__len__()] 
    else:
        dado = baseDados[:posicao]
        baseDados = baseDados[posicao + 1:]
    dados.append(dado)

#print(dados)

for dado in dados:
    if(dado.startswith("C")):
        clientes.append(dado)
    elif(dado.startswith("P")):
        produtos.append(dado)

#print(clientes)
#print(produtos)

listaClientes = []
listaProdutos = []

for cliente in clientes:
    dadosCliente = []
    posicao = 0

    while(posicao != -1):
        posicao = cliente.find(",")

        if(posicao == -1):
            dado = cliente[:cliente.__len__()]
        else:
            dado = cliente[:posicao]
            cliente = cliente[posicao + 1:]
        dadosCliente.append(dado)
    
    cli = {"nome": dadosCliente[0][1:], "idade": int(dadosCliente[1]), "cidade": dadosCliente[2]}
    listaClientes.append(cli)

for produto in produtos:
    dadosProduto = []
    posicao = 0

    while(posicao != -1):
        posicao = produto.find(",")

        if(posicao == -1):
            dado = produto[:produto.__len__()]
        else:
            dado = produto[:posicao]
            produto = produto[posicao + 1:]
        dadosProduto.append(dado)
    
    prod = {"nome": dadosProduto[0][1:], "quantidade": int(dadosProduto[1]), "preco": dadosProduto[2]}
    listaProdutos.append(prod)

print("Clientes: ")
print("")
for cliente in listaClientes:
        print(f"Nome: {cliente['nome']} | Idade: {cliente['idade']} | Cidade: {cliente['cidade']}")
        print("")


print("---------------------------------------------------------------------------------")
print("")
print("Produtos: ")
print("")
for produto in listaProdutos:
        print(f"Nome: {produto['nome']} | Quantidade em estoque: {produto['quantidade']} | Preço: R${produto['preco']}")
        print("")
    

