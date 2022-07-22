$(document).ready(
    $(document).ready(
        function (){
            $("#burger").click(
                function () {
                    $("#menu-items").toggle();
                    $('#burger').toggleClass("active");
                }
            )
        }
    ),
    $("#search-box").keyup(
        function(){
            let content = $("#search-box").val();
            let result = $("#suggest");

            if(content == "" || (/^[\s]+$/).test(content)){
                result.html("");
            }else if(content.toString().startsWith("a") || content.startsWith("A")){
                result.load("./script/Test1.html");
            }else{
                result.load("./script/Test2.html");
            }
        }
    )
)

function mostraMenu(){
    const menu =  document.querySelector("#menu-items");
    const burger = document.getElementById("burger");
    menu.classList.toggle("active");
}
