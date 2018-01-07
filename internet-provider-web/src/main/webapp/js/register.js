function checkform() {

    var pass1 = document.getElementById("pass1").value;
    var pass2 = document.getElementById("pass2").value;

    if (pass1 !== pass2) {
        var passErrorBlock = document.getElementById("passErrorBlock");
        var passErrorMessage = document.getElementById("passErrorHiddenId").value;
        passErrorBlock.innerHTML = passErrorMessage;
        return false;
    }
}
