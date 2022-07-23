function effettuaModificaProdotto(){
    let form = document.forms["modifica-prodotto"];

    for(let input of form){
        input.removeAttribute("disabled");
    }

    let ramTipo =  document.getElementById("ram_tipo");

    if(ramTipo.value == "nessuna"){
        let ramQuantita =  document.getElementById("ram_quantita");
        let ramUnit =  document.getElementById("ram_unit");

        ramUnit.setAttribute("disabled", "disabled");
        ramQuantita.setAttribute("disabled", "disabled");
    }



    form["modifica-prodotto-submit"].style.display = "block";



    $("button#effettua-modifica-prodotto-button").remove();
}