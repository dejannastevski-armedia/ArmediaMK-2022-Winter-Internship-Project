
    function login(){
    var userdto = {};
    userdto.email = $("#email").val();
    userdto.password = $("#password").val();
    $.ajax({
    type:"POST",
    contentType:"application/json",
    url:"/login",
    data:JSON.stringify(userdto),
    success: function(data){
    window.location.replace("http://localhost:8080/home");
},
    error: function (e){
    document.getElementById("errorMessage").innerText=e.responseText;
}
})
}

