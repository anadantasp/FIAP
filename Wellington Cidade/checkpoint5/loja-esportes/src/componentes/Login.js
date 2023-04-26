import './css/Login.css'
import { useState } from 'react';

function Login({setUser}){

    const[nome, setNome] = useState("")
    const[senha, setSenha] = useState("")
    const[error, setError] = useState(false)

    const handleSubmit = (e) =>{
        e.preventDefault()

        if(nome === "" || senha === ""){
            setError(true)
            return
        }

        setError(false)

        setUser([nome])
    }

    return(
        <div className='login'>
            <form onSubmit={handleSubmit}>
                <h1>Login</h1>
                <p>USUÁRIO: <input type="text" value={nome} onChange={e => setNome(e.target.value)}/></p>
                <p>SENHA: <input type="password" value={senha} onChange={e => setSenha(e.target.value)}/></p>

                <input type="submit" value="LOGAR"/>

            </form>
            {error && <p>Todos os campos são obrigatórios</p>}
        </div>
    )
}

export default Login;