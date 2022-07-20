function validateDatiCUtente(){
    let numeroCarta = document.getElementById("numCarta");
    if(numeroCarta==null){
        errorInput(numeroCarta);
        return false;
    }else{
        correctInput(numeroCarta);
        return true;
    }

    let cvv = document.getElementById("cvv");
    if(cvv==null){
        errorInput(cvv);
        return false;
    }else{
        correctInput(cvv);
        return true;
    }

    let dataScadenza = document.getElementById("dataCarta");
    if(dataScadenza==null){
        errorInput(dataScadenza);
        return false;
    }else{
        correctInput(dataScadenza);
        return true;
    }
}