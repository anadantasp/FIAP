import React from 'react'


export default function Home(){

    const home={
        backgroundColor: '#bff',
        height: '500px',
        textAlign: 'center',
        color: '#000'
    }

    return(
        <div style={home}>
            <h1>Página Home</h1>
            <p>Esta é uma página home</p>
        </div>
    );
}