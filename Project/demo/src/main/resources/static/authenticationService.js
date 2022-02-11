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
            window.sessionStorage.setItem('loggedUser', JSON.parse(obj).email);
            window.sessionStorage.setItem('loggedUserId', JSON.parse(obj).id);
            window.sessionStorage.setItem("role",JSON.parse(obj).role);
            if( window.sessionStorage.getItem("role")!=='admin'){
                window.location.replace("http://localhost:8080/home");
            }
            else {
                window.location.replace("http://localhost:8080/admin");
            }

        },
        error: function (xhr, status, error) {
            let errorMessage = xhr.responseJSON.message;
            $("#errorMessage").html(errorMessage);
        }
    })
}

