import React, { useState } from 'react';

export default () => {
    const[num, SetNum] = useState(0)

    return(
        <>
    
            <p>O Número {num} é:
            {//  pegar valores Elementos e comparar
             num % 2 == 0 ? 'par' : 'impar'}</p>

        {// Elemento e vai se é para ou impar
            num % 2 == 0  ?
            <p>Este Numero é par</p> :
            <p>Este Numero é Impar</p>
        }

        {/* ao clicar no botão ele compara os campos setado e chama  setNum que estpa no
         array , desta forma esta desestruturando para receber parte ou todo retorno(par ou impar)*/}
            <button onClick={()=>SetNum(num + 1)}>Realiza</button>      
       </> 
    );
}