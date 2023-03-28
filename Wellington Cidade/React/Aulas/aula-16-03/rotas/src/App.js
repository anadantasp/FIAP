import React from 'react';
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import Home from './components/Home';
import Produtos from './components/Produtos';
import Servicos from './components/Servicos';
import Error from './components/Error';
import Menu from './components/Menu';
import Exemplo from './components/Exemplo';

export default function App(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/menu" element={<Menu/>}/>
                <Route path="/" element={<Home/>}/>
                <Route path="/produtos" element={<Produtos/>}/>
                <Route path="/servicos" element={<Servicos/>}/>
                <Route path="*" element={<Error/>}/>
                <Route path="/exemplo" element={<Exemplo/>}/>
            </Routes>
        </BrowserRouter>
    );
}