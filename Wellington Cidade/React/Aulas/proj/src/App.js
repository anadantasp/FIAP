
import React,{useRef} from 'react';
import './App.css';
import Home from './Home'

function App() {

  const user = useRef()
  const password = useRef()
  const getUser = localStorage.getItem("userData")
  const getSenha = localStorage.getItem("senhaData")

  const handleSubmit = () => {
    if(user.current.value === "ana" && password.current.value === "12345"){
      localStorage.setItem("userData", "ana")
      localStorage.setItem("senhaData", "12345")
    }else{
      alert("Usuário e senha inválidos!")
    }
  }

  return (
    <div>
      <h1>Login</h1>
      {
        getUser && getSenha ? <Home/>:

        <form onSubmit={handleSubmit}>
          <p>Usuario: <input type="text" ref={user}/></p><br/>
          <p>Senha: <input type="password" ref={password}/></p><br/>
          <input type="submit" value="Login"/>
       </form>
      }
      
    </div>
  );
}

export default App;
