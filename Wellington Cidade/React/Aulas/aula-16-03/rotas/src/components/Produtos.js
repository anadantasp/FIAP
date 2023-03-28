import React from 'react'
import {Link} from 'react-router-dom'


export default function Produtos(){

    const produtos={
        backgroundColor: '#ffb',
        height: '500px',
        textAlign: 'center',
        color: '#000'
    }

    return(
        <div style={produtos}>
            <h1>Página Produtos</h1>
            <p>Esta é uma página de produtos</p>
            <br/>
            <button>
                <Link to="/menu">Voltar</Link>
            </button>
        </div>
    );
}