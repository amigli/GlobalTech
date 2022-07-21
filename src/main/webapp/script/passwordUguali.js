function validateEqualsPassword(){

        let check = document.getElementById("newPassword-check");

        check.style.display = "block";

        let p1 =  document.getElementById("newPassword1").value;
        let p2 = document.getElementById("newPassword2").value;

        let error = true;

        let li = document.getElementById("uguali");
        let iconUguali = document.querySelector("li#uguali > i");

        if(p1==p2)
            correctField(li, iconUguali);
        else{
            errorField(li, iconUguali);
            error = false;
        }

        return error;


    function correctField(li, icon){
        li.style.color =  "green";
        icon.setAttribute("class", "fa fa-check-circle");
    }

    function errorField(li, icon){
        li.style.color =  "red";
        icon.setAttribute("class", "fa fa-circle-o");
    }



}