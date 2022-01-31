function submitAnswer() {
    var answerDTO = {};
    answerDTO.answer = $("#answer").val();
    answerDTO.email = sessionStorage.getItem('loggedUser');
    answerDTO.questionId = $("#id").text();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/add-answer",
        data: JSON.stringify(answerDTO),
        success: function (data) {
            $('#myModal').modal('hide');

        },
        error: function (e) {
            document.getElementById("errorMessage").innerText = e.responseText;
        }
    })

}