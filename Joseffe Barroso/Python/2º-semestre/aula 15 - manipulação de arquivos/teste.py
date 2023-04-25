lendoArquivo = open("C:/arquivoscheckpointpython/pedidos.txt", "r")
arquivoTotalPedidos = open("./total_pedidos.txt", "a")

dados = lendoArquivo.readlines()

totalProdutos = 0
nome = " "
srting = ""

somaTotal = 0
for dado in dados:
    if(dado.find(",") != -1):
        dadosProduto = dado.split(",") 
        total = float(dadosProduto[1]) * float(dadosProduto[2])
        somaTotal += total
    else:
        arquivoTotalPedidos.write(f"{dado} - RS {somaTotal}\n")
        
        
