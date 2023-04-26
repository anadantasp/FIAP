import './css/Home.css'

function Home({user, setUser}){

    const handleLogout = () => {
        setUser([])
    }

    return(
        <>
            <h2>{user}</h2>
            <button onClick={handleLogout}>Encerrar sessão</button>
            <h3>Produtos mais vendidos</h3>
            <section>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>
                <article>
                    <img src="https://decathlonpro.vtexassets.com/arquivos/ids/2763414-588-752/segunda-pele-simple-warm-feminina-wedze-preto-m2.jpg?v=637544696247830000" alt='imagem'></img>
                </article>

            </section>
        </>
    )
}

export default Home