$(document).ready(function () {
    if (sessionStorage.getItem("loggedUser") == null) {
        $("#userLogged").html("You are not logged in");
    } else {
        $("#userLogged").html("You are logged in as: " + sessionStorage.getItem("loggedUser"));
    }

    $("#logout").click(function () {
        sessionStorage.removeItem("loggedUser");
    })

    $("#submitAnswer").click(function () {
        let answer = {
            answer: $("#answer").val(),
            email: sessionStorage.getItem("loggedUser"),
            id: $("#questionId").text()
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/answer-successful",
            data: JSON.stringify(answer),
            contentType: "application/JSON",
            success: function (data) {
                let message = "You have answered the question. Thanks :)";
                $("#SuccessfullyAnsweredQuestion").html(message);
                document.getElementById('id01').style.display = 'none';
                $("#answer").val("");
                let id = $("#questionId").text();
                window.location = "http://localhost:8080/answers/view-answer/" + id;
            },
            error: function (xhr, status, error) {
                let errorMessage = xhr.responseJSON.message;
                $("#errorMessage").html(errorMessage);
            }
        });
    });
});