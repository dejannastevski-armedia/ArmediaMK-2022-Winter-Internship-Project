$(document).ready(function () {
    $("#submit").click(function (e) {
        let user = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/auth/login-successful",
            data: JSON.stringify(user),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/auth/home";
            },
            error: function (data) {
                $("#errorMessage").html(data.responseText);
            }
        });
    });
});