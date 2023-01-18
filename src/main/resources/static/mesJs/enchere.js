
function traitementLogin() {
    console.log("Traitement login");
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd").value;
    console.log("Identifiant=" + email + " & pwd=" + pwd);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            retour = JSON.parse(this.responseText);
            if (retour['message'] === "Login incorrecte") {
                document.getElementById("message").innerHTML = retour['message'];
            }
            else {
                window.location.replace("/accueil");
            }
        }
    }
    xmlhttp.open("POST", "http://localhost:4444/login/traitement?adminUser=" + email + "&pwd=" + pwd);
    xmlhttp.send();
}

function listeCategorie() {
    console.log("Liste categorie");
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            //document.getElementById("categorie").innerHTML=this.responseText;
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log("OK");
                    document.getElementById("contenu").innerHTML = null;
                    document.getElementById("contenu1").innerHTML = null;
                    document.getElementById("contenu").innerHTML = this.responseText;
                }
            }
            xmlhttp.open("GET", "http://localhost:4444/afficherListeCategorie");
            xmlhttp.send();
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/listecategorie");
    xmlhttp.send();
}

function ajouterNouvelleCategorie() {
    console.log("Nouvelle catégorie");
    var categorie = document.getElementById("nomCategorie").value;
    console.log("Nom=" + categorie);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            //document.getElementById("categorie").innerHTML=this.responseText;
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log("OK");
                    $('#exampleModal').modal('hide');
                    //document.getElementById("contenu").innerHTML = null;
                    document.getElementById("contenu").innerHTML = this.responseText;

                }
            }
            xmlhttp.open("GET", "http://localhost:4444/afficherListeCategorie");
            xmlhttp.send();
        }
    }
    xmlhttp.open("POST", "http://localhost:4444/insertcategorie?categorie=" + categorie);
    xmlhttp.send();
}