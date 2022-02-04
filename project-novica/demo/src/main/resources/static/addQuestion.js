$(document).ready(function () {
    if (sessionStorage.getItem("user") == null) {
        $("#email").html("You are not logged in");
    } else {
        $("#email").html("You are logged in as: " + JSON.parse(sessionStorage.getItem("user")).email);
    }
});

function addQuestion() {
    var questionDTO = {};
    questionDTO.question = $("#question").val();
    questionDTO.title = $("#title").val();
    questionDTO.email = JSON.parse(sessionStorage.getItem("user")).email;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/add-question",
        data: JSON.stringify(questionDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}

function deleteQuestion(id) {
    var questionDTO = {};
    questionDTO.question = $("#question").val();
    questionDTO.title = $("#title").val();
    questionDTO.email = JSON.parse(sessionStorage.getItem("user")).email;
    questionDTO.questionId = id;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/delete-question",
        data: JSON.stringify(questionDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");
        },
        error: function (e) {
            alert(e.responseJSON.message);
        }
    });
}