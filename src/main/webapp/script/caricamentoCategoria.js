function validateFormCategoria(){
    let nome = document.getElementById("nomeCategoria");

    if(/^[\wèàùòé\s]{3,45}$/i.test(nome.value)){
        correctInput(nome);
        return true;
    }else{
        errorInput(nome);
        return false;
    }
}