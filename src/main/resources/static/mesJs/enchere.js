
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

function logout() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            retour = JSON.parse(this.responseText);
            if (retour['message'] === "Logout with success") {
                window.location.replace("/index");
            }
            else {
            }
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/logout");
    xmlhttp.send();
}


function listeDemande() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            document.getElementById("contenu1").innerHTML = null;
            document.getElementById("contenu").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/validationCompte");
    xmlhttp.send();
}

function validation(id, etat) {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            listeDemande();
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/validerdemande/" + id + "/" + etat);
    xmlhttp.send();
}
function commission() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("contenu1").innerHTML = null;
            document.getElementById("contenu").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/voircommission");
    xmlhttp.send();
}

function updateCommission() {
    var pourcentage = document.getElementById("pourcentage").value;
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            $('#commission').modal('hide');
            commission();
        }
    }
    xmlhttp.open("PUT", "http://localhost:4444/updatecommission/" + pourcentage);
    xmlhttp.send();
}

function listeProduit() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
            document.getElementById("contenu").innerHTML = null;
            document.getElementById("contenu1").innerHTML = null;
            document.getElementById("contenu").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:4444/afficherListeProduit");
    xmlhttp.send();
}

function ajouterNouveauProduit() {
    var produit = document.getElementById("nomProduit").value;
    var categorie = document.getElementById("idCategorie").value;
    console.log(produit);
    console.log(categorie);
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
            xmlhttp.open("GET", "http://localhost:4444/afficherListeProduit");
            xmlhttp.send();
        }
    }
    xmlhttp.open("POST", "http://localhost:4444/insertProduit?categorie=" + categorie +"&produit="+produit);
    xmlhttp.send();
}
