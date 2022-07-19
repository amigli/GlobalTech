function setDataFine(){
    let dataInzioString = document.getElementById("data-inizio").value;

    let dataInizio = new Date(dataInzioString);

    dataInizio.setDate(dataInizio.getDate() + 1);

    let dataFine =  document.getElementById("data-fine");
    dataFine.setAttribute("min", dataInizio.toISOString().split("T")[0]);
    dataFine.removeAttribute("disabled");
}

function validateFormOfferta(){
    let nome = document.getElementById("nome");

    if(/^[\wèàùòé\s]{3,45}$/i.test(nome.value)){
        correctInput(nome);

        return true;
    }else{
        errorInput(nome);
        return false;
    }
}


