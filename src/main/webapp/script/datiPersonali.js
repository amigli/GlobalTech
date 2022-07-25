function validateFormDatiPUtente(){
    let nome = document.getElementById("nome");
    let state = true;
    if (nome.value.length < 3 || !(/^[A-Z\s]{3,30}$/i.test(nome.value))){
        errorInput(nome);
        state = false;
    }else{
        correctInput(nome);
    }

    let cognome=document.getElementById("cognome");
    if(cognome.length < 3 || !(/^[A-Z\s]{3,30}$/i.test(cognome.value))){
        errorInput(cognome);
        state = false;
    }else{
        correctInput(cognome);
    }

    return state;
}