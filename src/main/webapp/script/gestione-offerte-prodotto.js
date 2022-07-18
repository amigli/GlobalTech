
function rimuoviOfferta(elementName){
    let id =  document.getElementById("id-prod").value;
    const checkbox = document.getElementsByName(elementName);
    const checked = new Array();
    for(let elem of checkbox){
        if(elem.checked == true){
            checked.push(elem);
        }
    }

    if(checked.length > 0) {

        let parameters = "offerta=" + checked[0].value;

        for (let i = 1; i < checked.length; i++) {
            parameters += "&offerta=" + checked[i].value;
        }



        const xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let elementId =
                        elementName == "offerta-attiva-prodotto" ?
                            "offerte-attive-other" : "offerte-future-other";

                        let otherSection = document.getElementById(elementId);

                        for (let elem of checked) {

                            let attributeNewName = elementName == "offerta-attiva-prodotto" ?
                                "offerta-attiva-other" : "offerta-futura-other";

                            elem.setAttribute("name", attributeNewName);
                            elem.checked = false;

                            let parent = elem.parentNode;
                            parent.parentNode.removeChild(parent);
                            otherSection.appendChild(parent);

                        }
                    }

            }

            xhttp.open("POST", "rimuovi-offerta-prodotto");
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(parameters + "&id=" + id)
    } else {
        window.alert("Selezionare almeno una categoria da rimuovere!")
    }


}


function aggiungiOfferta(elementName){
    let id =  document.getElementById("id-prod").value;
    const checkbox = document.getElementsByName(elementName);
    const checked = new Array();
    for(let elem of checkbox){
        if(elem.checked == true){
            checked.push(elem);
        }
    }

    if(checked.length > 0) {

        let parameters = "offerta=" + checked[0].value;

        for (let i = 1; i < checked.length; i++) {
            parameters += "&offerta=" + checked[i].value;
        }
            const xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let elementId =
                        elementName == "offerta-attiva-other" ?
                            "offerte-attive-prodotto" : "offerte-future-prodotto";


                        let otherSection = document.getElementById(elementId);

                        for (let elem of checked) {

                            let attributeNewName = elementName == "offerta-attiva-other" ?
                                "offerta-attiva-prodotto" : "offerta-futura-prodotto";

                            elem.setAttribute("name", attributeNewName);
                            elem.checked = false;

                            let parent = elem.parentNode;
                            parent.parentNode.removeChild(parent);
                            otherSection.appendChild(parent);

                        }
                    }
                }

            xhttp.open("POST", "applica-offerta-prodotto");
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(parameters + "&id=" + id)
    } else {
        window.alert("Selezionare almeno una categoria da rimuovere!")
    }


}
