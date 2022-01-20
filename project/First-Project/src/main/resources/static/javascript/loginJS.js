$(document).ready(function () {
    $("#submit").click(function (e) {
        let User = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/auth/login-successful",
            //data: {'email': User.email, 'password': User.password},
            data: JSON.stringify(User),
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