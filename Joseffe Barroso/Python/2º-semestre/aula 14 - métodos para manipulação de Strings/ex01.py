alunosVestibular = "Jose dos Santos,7,Sao Paulo;Sandra Silva,6.5,Sao Jose do Rio Preto;Augusto Soares,8,Sao Paulo;Vanderlei Azevedo,5.65,Santos;Vanessa Ferreira,9,Sao Paulo;Natan Cruz,10,Sao Paulo"


alunos = []
posicao = 0
while(posicao != -1):
    posicao = alunosVestibular.find(";")
    #print(posicao)
    if(posicao == -1):
         aluno = alunosVestibular[:alunosVestibular.__len__()]
    else:
        aluno = alunosVestibular[:posicao]
        alunosVestibular = alunosVestibular[posicao + 1:]
        #print(alunosVestibular)
        #print(aluno)
    alunos.append(aluno)


listaAlunos = []
for i in range(0, alunos.__len__(), 1):
    dadosAluno = []
    posicao = 0
    while(posicao != -1):
        posicao = alunos[i].find(",")
        if(posicao == -1):
             dado = alunos[i][:alunos[i].__len__()]
        else:
            dado = alunos[i][:posicao]
            alunos[i] = alunos[i][posicao + 1:]
        dadosAluno.append(dado)

    aluno = {"nome": dadosAluno[0], "nota": float(dadosAluno[1]), "cidade": dadosAluno[2]}
    if(aluno['nota'] >= 7):
        listaAlunos.append(aluno)

for aluno in listaAlunos:
        print(f"Nome: {aluno['nome']} | Nota: {aluno['nota']} | Cidade: {aluno['cidade']}")
        print("")


