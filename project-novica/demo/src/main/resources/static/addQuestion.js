function addQuestion() {
    //alert("vo funkcija");
    var questionDTO = {};
    questionDTO.question = $("#question").val();
    questionDTO.title = $("#title").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/auth/home",
        //  data:{question: question, title: title},
        data: JSON.stringify(questionDTO),
        success: function (data) {
            alert("vo success");
            window.location.replace("http://localhost:8080/auth/home");
        },
        error: function (e) {
            // alert("vo error");
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}