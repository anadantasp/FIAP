import React, { useEffect, useState } from "react";
import './css/contador.css'

function Contador(){

    const calculateTimeLeft = () => {
        let year = new Date().getFullYear();

        console.log(year)

        const difference = +new Date(`05/15/${year}`) - +new Date();

        let timeLeft = {};

        if (difference > 0) {
            timeLeft = {
              days: Math.floor(difference / (1000 * 60 * 60 * 24)),
              hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
              minutes: Math.floor((difference / 1000 / 60) % 60),
              seconds: Math.floor((difference / 1000) % 60)
            };
          }

          return timeLeft;
    };

    const [timeLeft, setTimeLeft] = useState(calculateTimeLeft());

    useEffect(() => {
        const timer = setTimeout(() => {
          setTimeLeft(calculateTimeLeft());
        }, 1000);

        return () => clearTimeout(timer);
      });

      const timerComponents = [];

      Object.keys(timeLeft).forEach((interval) => {
        if (!timeLeft[interval]) {
          return;
        }
      
        timerComponents.push(
          <span>
            {timeLeft[interval]} {interval}{" "}
          </span>
        );
      });

    return(
        <section className="verLimite">
            <article className="video">
                <iframe width="560" height="315" src="https://www.youtube.com/embed/_wLlhIU1KiY" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
            </article>
            <p className="contador">UMA NOVA COLEÇÃO VEM AÍ</p>
            <p className="contador">{timerComponents.length ? timerComponents : <span>Time's up!</span>}</p>
            
            
            
        </section>
    )
}

export default Contador