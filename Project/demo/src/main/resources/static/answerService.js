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
            window.location.replace("http://localhost:8080/view-answer/" + answerDTO.questionId);

        },
        error: function (e) {
            document.getElementById("errorMessage").innerText = e.responseText;
        }
    })

}

function downVotesAnswer(id) {
    var answerDTO = {};
    answerDTO.answerId = id;
    answerDTO.questionId = $("#id").text();
    answerDTO.userId= sessionStorage.getItem('loggedUserId');
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/increment-down/" + answerDTO.answerId + "/" + answerDTO.questionId,
        data: JSON.stringify(answerDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/view-answer/" + answerDTO.questionId);

        },
        error: function (e) {

        }
    })
}

function upVotesAnswer(id) {
    var answerDTO = {};
    answerDTO.answerId = id;
    answerDTO.questionId = $("#id").text();
    answerDTO.userId= sessionStorage.getItem('loggedUserId');
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/increment-up/"  + answerDTO.answerId + "/" + answerDTO.questionId,
        data: JSON.stringify(answerDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/view-answer/" + answerDTO.questionId);
        },
        error: function (e) {

        }
    })
}

