import React from 'react'
import Sentimentos from '../img/sentimentos.jpg'
import Apesar from '../img/apesar de tudo.jpg'
import Amoras from '../img/amoras.jpg'
import chorar from '../img/pode-chorar.jpg'
import '../css/livros.css'

export default function Livros(){
    return(
        <>
        <section>
            <article>
                <img src={Sentimentos} className="livros" alt="" />
            </article>
            <article>
                <img src={Apesar} className="livros" alt="" />
            </article>
            <article>
            <img src={Amoras} className="livros" alt="" />
            </article>
            <article>
            <img src={chorar} className="livros" alt="" />
            </article>
        </section>
        </>
    )
}