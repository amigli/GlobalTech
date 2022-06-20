 function validateForm(){
    let form = document.forms["caricamento-prodotto"];
    let nome = form["nome"];
    let result = true;
    if((/^[A-Z\s]{3,45}$/i).test(nome.value)){
        inputCorrect(nome, "nome");
    }else{
        inputError(nome, "nome");
        result = false;
    }
   let marca =  form["marca"];

    if((/^[A-Z0-9\s]{4,30}$/i).test(marca.value)){
        inputCorrect(marca, "marca" );
    }else{
        inputError(marca, "marca")
        result=false
    }

    let color = form["colore"];

    if((/^[A-Za-z]{4,30}$/i).test(color.value)){
        inputCorrect(color,"colore")
    }else{
        result=false;
        inputError(color, "colore")
    }

    return result;
}

function inputError(input, id){
   let err = "err_" + id;
   document.getElementById(err).textContent = "Inserire " + id + " ammissibile";
   input.style.backgroundColor = "red";
   input.style.border = "2px dotted black";

}

function inputCorrect(input, id){
    let err = "err_" + id;
    document.getElementById(err).textContent = "";
    input.style.backgroundColor = "white";
    input.style.border = "1px solid #ccc";
}

function activeRam(value){
    let elem =  document.getElementById("ram_quantita");
    if(value != "nessuna"){
        elem.removeAttribute("disabled");
    }else{
        elem.setAttribute("disabled", "disabled");
        elem.value = "";
    }

}