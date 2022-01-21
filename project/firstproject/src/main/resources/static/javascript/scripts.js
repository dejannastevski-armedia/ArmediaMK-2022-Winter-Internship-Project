function loginUser()
{
    var userDTO = {};
    userDTO.email = $("#username").val();
    userDTO.password = $("#password").val();
    $.ajax({
        type: "POST",
        contentType:"application/json",
        url: "/auth/login",
        //data:{email: email, password: password},
        data: JSON.stringify(userDTO),
        success: function (){
            window.location.replace("http://localhost:8080/auth/logged-in");
        },
        error: function (e){
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}