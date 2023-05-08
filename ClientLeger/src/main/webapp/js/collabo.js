let utilisateur;

function getUtilisateur() {
    utilisateur = sessionStorage.getItem("user");
    console.log(utilisateur);
}

$(document).ready(function() {
    getUtilisateur();
})