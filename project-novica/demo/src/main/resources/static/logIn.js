function logIn() {
    var userDTO = {};
    userDTO.email = $("#email").val();
    userDTO.password = $("#password").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/auth/login",
        //  data:{email: email, password: password},
        data: JSON.stringify(userDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");
            window.sessionStorage.setItem('user', JSON.stringify(data));
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseJSON.message;

        }
    });
}