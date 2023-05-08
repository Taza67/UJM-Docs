function getUtilisateur() {
    let userDataElem = document.querySelector("#user-data");
    let encodedUserJsonString = userDataElem.getAttribute('data-user');
    console.log(encodedUserJsonString);
    let userJsonString = decodeURIComponent(encodedUserJsonString);
    console.log(userJsonString);
}

getUtilisateur();