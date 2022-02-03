function login() {
    let userdto = {};
    userdto.email = $("#email").val();
    userdto.password = $("#password").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/login",
        data: JSON.stringify(userdto),
        success: function (data) {
            let obj = JSON.stringify(data);
            window.location.replace("http://localhost:8080/home");
            window.sessionStorage.setItem('loggedUser', JSON.parse(obj).email);
            window.sessionStorage.setItem('loggedUserId', JSON.parse(obj).id);
        },
        error: function (xhr, status, error) {
            let errorMessage = xhr.responseJSON.message;
            $("#errorMessage").html(errorMessage);
        }
    })
}

