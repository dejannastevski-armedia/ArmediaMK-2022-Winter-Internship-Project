function logIn() {
    var UserDTO = {};
    UserDTO.email = $("#email").val();
    UserDTO.password = $("#password").val();
    $.ajax({
        type:"POST",
        contentType:"application/json",
        url:"/auth/login",
        //  data:{email: email, password: password},
        data: JSON.stringify(UserDTO),
        success: function (data){
            window.location.replace("http://localhost:8080/auth/home");
        },
        error: function(e){
            document.getElementById("errorMessage").innerHTML=e.responseText;
        }
    });
}