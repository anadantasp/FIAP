import React,{useState} from 'react'
import './css/contato.css'

function Contato(){

    const[nome,setNome]= useState ("")
    const[email,setEmail] = useState("")
    const[userEmail,setUserEmail] = useState()
    const[error, setError] = useState(false)

    function enviarEmail(e){

        e.preventDefault()

        setUserEmail(email)
        console.log(userEmail)
    }

    function limparEmail(){
        setUserEmail('')
    }

    return(
        <div className='contato'>
            <h3>PREENCHA O FORMULÁRIO ABAIXO</h3>
            <p>Nome: <input type="text" onChange={(e)=>setNome(e.target.value)}/></p>
            <p>E-mail: <input type="text" onChange={(e)=>setEmail(e.target.value)}/></p>
            <p>Mensagem: <textarea rows="4" cols="50"></textarea></p>
            <button className="botao" type="submit" onClick={enviarEmail}>ENVIAR</button>

            {userEmail &&(
            <div  className='condicional'>
                <p>Olá {nome}! Recebemos a sua mensagem e em um prazo de 48 horas entraremos em conta através do e-mail informadao: <span>{userEmail}</span></p>
            </div>

         )}
            
        </div>
        
        
    )
}

export default Contato