import React from 'react'
import '../css/cabecalho.css'
import Carrinho from '../img/bolsa-de-compras.png'
import Usuario from '../img/account.png'
import Login from '../img/enter.png'

export default function Cabecalho(props){
    return(
        <>
        <header>
            <h3>LETRINHAS</h3>
            <input type="search"/>
            <div className='cabecalho-icons'>
                <img src={Carrinho} className="icons" alt="Carrinho de compras"/>
                <img src={Usuario} className="icons" alt="Minha conta" />
                <img src={Login} className="icons" alt='Login'/>
            </div>
        </header>
        </>
    )
}