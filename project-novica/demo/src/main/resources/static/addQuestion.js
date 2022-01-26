$(document).ready(function () {
    if (sessionStorage.getItem("email") == null) {
        $("#email").html("You are not logged in");
    } else {
        $("#email").html("You are logged in as: " + sessionStorage.getItem("email"));
    }
});

function addQuestion() {
    var questionDTO = {};
    questionDTO.question = $("#question").val();
    questionDTO.title = $("#title").val();
    questionDTO.email = sessionStorage.getItem("email");
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/question/add-question",
        //  data:{question: question, title: title},
        data: JSON.stringify(questionDTO),
        success: function (data) {
            // alert("vo success");
            window.location.replace("http://localhost:8080/home");
        },
        error: function (e) {
            // alert("vo error");
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}