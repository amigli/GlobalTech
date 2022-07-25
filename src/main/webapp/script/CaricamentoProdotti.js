 function validateForm(id){
    let form = document.forms[id];
    let nome = form["nome"];
    let result = true;
    if((/^[\w\s]{3,45}$/i).test(nome.value)){
        inputCorrect(nome, "nome");
    }else{
        inputError(nome, "nome");
        result = false;
    }
   let marca =  form["marca"];

    if((/^[\w\s]{3,30}$/i).test(marca.value)){
        inputCorrect(marca, "marca" );
    }else{
        inputError(marca, "marca")
        result=false
    }

    let color = form["colore"];

    if((/^[\w\s]{3,30}$/i).test(color.value)){
        inputCorrect(color,"colore")
    }else{
        result=false;
        inputError(color, "colore")
    }

     let cpu = form["cpu_nome"];

     if((/^[\w\s]{4,30}$/i).test(cpu.value)){
         inputCorrect(cpu,"cpu_nome")
     }else{
         result=false;
         inputError(cpu, "cpu_nome")
     }

    if(!result){
        window.alert("Inserire parametri validi!");
    }
    return result;
}

function inputError(input, id){
   let err = "err_" + id;
   document.getElementById(err).textContent = "Inserire  " + id + " ammissibile";
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
    let unit = document.getElementById("ram_unit");

    if(value != "nessuna"){
        elem.removeAttribute("disabled");
        unit.removeAttribute("disabled");
        elem.setAttribute("required", "required")
        unit.setAttribute("required", "required")
    }else{
        unit.setAttribute("disabled", "disabled");
        elem.setAttribute("disabled", "disabled");
        elem.value = "";
        elem.removeAttribute("required")
        unit.removeAttribute("required")
    }

}