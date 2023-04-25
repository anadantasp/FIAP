import React from 'react'
import './App.css'

function Home(){    

    const handleSubmit = () => {
        localStorage.clear();
        window.location.reload();
    }

    return (
        <>
            <h1>Home</h1>
            <button onClick={handleSubmit}>Logout</button>
        </>
    )
}
export default Home