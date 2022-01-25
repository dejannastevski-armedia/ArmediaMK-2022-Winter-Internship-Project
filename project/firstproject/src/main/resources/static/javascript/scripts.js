function loginUser()
{
    var userDTO = {};
    userDTO.email = $("#username").val();
    userDTO.password = $("#password").val();
    $.ajax({
        type: "POST",
        contentType:"application/json",
        url: "/auth/login",
        //data:{email: email, password: password},
        data: JSON.stringify(userDTO),
        success: function () {
            window.location.replace("http://localhost:8080/auth/logged-in");
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}

function saveQuestion() {
    var questionDTO = {};
    questionDTO.title = $("#title").val();
    questionDTO.question = $("#questionform").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/save",
        data: JSON.stringify(questionDTO),
        success: function () {
            window.location.replace("http://localhost:8080/auth/logged-in");
        },
        error: function (e) {
            document.getElementById("questionsErrorMessage").innerHTML = e.responseText;
        }
    });
}