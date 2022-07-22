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

function validateFormDatiSpedizione(){
    let state = true;

    let nome = document.getElementById("nome");

    if(nome.value.length < 3  || !/^[A-Z\s]{3,30}/i.test(nome.value)){
        errorInput(nome);
        state = false;
    }else{
        correctInput(nome);
    }

    let cognome = document.getElementById("nome");

    if(cognome.value.length < 3  || !/^[A-Z\s]{3,30}/i.test(cognome.value)){
        errorInput(cognome);
        state = false;
    }else{
        correctInput(cognome);
    }
    let via =  document.getElementById("via");

    if(via.value.length < 3  || !/^[A-Z0-9\s]{3,30}/i.test(via.value)){
        errorInput(via);
        state = false;
    }else{
        correctInput(via);
    }

    let citta = document.getElementById("citta");

    if(citta.value.length < 3  || !/^[A-Z\s]{3,30}/i.test(citta.value)){
        errorInput(citta);
        state = false;
    }else{
        correctInput(citta);
    }
    let cap = document.getElementById("cap");

    if(/^[0-9]{5}/i.test(cap.value)){
        errorInput(cap);
        state = false;
    }else{
        correctInput(cap);
    }

    return state;
}


