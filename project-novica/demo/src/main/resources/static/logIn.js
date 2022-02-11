$(document).ready(function () {
    if (sessionStorage.getItem("user") == null) {
        $("#email").html("You are not logged in");
    } else {
        $("#email").html("You are logged in as: " + JSON.parse(sessionStorage.getItem("user")).email);
    }
});
function logIn() {
    var userDTO = {};
    userDTO.email = $("#email").val();
    userDTO.password = $("#password").val();
    userDTO.role = $("role").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/auth/login",
        //  data:{email: email, password: password},
        data: JSON.stringify(userDTO),
        success: function (data) {
            window.sessionStorage.setItem('user', JSON.stringify(data));
            let obj = JSON.stringify(data);
            window.sessionStorage.setItem("role", JSON.parse(obj).role.name);
            if (window.sessionStorage.getItem("role") == 'ADMIN') {
                window.location.replace("http://localhost:8080/auth/admin");
            } else {
                window.location.replace("http://localhost:8080/home");
            }
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseJSON.message;

        }
    });
}

$.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/login",
    data: JSON.stringify(userdto),
    success: function (data) {
        let obj = JSON.stringify(data);
        window.sessionStorage.setItem('loggedUser', JSON.parse(obj).email);
        window.sessionStorage.setItem('loggedUserId', JSON.parse(obj).id);
        window.sessionStorage.setItem("role", JSON.parse(obj).role);
        if (window.sessionStorage.getItem("role") !== 'admin') {
            window.location.replace("http://localhost:8080/home");
        } else {
            window.location.replace("http://localhost:8080/admin");
        }

    },
    error: function (xhr, status, error) {
        let errorMessage = xhr.responseJSON.message;
        $("#errorMessage").html(errorMessage);
    }
})