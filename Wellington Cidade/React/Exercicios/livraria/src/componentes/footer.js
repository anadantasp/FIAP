import React from "react";
import Instagram from '../img/instagram (1).png'
import Facebook from '../img/facebook.png'
import YouTube from '../img/video.png'
import '../css/footer.css'

export default function Footer(props){
    return(
        <>
        <footer>
            <h3>Letrinhas</h3>
            <div className="footer-icons">
                <img src={Instagram} className="icons" alt="Instagram logo" />
                <img src={Facebook} className="icons" alt="Facebook logo" />
                <img src={YouTube} className="icons" alt="YouTube logo" />
            </div>
        </footer>
        </>
    )
}