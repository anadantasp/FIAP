//EXERCÍCIO 1

const alunos = [
    {curso: "Análise e Desenvolvimento de Sistemas",
    aluno: "Ana",
    professor: "wellington cidade",
    turma: "1TDSPR",
    periodo: "noturno"},
    {curso: "Editoração",
    aluno: "Diego",
    professor: "Marcelo",
    turma: "1BASP",
    periodo: "diurno"},
    {curso: "Gatronomia",
    aluno: "João",
    professor: "André",
    turma: "2PFMP",
    periodo: "vespertino"},
]


function telaAlunoEstudando(alunos){
    document.write(`<br><br>LISTA DE ALUNOS<br><br>`)
    alunos.forEach(element => document.write(`Curso: ${element.curso}<br>
    Aluno: ${element.aluno}<br>
    Professor: ${element.professor}<br>
    Turma: ${element.turma}<br>
    Período: ${element.periodo}<br><br>`))
}

telaAlunoEstudando(alunos)


const aluno = alunos.find(aluno => aluno.aluno === "Diego")
document.write(`O aluno buscado foi: ${aluno.aluno} que cursa ${aluno.curso} com o professor ${aluno.professor}
da turma ${aluno.turma} no perído ${aluno.periodo}<br><br>`)


//EXERCÍCIO 2

const funcionarios = [
    {
        nome: "Ana",
        idade: 26,
        sexo: "F",
        cargo: "Desenvolvedora",
        salario: 3500.50,
        descontos: 300.50,
        dataAdmissao: 2020,
        dataDemissao: 2023
    },
    {
        nome: "Diego",
        idade: 31,
        sexo: "M",
        cargo: "Escritor",
        salario: 5500.50,
        descontos: 257.60,
        dataAdmissao: 2009,
        dataDemissao: 2016
    },
    {
        nome: "Manuela",
        idade: 57,
        sexo: "F",
        cargo: "Veterinaria",
        salario: 6500,
        descontos: 710.20,
        dataAdmissao: 1996,
        dataDemissao: 2020
    },
    {
        nome: "Manuel",
        idade: 36,
        sexo: "M",
        cargo: "Designer",
        salario: 4500.50,
        descontos: 250.70,
        dataAdmissao: 2005,
        dataDemissao: 2015
    },
    {
        nome: "Olivia",
        idade: 33,
        sexo: "F",
        cargo: "Advogada",
        salario: 13500.50,
        descontos: 620.50,
        dataAdmissao: 2000,
        dataDemissao: 2014
    },
]

const funcionariosAdmissao = funcionarios.filter(funcionario => (funcionario.dataAdmissao >= 2000 && funcionario.dataAdmissao <= 2010));


function listaFuncionariosAdmissao(funcionariosAdmissao){
    document.write(`LISTA DE FUNCIONÁRIOS QUE TIVERAM DATA DE ADMISSÃO ENTRE 2000 À 2010<br><br>`)
    funcionariosAdmissao.forEach(element => document.write(`Nome: ${element.nome}<br>
    Idade: ${element.idade}<br>
    Sexo: ${element.sexo}<br>
    Cargo: ${element.cargo}<br>
    Salário: R$${element.salario}<br>
    Descontos: R$${element.descontos}<br>
    Data de admissão: ${element.dataAdmissao}<br>
    Data de demissão: ${element.dataDemissao}<br><br>`))
}

listaFuncionariosAdmissao(funcionariosAdmissao)

const funcionariosDemissao = funcionarios.filter(funcionario => (funcionario.dataDemissao > 2018))

function listaFuncionariosDemissao(funcionariosDemissao){
    document.write(`LISTA DE FUNCIONARIOS QUE FORAM DEMITIDOS DEPOIS DE 2018<br><br>`)
    funcionariosDemissao.forEach(element => document.write(`Nome: ${element.nome}<br>
    Idade: ${element.idade}<br>
    Sexo: ${element.sexo}<br>
    Cargo: ${element.cargo}<br>
    Salário: R$${element.salario}<br>
    Descontos: R$${element.descontos}<br>
    Data de admissão: ${element.dataAdmissao}<br>
    Data de demissão: ${element.dataDemissao}<br><br>`))
}

listaFuncionariosDemissao(funcionariosDemissao)

funcionarios.map(funcionario => document.write(`${funcionario.nome}` + ' tem salario de R$' + `${funcionario.salario} ` + 'com desconto de R$' + `${funcionario.descontos}<br>`))

const valorTotalDescontos = funcionarios.reduce((preVal, elem ) => preVal + elem.descontos, 0)

document.write(`<br><br>O valor total dos descontos é R$ ${valorTotalDescontos}`)

//EXERCICIO 3
function somaTradicional(a, b, c){
    return (a + b)/c
}

console.log(somaTradicional(7,3,2))

const somaArrowFunction = (a, b, c) => {
    return (a + b)/c
}

console.log(somaArrowFunction(7,3,2))

//EXERCÍCIO 4
let cliques = 0

function contaCliquesTradicional(){
    updateDisplay(++cliques)
    console.log(cliques)
}

const contaCliquesArrowFunction = () => {
    updateDisplay(++cliques)
    console.log(cliques)
}

function updateDisplay(val) {
    document.getElementById("counter-label").innerHTML = val;
}

//EXERCICIO 5

function calculaMedia(nota1, nota2, nota3, nota4, nota5, nota6, nota7){
    let media = (nota1 + nota2 + nota3 + nota4 + nota5 + nota6 + nota7)/7

    if(media < 6){
        console.log("Aluno REPROVADO")
    }else{
        console.log("Aluno APROVADO")
    }
}

calculaMedia(3,7,4,8,8,5,9)

//EXERCICIO 6
usuario = document.getElementById("usuario")
senha = document.getElementById("senha")

function logar(){
    if(usuario.value == "adm" && senha.value == "1234"){
        alert("Usuário logado com sucesso!")
    }else{
        alert("Usuario inválido")
    }
}

//EXERCICIO 7
idade = document.getElementById("idade")

function calcularIdade(){

//console.log(idade.value)
valorIdade = parseInt(idade.value)
//console.log(valorIdade)

    if(valorIdade <= 12){
        alert("Criança")
    }else if(valorIdade >= 13 && valorIdade <= 18){
        alert("Adolescente")
    }else if(valorIdade >= 19 && valorIdade <= 60){
        alert("Adulto")
    }else{
        alert("Idoso")
    }
}

//EXERCÍCIO 8
numero = document.getElementById("calcParImpar")

function calcularNumero(){
    valorNumero = parseInt(numero.value)

    if(valorNumero % 2 == "0"){
        alert("Este número é par")
    }else{
        alert("Este número é impar")
    }
}








