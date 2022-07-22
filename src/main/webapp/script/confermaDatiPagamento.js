function activeFormPagamento(){
    let form =  document.forms["form-dati-pagamento"];

    for(let input of form){
        input.removeAttribute("disabled");
    }

    let submit = document.getElementById("continua");

    submit.style.display = "block";

    let form2 =  document.getElementById("form-no-modifica");

    form2.parentNode.removeChild(form2);

    let modificaButton =  document.getElementById("modifica-button");
    modificaButton.parentNode.removeChild(modificaButton);
}

