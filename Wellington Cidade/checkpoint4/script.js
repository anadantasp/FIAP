//EXERCÍCIO 1 - OK
const escola = [
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


function telaAlunoEstudando(escola){
    document.write(`LISTA DE ALUNOS<br><br>`)
    escola.forEach(element => document.write(`Curso: ${element.curso}<br>
    Aluno: ${element.aluno}<br>
    Professor: ${element.professor}<br>
    Turma: ${element.turma}<br>
    Período: ${element.periodo}<br><br>`))
}

telaAlunoEstudando(escola)

