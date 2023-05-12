import './css/navbar.css'
import {Link} from 'react-router-dom'

function Nav(){
    return(
        <div>
            <ul>
                <li>
                    <Link to="/">HOME</Link>
                </li>
                <li>
                    <Link to="/contato">FALE CONOSCO</Link>
                </li>
            </ul>
        </div>
    )
}

export default Nav