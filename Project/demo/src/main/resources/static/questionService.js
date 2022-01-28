function displayEmail() {
    let email = sessionStorage.getItem('loggedUser');
    document.getElementById("displayLoggedUser").innerText = email;
    console.log(email);
}

function submitQuestion() {
    var question = {};
    question.title = $("#title").val();
    question.question = $("#question").val();
    question.email = sessionStorage.getItem('loggedUser');
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addQuestion",
        data: JSON.stringify(question),
        success: function (data) {
            $('#myModal').modal('hide');
            window.location.replace("http://localhost:8080/home");

        },
        error: function (e) {
            document.getElementById("errorMessage").innerText = e.responseText;
        }
    })

}

function logOut() {
    sessionStorage.removeItem('loggedUser');
}
