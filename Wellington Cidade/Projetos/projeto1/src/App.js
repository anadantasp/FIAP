import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Home from './componentes/Home'
import Empresa from './componentes/Empresa'
import Contato from './componentes/Contato'
import Nav from './componentes/Nav'
import Footer from './componentes/Footer'

function App() {
  return (
    <>
     <BrowserRouter>
      <Nav/>
       <Routes>
         <Route path="/" element={<Home/>}></Route>
         <Route path="/empresa" element={<Empresa/>}></Route>
         <Route path="/contato" element={<Contato/>}></Route>
       </Routes>
        <Footer/>
     </BrowserRouter>
    </>
  );
}

export default App;
