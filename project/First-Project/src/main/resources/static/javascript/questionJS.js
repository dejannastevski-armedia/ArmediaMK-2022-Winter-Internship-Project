$(document).ready(function () {
    if (sessionStorage.getItem("loggedUser") == null) {
        $("#userLogged").html("You are not logged in");
    } else {
        $("#userLogged").html("You are logged in as: " + sessionStorage.getItem("loggedUser"));
    }
    $("#logout").click(function () {
        sessionStorage.removeItem("loggedUser");
    })
    $("#submitQuestion").click(function () {
        let question = {
            title: $("#title").val(),
            question: $("#question").val(),
            email: sessionStorage.getItem("loggedUser")
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/questions/ask-question",
            data: JSON.stringify(question),
            contentType: "application/JSON",
            success: function (data) {
                let message = "You have asked a question. Now wait someone to answer it :)";
                $("#SuccessfullyAskedQuestion").html(message);
                document.getElementById('id01').style.display = 'none';
                $("#title").val("");
                $("#question").val("");
            },
            error: function (data) {
                $("#errorMessage").html(data.responseText);
            }
        });
    });
});