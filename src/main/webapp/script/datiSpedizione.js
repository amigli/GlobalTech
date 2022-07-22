function validateFormDatiSUtente(){
    let indirizzo = document.getElementById("indirizzo");
    let state = true;
    if(indirizzo.value.length < 3 || !(/^[A-Z\s]{3,30}$/i.test(indirizzo.value))){
        errorInput(indirizzo);
        state = false;
    }else{
        correctInput(indirizzo);
    }

    let citta = document.getElementById("citta");
    if(citta.value.length < 3 || !(/^[A-Z\s]{1,30}$/i.test(citta.value))) {
        errorInput(citta);
        state = false;
    }else{
        correctInput(citta);
    }

    return state;
}