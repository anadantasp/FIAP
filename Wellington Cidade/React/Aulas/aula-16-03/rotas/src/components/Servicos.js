import React from 'react'


export default function Servicos(){

    const servicos={
        backgroundColor: 'darkblue',
        height: '500px',
        textAlign: 'center',
        color: '#000'
    }

    return(
        <div style={servicos}>
            <h1>Página Serviços</h1>
            <p>Esta é uma página de serviços</p>
        </div>
    );
}