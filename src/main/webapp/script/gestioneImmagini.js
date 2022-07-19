function rimuoviImmagini(idImage, idProdotto){
    const xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            let node =  document.querySelector("img#img-" + idImage);
            let parent =  node.parentNode;
            parent.parentNode.removeChild(parent);
            window.alert("Immagine rimossa con successo");
        }
    }

    xhttp.open("POST", "rimuovi-immagine")
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id_prodotto=" + idProdotto + "&id_image=" + idImage);
}