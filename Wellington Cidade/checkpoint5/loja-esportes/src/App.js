import Home from './componentes/Home'
import Login from './componentes/Login'
import { useState } from 'react';

function App() {

    const [user, setUser] = useState([])

  return (
    <div>
      {
          !user.length > 0
          ? <Login setUser={setUser} />
          : <Home user={user} setUser={setUser}/>
      }
    </div>
    
  );
  
}

export default App;
