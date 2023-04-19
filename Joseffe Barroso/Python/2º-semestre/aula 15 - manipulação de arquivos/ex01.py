listaCompras = []

print("---------- CADASTRO DE PRODUTOS ----------")
for i in range(0,3,1):
    nome = input("Digite o nome do produto: ")
    quantidade = int(input("Digite a quantidade de produtos: "))
    preco = float(input("Digite o preco do produto: "))

    produto = {"nome": nome, "quantidade": quantidade, "preco": preco}

    listaCompras.append(produto)

arquivo = open("C:/Users/anaca/arquivo_python.txt", "a")

for produto in listaCompras:
        arquivo.write(f"Nome: {produto['nome']} | Quantidade: {produto['quantidade']} | Preço: R${produto['preco']}\n")
