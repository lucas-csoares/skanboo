<template>
    <section>


        <div class="descricao">
            <div class="imagem">
                <img src="" alt="Foto do Produto" />
            </div>
            <div class="titulo">
                <h1>titulo fgsdf fdsfdsd</h1>
            </div>
            <div class="informacoes-produto">

                <span><b>Categoria do produto: </b></span>
                <span class="categoria">categoria</span>

                <br />

                <span><b>Categoria de interesse </b></span>
                <span class="oferta">categoria desejada</span>
                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Laboriosam ut eos eligendi tempore quis, ab
                dolores
                hic non vel eius expedita dolorem soluta, corrupti quam? Debitis doloribus mollitia illum unde
                fugiat
                accusamus pariatur, nostrum quia similique alias labore delectus eaque non. Ipsam distinctio itaque
                hic
                ab
                ipsa perspiciatis consectetur iure.</p>
            </div>

        </div>
        <button>Negociar</button>

    </section>
</template>
  
<script>


</script>
  
<style scoped>
.container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    max-width: 500px;
    padding: 10px;
    text-align: left;
    margin: 0 auto;
}

.imagem {
    width: 300px;
    height: 300px;
    background-color: grey;
}

img {
    max-width: 300px;
    max-height: 300px;
    object-fit: cover;
}

.titulo {
    display: flex;
    flex-direction: column;
    height: 100%;
    margin-left: 10px;
    margin-top: 10px;
    padding: 10px;
    padding-top: 0;
    flex: 1;
}

.informacoes-produto {
    display: flex;
    flex-direction: column;
    height: 100%;
    margin-left: 0px;
    margin-top: 10px;
    padding: 10px;
    padding-top: 0;
    flex: 1;
    border: 2px solid #515864;
}

span {
    line-height: 1.5em;
}

.descricao {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    max-width: 500px;
    padding: 0px;
    text-align: left;
    margin: 0 auto;
}

button {
    box-sizing: border-box;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    width: 500px;
    height: 32px;
    background: transparent;
    border: 1px solid #000;
    border-radius: 16px;
    font-weight: 600;
    color: #515864;
    transition: 0.3s;
    margin-top: 10px;
}

button:hover {
    box-sizing: border-box;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    width: 100px;
    height: 32px;
    background: #f9dc5c;
    border: 1px solid #f9dc5c;
    border-radius: 16px;
    font-weight: 600;
    color: #515864;
    transition: 0.3s;

    margin-top: 10px;
}

p {
    line-height: 1.5em;
    padding: 20px 0px;
    font-size: 16px;
    text-align: justify;
    word-break: break-all;
}

h1 {
    font-size: 2em;
}
</style>
  