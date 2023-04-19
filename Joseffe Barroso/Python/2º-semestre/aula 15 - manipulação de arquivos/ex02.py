arquivo = open("C:/Users/anaca/arquivo_python.txt", "r")
arquivo_total_produtos = open("C:/Users/anaca/total_produtos.txt", "w")

produtos = arquivo.readlines()

for p in produtos:
    infoProdutos = p.split(" ")
    total = int(infoProdutos[4]) * float(infoProdutos[7][2:])
    arquivo_total_produtos.write(f"Produto: {infoProdutos[1]} | Total: R${total}\n")
