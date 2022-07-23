let activeImage = 0;

$(document).ready(
    $("#left-row").click(function (){
        let size = document.querySelector("input#numero-foto").value;
        let oldImage =  document.getElementById("img-gallery-" + activeImage);

        if(activeImage == 0){
            activeImage = size - 1;
        }else{
            activeImage -= 1;
        }

        let newImage =  document.getElementById("img-gallery-" + activeImage);

        newImage.style.display = "block";
        oldImage.style.display = "none";
    }),
    $("#right-row").click(function (){
        let size = document.querySelector("input#numero-foto").value;
        let oldImage =  document.getElementById("img-gallery-" + activeImage);

        if(activeImage == size - 1){
            activeImage = 0;
        }else{
            activeImage = parseInt(activeImage) + 1;
        }

        let newImage =  document.getElementById("img-gallery-" + activeImage);

        newImage.style.display = "block";
        oldImage.style.display = "none";


    })







)