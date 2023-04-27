import {BrowserRouter,Routes,Route} from 'react-router-dom'
import Navbar from './componentes/navbar'
import Footer from './componentes/footer'
import Home from './componentes/home'
import Contato from './componentes/contato'


function App() {
  return (
    <>
      <BrowserRouter>
      <Navbar/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/contato" element={<Contato/>}/>
        </Routes>
        <Footer/>
    </BrowserRouter>
    </>
  );
}

export default App;
