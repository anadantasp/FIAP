import {Link} from 'react-router-dom'
import '../index.css'
import './css/style.css'

function Nav(){
    return(
        <>
        <div id='menu'>
           <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/empresa">Empresa</Link>
                </li>
                <li>
                    <Link to="/contato">Contato</Link>
                </li>
           </ul>
           </div>
        </>
    )
}

export default Nav