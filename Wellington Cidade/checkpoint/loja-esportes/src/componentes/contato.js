import React,{useState} from 'react'
import './css/contato.css'

function Contato(){

    const[nome,setNome]= useState ("")
    const[email,setEmail] = useState("")
    const[userEmail,setUserEmail] = useState()

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
            <h3>Preencha o formulário abaixo</h3>
            <p>Nome: <input type="text" onChange={(e)=>setNome(e.target.value)}/></p>
            <p>E-mail:<input type="text" onChange={(e)=>setEmail(e.target.value)}/></p>
            <p>Mensagem: <textarea rows="4" cols="50"></textarea></p>
            <button type="submit" onClick={enviarEmail}>Enviar</button>

            {userEmail &&(
            <div  className='condicional'>
                <p>O email do usuário é: <span>{userEmail}</span></p>
            </div>

         )}
            
        </div>
        
        
    )
}

export default Contato