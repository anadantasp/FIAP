
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

