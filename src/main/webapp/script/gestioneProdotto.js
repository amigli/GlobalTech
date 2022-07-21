function effettuaModificaProdotto(){
    let form = document.forms["modifica-prodotto"];

    for(let input of form){
        input.removeAttribute("disabled");
    }

    form["modifica-prodotto-submit"].style.display = "block";

    $("button#effettua-modifica-prodotto-button").remove();
}