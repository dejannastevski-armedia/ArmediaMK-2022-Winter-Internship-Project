$(document).ready(function () {
    $("#submit").click(function (e) {
        let user = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/auth/login",
            data: JSON.stringify(user),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/home";
            },
            error: function (data) {
                $("#errorMessage").html(data.responseText);
            }
        });
    });
});