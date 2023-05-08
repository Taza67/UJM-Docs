function getUtilisateur() {
    let userDataElem = document.querySelector("#user-data");
    let userJsonString = userDataElem.getAttribute('data-user');
    let user = JSON.parse(userJsonString);
    console.log(user.id);
    console.log(user.username);
}

$(document).ready(function() {
    getUtilisateur();
})