function effettuaModificaProdotto(){
    let form = document.forms["modifica-prodotto"];

    for(let input of form){
        input.removeAttribute("disabled");
    }

    let ramTipo =  document.getElementById("ram_tipo");

    if(ramTipo.value.length == 0){
        let ramQuantita =  document.getElementById("ram_quantita");

        ramQuantita.setAttribute("disabled", "disabled");
    }



    form["modifica-prodotto-submit"].style.display = "block";



    $("button#effettua-modifica-prodotto-button").remove();
}