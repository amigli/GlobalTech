function validateDatiCUtente(){
    let numeroCarta = document.getElementById("numCarta");
    let state = true;
    if(!/^[0-9]{13,16}$/.test(numeroCarta.value)){
        errorInput(numeroCarta);
        state = false;
    }else{
        correctInput(numeroCarta);
    }

    let cvv = document.getElementById("cvv");
    if(!/^[0-9]{3}/.test(cvv.value)){
        errorInput(cvv);
        state = false;
    }else{
        correctInput(cvv);
    }


    return state;
}