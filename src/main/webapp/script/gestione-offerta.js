



function rimuoviProdotto(){
    let id = document.getElementById("id_offer").value;
    const chekboxAll =  document.getElementsByName("prod-attivi");
    const checked = Array.from(chekboxAll).filter(c=>c.checked);

    if(checked.length > 0){

        let parameters = "prodotto=" + checked[0].value;

        for (let i = 1; i < checked.length; i++) {
            parameters += "&prodotto=" + checked[i].value;
        }

        const xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let otherSection =  document.getElementById("prodotti-other");

                for(let c of checked){
                    c.checked = false;
                    c.setAttribute("name", "prod-other" )

                    let parent = c.parentNode;
                    parent.parentNode.removeChild(parent);
                    otherSection.appendChild(parent);
                }
            }
        }

        xhttp.open("POST", "rimuovi-prodotto-offerta");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters + "&id=" + id)
    }else{
        window.alert("Selezionare almeno un prodotto da eliminare")
    }


}

function aggiungiProdotto(){
    let id = document.getElementById("id_offer").value;
    const chekboxAll =  document.getElementsByName("prod-other");
    const checked = Array.from(chekboxAll).filter(c=>c.checked);

    if(checked.length > 0){

        let parameters = "prodotto=" + checked[0].value;

        for (let i = 1; i < checked.length; i++) {
            parameters += "&prodotto=" + checked[i].value;
        }

        const xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let otherSection =  document.getElementById("prodotti-attivi");

                for(let c of checked){
                    c.checked = false;
                    c.setAttribute("name", "prod-attivi" )

                    let parent = c.parentNode;
                    parent.parentNode.removeChild(parent);
                    otherSection.appendChild(parent);
                }
            }
        }

        xhttp.open("POST", "aggiungi-prodotto-offerta");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters + "&id=" + id)
    }else{
        window.alert("Selezionare almeno un prodotto da eliminare")
    }
}

function effettuaModifica(){
    let forms = document.forms["form-modifica"];

    for(let el of forms){
        el.removeAttribute("disabled");
    }

    document.getElementById("salva-modifica").style.display =  "block";
    document.getElementById("effettua-modifica").style.display = "none";
    document.getElementById("gestione-prodotti-offerta").style.display = "none";
}