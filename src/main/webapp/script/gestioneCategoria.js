function rimuoviProdottoCategoria(){
    let id = document.getElementById("id_cat").value;
    const chekboxAll =  document.getElementsByName("prod-categoria");
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

        xhttp.open("POST", "rimuovi-categoria-prodotto");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters + "&id=" + id)
    }else{
        window.alert("Selezionare almeno un prodotto da aggiungere")
    }


}

function aggiungiProdottoCategoria(){
    let id = document.getElementById("id_cat").value;
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
                let otherSection =  document.getElementById("prodotti-categoria");

                for(let c of checked){
                    c.checked = false;
                    c.setAttribute("name", "prod-categoria" )

                    let parent = c.parentNode;
                    parent.parentNode.removeChild(parent);
                    otherSection.appendChild(parent);
                }
            }
        }

        xhttp.open("POST", "aggiungi-categoria-prodotto");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters + "&id=" + id)
    }else{
        window.alert("Selezionare almeno un prodotto da eliminare")
    }
}