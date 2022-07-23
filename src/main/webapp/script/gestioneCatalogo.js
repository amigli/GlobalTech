function filtraProdotti(){
    let categoria = document.querySelector("select#select-categoria").value;
    let marca =  document.querySelector("select#select-marca").value;

    const xhttp =  new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200 ){
            let responseText = xhttp.responseText;
            const prodottiList = JSON.parse(responseText);

            let section = document.querySelector("section#prodotti-catalogo");
            section.textContent = "";

            if(prodottiList.length > 0){
                for(let item of prodottiList){
                    let figure = document.createElement("figure");
                    figure.setAttribute("class", "prodotto-figure");

                    let img = document.createElement("img");

                    img.setAttribute("class", "prodotto-img");

                    if(item.prodotto.immagini.length > 0){
                        img.setAttribute( "src",
                            "image/" + item.prodotto.immagini[0].prodottoId + "/" + item.prodotto.immagini[0].numeroId + "."
                            + item.prodotto.immagini[0].estensione  );
                    }else{
                        img.setAttribute("src", "./asset/default.png");
                    }

                    figure.appendChild(img);
                    let figcaption =  document.createElement("figcaption");

                    figure.appendChild(figcaption);

                    let nameH1 = document.createElement("h1");
                    nameH1.setAttribute("id", "marca-nome");
                    nameH1.appendChild(document.createTextNode(item.prodotto.marca + "-" + item.prodotto.nome));

                    let div =  document.createElement("div");
                    div.setAttribute("id", "title-prezzo");
                    div.appendChild(nameH1);

                    let prezzo = document.createElement("h2");
                    prezzo.setAttribute("id","prezzo");
                    prezzo.appendChild(document.createTextNode("€" + item.prezzo));

                    div.appendChild(prezzo)

                    figcaption.appendChild(div);

                    let description = document.createElement("div");
                    description.setAttribute("id", "descrizione");
                    description.appendChild(document.createTextNode(item.prodotto.descrizione.substring(0, 50)));
                    figcaption.appendChild(description);




                    let form = document.createElement("form");
                    form.setAttribute("action", "aggiungi-carrello");
                    form.setAttribute("method", "post");


                    let quantita = document.createElement("input");
                    quantita.setAttribute("type", "hidden");
                    quantita.setAttribute("name", "quantita");
                    quantita.setAttribute("value", "1");
                    form.appendChild(quantita);

                    let prodotto = document.createElement("input");
                    prodotto.setAttribute("type", "hidden");
                    prodotto.setAttribute("name", "prodotto");
                    prodotto.setAttribute("value", item.prodotto.id);
                    form.appendChild(prodotto);

                    let submit =  document.createElement("input");
                    submit.setAttribute("type", "submit");
                    submit.setAttribute("value", "Aggiungi al carrello");
                    submit.setAttribute("class", "aggiungi-carrello-catalogo");
                    form.appendChild(submit)

                    figcaption.appendChild(form);

                    figure.addEventListener("click",
                        function(){location.href = 'dettaglio-prodotto?id=' + item.prodotto.id })

                    section.appendChild(figure)
                }
            }else{
                section.textContent =  "La ricerca non ha prodotto risultati";
            }
        }
    }

    xhttp.open("GET", "filtra-prodotti?categoria="+categoria +"&marca=" + marca);
    xhttp.send();


}

