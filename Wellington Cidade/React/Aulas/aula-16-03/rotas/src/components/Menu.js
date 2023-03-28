import React from 'react';
import {Link} from 'react-router-dom';
import './Menu.css'

export default function Menu(){
    return(
        <nav>
            <ul>
                <li><Link to='/' >Home</Link></li>
                <li><Link to='/Produtos'>Produtos</Link></li>
                <li><Link to='/Servicos'>Servicos</Link></li>
            </ul>
        </nav>
    )
}