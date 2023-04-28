import {FaFacebook, FaInstagram,FaLinkedin} from 'react-icons/fa'
import './css/footer.css'

function Footer(){
    return(
        <footer>
            <ul className='rsocial'>
                <li>
                   <FaFacebook/>
                </li>
                <li>
                    <FaInstagram/>
                </li>
                <li>
                    <FaLinkedin/>
                </li>
            </ul>

        <p>ANA PRADO - 2023</p>
        
        </footer>
    )
}

export default Footer