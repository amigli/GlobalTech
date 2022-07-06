// noinspection DuplicatedCode

function rimuoviCategoria(){
    let id =  document.getElementById("id-prod").value;
    const checkbox = document.getElementsByName("categoria-prod");
    const checked = new Array();
    for(let elem of checkbox){
        if(elem.checked == true){
            checked.push(elem);
        }
    }

    if(checked.length > 0){

        let parameters = "categoria=" + checked[0].value;

        for(let i=1; i < checked.length; i++){
            parameters += "&categoria=" +checked[i].value;
        }


        const xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                let otherSection = document.getElementById("altre-categorie");

                for(let elem of checked){
                    elem.setAttribute("name", "categoria-other");
                    elem.checked = false;

                    let parent =  elem.parentNode;
                    parent.parentNode.removeChild(parent);
                    otherSection.appendChild(parent);

                }
            }
        };

        xhttp.open("POST", "rimuovi-prodotto-categoria");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters+"&id=" + id);
    }else{
        window.alert("Selezionare almeno una categoria da rimuovere!")
    }


}

function aggiungiCategoria(){

    let id =  document.getElementById("id-prod").value;
    const checkbox = document.getElementsByName("categoria-other");
    const checked = [];

    for(let elem of checkbox){
        if(elem.checked == true){
            checked.push(elem);
        }
    }

    if(checked.length > 0){
        let parameters = "categoria=" + checked[0].value;

        for(let i=1; i < checked.length; i++){
            parameters += "&categoria=" +checked[i].value;
        }

        console.log(parameters)


        const xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                let otherSection = document.getElementById("categorie-prodotto");

                for(let elem of checked){
                    elem.setAttribute("name", "categoria-prod");
                    elem.checked = false;

                    let parent =  elem.parentNode;
                    parent.parentNode.removeChild(parent);
                    otherSection.appendChild(parent);

                }
            }
        };

        xhttp.open("POST", "aggiungi-prodotto-categoria");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(parameters+"&id=" + id);
    }else{
        window.alert("Selezionare almeno una categoria da rimuovere!");
    }







}