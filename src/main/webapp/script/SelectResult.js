let searchbox =  document.querySelector("#search-box");

searchbox.addEventListener("keyup", displayResult)

function displayResult(){
    const xhttp =  new XMLHttpRequest();
    let suggestBox = document.getElementById("suggest");

    xhttp.onreadystatechange =  function (){
        if(this.readyState == 4 && this.status == 200){

            suggestBox.innerHTML = this.responseText;
        }
    }

    if(searchbox.value.toString()== "" ||(/^[\s]+$/).test(searchbox.value.toString())){
        suggestBox.innerHTML = "";
    }else{
        if(searchbox.value.toString().startsWith("a") || searchbox.value.toString().startsWith("A") )
            xhttp.open("GET", "./script/Test1.html", true);
        else
            xhttp.open("GET", "./script/Test2.html", true);

        xhttp.send();
    }


}