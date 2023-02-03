
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
    xmlhttp.open("POST", "https://backofficeventeenchere-production-db7d.up.railway.app/login/traitement?adminUser=" + email + "&pwd=" + pwd);
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
            xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/afficherListeCategorie");
            xmlhttp.send();
        }
    }
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/listecategorie");
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
            xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/afficherListeCategorie");
            xmlhttp.send();
        }
    }
    xmlhttp.open("POST", "https://backofficeventeenchere-production-db7d.up.railway.app/insertcategorie?categorie=" + categorie);
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
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/logout");
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
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/validationCompte");
    xmlhttp.send();
}

function validation(id, etat) {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            listeDemande();
        }
    }
    xmlhttp.open("PUT", "https://backofficeventeenchere-production-db7d.up.railway.app/validerdemande/" + id + "/" + etat);
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
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/voircommission");
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
    xmlhttp.open("PUT", "https://backofficeventeenchere-production-db7d.up.railway.app/updatecommission/" + pourcentage);
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
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/afficherListeProduit");
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
            xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/afficherListeProduit");
            xmlhttp.send();
        }
    }
    xmlhttp.open("POST", "https://backofficeventeenchere-production-db7d.up.railway.app/insertProduit?categorie=" + categorie + "&produit=" + produit);
    xmlhttp.send();
}

function parametreEnchereAdmin() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
            document.getElementById("contenu").innerHTML = null;
            document.getElementById("contenu1").innerHTML = null;
            document.getElementById("contenu").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/listeEnchereAdmin");
    xmlhttp.send();
}

function insertEnchereAdmin() {
    var categorie = document.getElementById("categorie").value;
    var duree = document.getElementById("duree").value;
    console.log(categorie);
    console.log(duree);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
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
            xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/listeEnchereAdmin");
            xmlhttp.send();
        }
    }
    xmlhttp.open("POST", "https://backofficeventeenchere-production-db7d.up.railway.app/insertEnchereAdmin?categorie=" + categorie + "&duree=" + duree);
    xmlhttp.send();
}

function openModalUpdate(id) {
    console.log(id);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
            $('#exampleModal1').modal('show');
            var retour = JSON.parse(this.responseText);
            console.log(retour[0]["duree"]);
            document.getElementById("dureeToUpdate").value = retour[0]["duree"];
            document.getElementById("idToUpdate").value = id;
        }
    }
    xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/getInfoToUpdate?id=" + id);
    xmlhttp.send();
}

function updateEnchereAdmin() {
    var id = document.getElementById("idToUpdate").value;
    var nouvelleDuree = document.getElementById("dureeToUpdate").value;
    console.log(id);
    console.log(nouvelleDuree);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
            //document.getElementById("categorie").innerHTML=this.responseText;
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log("OK");
                    $('#exampleModal1').modal('hide');
                    //document.getElementById("contenu").innerHTML = null;
                    document.getElementById("contenu").innerHTML = this.responseText;

                }
            }
            xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/listeEnchereAdmin");
            xmlhttp.send();
        }
    }
    xmlhttp.open("PUT", "https://backofficeventeenchere-production-db7d.up.railway.app/updateEnchereAdminWs?duree=" + nouvelleDuree + "&id=" + id);
    xmlhttp.send();
}
