//função tradicional
function soma(num1, num2){
    return num1 - num2
}

console.log(soma(2,3))

const soma1 = (num3,num4) => {
    return num3 + num4
}

console.log(soma1(5,9))

//function tradicional
const texto = "Olá mundo 😊"

function sepaTexto(texto){
    return texto.split("")
}

console.log(sepaTexto(texto))

//arrow function
const texto1 = "mundo"
const sepaTexto1 = (texto1) => {
    return texto1.split('')
}

console.log(sepaTexto1(texto1))

//arrow function com array
const usuarios = [
    {id: 1, nome: "Huguinho"},
    {id: 2, nome: "Zézinho"},
    {id: 3, nome: "Luizinho"}
]

const usuario = usuarios.find(usuario => usuario.id === 1)
document.write(`O usuário é: ${usuario.nome}`)

//exemplo com filter, map e reduce
const funcionarios = [
    {nome: "Ana", salario: 3000, dataNascimento: 1996},
    {nome: "Diego", salario: 1500, dataNascimento: 1992},
    {nome: "Chewie", salario: 1000, dataNascimento: 2015},
    {nome: "Ivone", salario: 2000, dataNascimento: 1975},
    {nome: "Olívia", salario: 5000, dataNascimento: 1990}
    
]

const dataNascimento = funcionarios.filter(funcionario => (funcionario.dataNascimento > 2000));

console.log(dataNascimento);

const exibeInfo = funcionarios.map(funcionario => `${funcionario.nome}` + ' tem salario de: R$' + `${funcionario.salario}`)

console.log(exibeInfo)