function validateFormDatiSUtente(){
    let indirizzo = document.getElementById("indirizzo");
    if(indirizzo==null || !(/^[A-Z\s]{1,30}$/i.test(indirizzo.value))){
        errorInput(indirizzo);
        return false;
    }else{
        correctInput(indirizzo);
        return true;
    }

    let citta = document.getElementById("citta");
    if(citta==null) {
        errorInput(citta);
        return false;
    }else{
        correctInput(citta);
        return true;
    }
}