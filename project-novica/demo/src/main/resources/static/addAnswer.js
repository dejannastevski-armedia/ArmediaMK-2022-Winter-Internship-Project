function addAnswer() {
    var answerDTO = {};
    answerDTO.answer = $("#answer").val();
    answerDTO.email = JSON.parse(sessionStorage.getItem("user")).email;
    answerDTO.questionId = $("#questionId").text();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/answer/add-answer",
        data: JSON.stringify(answerDTO),
        success: function (data) {
            $('#myModal').modal('hide');
        },
        error: function (e) {
            document.getElementById("errorMessage").innerHTML = e.responseText;
        }
    });
}