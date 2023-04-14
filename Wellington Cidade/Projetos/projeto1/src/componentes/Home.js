
import React,{useState} from 'react'
import './css/style.css'

function Home(){

    //criando o useState(manipula o estado da variável)
    const [nome, setNome] = useState("Ana")

    return(
        <div className="home">
        <h1>Home</h1>
        <br/>
        <p className='nome'>Nome: {nome}</p>

        </div>
    )
}

export default Home