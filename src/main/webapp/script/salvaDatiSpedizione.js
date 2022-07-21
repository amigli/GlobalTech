function activeForm(){
    let form =  document.forms["dati-spedizione"];

    for(let input of form){
        input.removeAttribute("disabled");
    }

    let submit = document.getElementById("salva_button");

    submit.style.display = "block";

    let form2 =  document.getElementById("form-no-modifica");

    form2.parentNode.removeChild(form2);
    let modificaButton =  document.getElementById("modifica-button");
    modificaButton.parentNode.removeChild(modificaButton);
}