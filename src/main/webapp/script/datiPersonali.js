function validateFormDatiPUtente(){
    let nome = document.getElementById("nome");
    if (nome.value == null || nome.length < 3 || !(/[A-Z]{3,30}/i.test(nome.value))){
        errorInput(nome);
        return false;
    }else{
        correctInput(nome);
        return true;
    }

    let cognome=document.getElementById("cognome");
    if(cognome.value == null || cognome.length < 3 || !(/^[A-Z\s]{3,30}$/i.test(cognome.value))){
        errorInput(cognome);
        return false;
    }else{
        correctInput(cognome);
        return true;
    }

    let dataNascita = document.getElementById("dataNascita");
    const data=dataNascita.value.split("-");
    let anno = data[0];
    var dataOggi = new Date();

    if(dataNascita.value == null){
        errorInput(dataNascita);
        return false;
    }
    else{
        correctInput(dataNascita);
        return true;
    }

    let telefono = document.getElementById("telefono");
    if(telefono.value == null){
        errorInput(telefono);
        return false;
    }
    else{
        correctInput(telefono);
        return true;
    }



}