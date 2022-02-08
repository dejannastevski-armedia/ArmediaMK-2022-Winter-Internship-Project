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
        type: "DELETE",
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

function editQuestion(id) {
    var questionDTO = {};
    var question1 = "#question-" + id;
    var title1 = "#title-" + id;
    questionDTO.question = document.getElementById(question1).value;
    questionDTO.title = document.getElementById(title1).value;
    questionDTO.email = JSON.parse(sessionStorage.getItem("user")).email;
    questionDTO.questionId = id;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/edit-question",
        data: JSON.stringify(questionDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");
        },
        error: function (e) {
            document.getElementById("#editError-" + id).innerText = e.responseText;
        }
    });
}