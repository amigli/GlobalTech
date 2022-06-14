$(document).ready(
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