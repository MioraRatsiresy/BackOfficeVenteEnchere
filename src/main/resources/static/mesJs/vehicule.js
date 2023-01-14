
//voir liste des véhicules
function voirliste() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            listevehicule(this.responseText);
        }
    }
    xmlhttp.open("GET", "http://localhost:6942/Vehicules/liste");
    xmlhttp.send();
}
//afficher détail d'un véhicule
function afficherDetail(id) {

    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            VehiculeInformation(this.responseText);
            //document.getElementById("detail").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:6942/vehicules/" + id + "/detail/" + sessionStorage.getItem('TokenUtilisateur'));
    xmlhttp.send();
}
function listevehicule(info) {
    info = JSON.parse(info);
    var avoir = ['immatriculation'];
    var fonction = "afficherDetail";
    var table = this.constructHead(null, avoir, info["data"], fonction, 0);
    document.getElementById("vehicule").replaceChildren();
    document.getElementById("vehicule").appendChild(table);
}
function VehiculeInformation(detail) {
    detail = JSON.parse(detail);
    //detail
    var title_detail = ['Immatriculation', 'Modele', 'Puissance', 'Energie', 'Porte', 'Place', 'Vitesse Maximale', 'Categorie', 'Type'];
    var title_donnee = ['immatriculation', 'modele', 'puissance', 'energie', 'porte', 'place', 'vitesseMax', 'categorie', 'type'];

    //kilometrage
    var title_kilometrage = ['Date', 'Km début', 'Km fin'];
    var tete_kilometrage = ['date', 'kmdebut', 'kmfin'];

    var table = this.constructHead(title_detail, title_donnee, detail["data"], null, null);
    var table1 = this.constructHead(title_kilometrage, tete_kilometrage, detail["data"][0]["kilometrage"], null, null);
    document.getElementById("detail").replaceChildren();
    document.getElementById("detail").appendChild(table);
    document.getElementById("detail").appendChild(table1);
    let deconnexion = document.createElement('a');
    let textNode = document.createTextNode("Logout");
    deconnexion.appendChild(textNode);
    deconnexion.href = "#";
    deconnexion.onclick = function () {
        logout();
    }
    document.getElementById("detail").appendChild(deconnexion);


}

//deconnexion
function logout() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var retour = JSON.parse(this.responseText);
            if (retour['message'] === "Logout with success") {
                sessionStorage.clear();
                window.location.reload(true);
            }
            else {
                document.getElementById('erreur').innerHTML = "<strong>" + retour['message'] + "</strong>";
            }
        }
    }
    xmlhttp.open("GET", "http://localhost:6942/deconnexion");
    xmlhttp.send();
}

function constructHead(titre, donnetitre, donnee, fonction, place) {
    let table = document.createElement('table');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.className = "table table-striped table-responsive-md";
    table.style.width = "80%";
    table.border = "1";
    table.appendChild(thead);
    table.appendChild(tbody);

    // en-tete
    if (titre != null) {
        let row = document.createElement('tr');
        for (let i = 0; i < titre.length; i++) {
            let heading = document.createElement('th');
            heading.innerHTML = titre[i];
            row.appendChild(heading);
        }
        thead.appendChild(row);
    }

    //body
    if (fonction != null) {
        for (let j = 0; j < donnee.length; j++) {
            let row1 = document.createElement('tr');
            for (let i = 0; i < donnetitre.length; i++) {
                let tdbody = document.createElement('td');
                if (place == i) {
                    if (sessionStorage.getItem('TokenUtilisateur') == null) {
                        tdbody.innerHTML = "<a href='#' onclick='login(" + donnee[j]["id"] + ");'>" + donnee[j][donnetitre[i]] + "</a>";
                    }
                    else {
                        tdbody.innerHTML = "<a href='#' onclick='" + fonction + "(" + donnee[j]["id"] + ");'>" + donnee[j][donnetitre[i]] + "</a>";
                    }
                }
                else {
                    tdbody.innerHTML = donnee[j][donnetitre[i]];
                }
                row1.appendChild(tdbody);
            }
            tbody.appendChild(row1);
        }
    }
    else {
        for (let j = 0; j < donnee.length; j++) {
            let row1 = document.createElement('tr');
            for (let i = 0; i < donnetitre.length; i++) {
                let tdbody = document.createElement('td');
                tdbody.innerHTML = donnee[j][donnetitre[i]];
                row1.appendChild(tdbody);
            }
            tbody.appendChild(row1);
        }
    }
    return table;
}

/***********************************************************************************************/
var idVehicule = 0;
function login(id) {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            detail.innerHTML = this.responseText;
        }
    }
    idVehicule = id;
    xmlhttp.open("GET", "http://localhost:6942/Vehicules/login");
    xmlhttp.send();
}
function traitementLogin() {
    let email = document.getElementById('email').value;
    let pwd = document.getElementById('pwd').value;
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("OK");
            console.log(this.responseText);
            retour = JSON.parse(this.responseText);
            if (retour['message'] === "Login correcte") {
                sessionStorage.setItem("TokenUtilisateur", retour['token']);
                console.log(retour['token']);
                detail.innerHTML = "";
                afficherDetail(idVehicule);
            }
            else {
                document.getElementById('erreur').innerHTML = "<strong>" + retour['message'] + "</strong>";
            }
        }
    }
    xmlhttp.open("POST", "http://localhost:6942/Vehicule/traitementLogin?email=" + email + "&pwd=" + pwd);
    xmlhttp.send();
}

/*************************************** Liste vehicule expiré dans 3 mois ***********************************************/

function viewLogin() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            detail.innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "http://localhost:6942/Vehicules/login");
    xmlhttp.send();
}

function voirListeVehiculeAssuranceExpire3Mois() {
    console.log("Liste des vehicules dont l'assurance est expiré dans 3 mois");
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // console.log(this.responseText);
            //VehiculeInformation(this.responseText);
            //document.getElementById("detail").innerHTML = this.responseText;
            vehicule.innerHTML = null;
            detail.innerHTML = null;
            liste = JSON.parse(this.responseText);
            for (var item = 0; item < liste["data"].length; item++) {
                vehicule.innerHTML = vehicule.innerHTML + "<div class='panel panel-info'>" +
                    "<div class='panel-heading'><a onclick='afficherDetail(" + liste["data"][item]["id"] + ");'>" +
                    liste["data"][item]["immatriculation"] + " " + liste["data"][item]["categorie"] + " " + liste["data"][item]["modele"] +
                    "</a></div>" +
                    "<div class='panel-body'><div><b>Date de paiement de l'assurance :</b> " + liste["data"][item]["dateAssurance"] + "</div></div>" +
                    "<div class='panel-body'><div><b>Date d'expiration de l'assurance :</b> " + liste["data"][item]["dateAssurance"] + "</div></div>" +
                    "</div>";
            }
            console.log(liste);
        }
    }
    if (sessionStorage.getItem('TokenUtilisateur') == null) {
        viewLogin();
    }
    else {
        xmlhttp.open("GET", "http://localhost:6942/vehicule/assurance/expiration/3/" + sessionStorage.getItem('TokenUtilisateur'));
        xmlhttp.send();
    }
}
