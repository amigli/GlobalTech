

$(document).ready(
        function (){
            $("#burger").click(
                function () {
                    $("#menu-items").toggle();
                    $('#burger').toggleClass("active");
                }
            )
        }
    ,
    $("search-box").focusout(
        function (){
            let result = document.querySelector("#suggest");

            result.style.display = "none";
        }

    )
)

function getSuggerimento(){
    let content = document.querySelector("#search-box").value;
    let result = document.querySelector("#suggest");

    const xhttp = new XMLHttpRequest();

    result.innerHTML = '';

    xhttp.onreadystatechange =  function (){
        if(this.readyState == 4){
            if(this.status == 200){
                let response = xhttp.responseText;
                let products = JSON.parse(response);
                let ul = document.createElement("ul");

                ul.setAttribute("id", "result");

                if(products.length > 0){

                    for(let p of products){
                        let li = document.createElement("li");
                        let a = document.createElement("a");

                        a.setAttribute("href", "ricerca?key=" + p.nome);

                        let txt =  document.createTextNode(p.nome);

                        a.appendChild(txt);
                        li.appendChild(a);
                        ul.appendChild(li);
                    }


                    result.appendChild(ul);
                    result.style.display = "block";

                }else{
                    result.style.display = "none";
                }
            }else {
                window.alert("Si è verificato un errore, riprovare più tardi.")
            }
        }
    }

    xhttp.open("POST", "search-suggest");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    if(content.toString().length > 0 && !/^[\s]+$/.test(content.toString()))
        xhttp.send("key="+content);
    else
        result.style.display = "none";
}


