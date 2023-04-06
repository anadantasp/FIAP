//EXERCÍCIO 1 - OK

//A - Crie um método tela aluno estudando e apresente na tela
const alunos = [
    {curso: "análise e desenvolvimento de sistemas",
    aluno: "ana carolina",
    professor: "wellington cidade",
    turma: "1TDSPR",
    periodo: "noturno"},
    {curso: "engenharia de software",
    aluno: "diego",
    professor: "Marcelo",
    turma: "1ESTR",
    periodo: "diurno"},
    {curso: "sistemas da informação",
    aluno: "João",
    professor: "Joseffe",
    turma: "1SIOP",
    periodo: "noturno"},
]


function telaAlunoEstudando(alunos){
    document.write(`LISTA DE ALUNOS<br><br>`)
    alunos.forEach(element => document.write(`Curso: ${element.curso}<br>
    Aluno: ${element.aluno}<br>
    Professor: ${element.professor}<br>
    Turma: ${element.turma}<br>
    Período: ${element.periodo}<br><br>`))
}

telaAlunoEstudando(alunos)

//B -  busque um aluno usando o método find e apresente na tela 
const aluno = alunos.find(aluno => aluno.aluno === "diego")
document.write(`O aluno buscado foi: ${aluno.aluno}`)

