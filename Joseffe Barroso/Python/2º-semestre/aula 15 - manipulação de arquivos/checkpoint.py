
resposta = "S"

pedidos = []

while(resposta == "S"):
    produtos = []
    print("--------- CADASTRO DE PEDIDOS ---------")
    nomeCliente = input("Digite o nome do cliente: ")
    quantidadeProdutos = int(input("Informe a quantidade de produtos no pedido: "))

    for i in range(0, quantidadeProdutos, 1):
        nomeProduto = input("Digite o nome do produto: ")
        quantidade = input("Digite a quantidade: ")
        precoUnitario = input("Digite o preço unitário do produto: ")

        produto = {"nome": nomeProduto, "quantidade": quantidade, "preco": precoUnitario}

        produtos.append(produto)

    cliente = {"nome": nomeCliente, "produtos": produtos}

    pedidos.append(cliente)

    resposta = input("Deseja registrar um novo pedido?(S/N)").upper()

arquivo = open("C:/arquivoscheckpointpython/pedidos.txt", "a")

for pedido in pedidos:
    arquivo.write(f"{pedido['nome']}\n")
    for produto in pedido['produtos']:
        arquivo.write(f"{produto['nome']},{produto['quantidade']},{produto['preco']}\n")
    print("")

lendoArquivo = open("C:/arquivoscheckpointpython/pedidos.txt", "r")
arquivoTotalPedidos = open("C:/arquivoscheckpointpython/total_pedidos.txt", "a")

totalProdutos = 0
nomeCliente = ""

dados = lendoArquivo.readlines()

for dado in dados:
    if(dado.find(",") == -1):
        if(nomeCliente == ""):
            nomeCliente = dado   
        elif(nomeCliente != ""):
            arquivoTotalPedidos.write(f"{nomeCliente} - R$ {totalProdutos}")
            nomeCliente = dado
            totalProdutos = 0  
    else:
        infoProdutos = dado.split(",")
        total = float(infoProdutos[1]) * float(infoProdutos[2])
        totalProdutos = totalProdutos + total




    
