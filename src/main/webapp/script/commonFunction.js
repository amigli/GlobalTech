function errorInput(elem){
    elem.style.backgroundColor = "red";
    elem.style.border = "2px dotted black";

    let error = document.createElement("div");
    error.setAttribute("id", "err_" + elem.getAttribute("id"))
    error.appendChild(document.createTextNode("Inserire " + elem.getAttribute("id") + " ammissibile"))

    elem.parentNode.appendChild(error);
}

function correctInput(elem){
    elem.removeAttribute("style");
    let error = document.getElementById("err_" + elem.getAttribute("id"));
    if(error != null){
        error.parentNode.removeChild(error);
    }
}

function correctField(li, icon){
    li.style.color =  "green";
    icon.setAttribute("class", "fa fa-check-circle");
}

function errorField(li, icon){
    li.style.color =  "red";
    icon.setAttribute("class", "fa fa-circle-o");
}
