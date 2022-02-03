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

function thumbsUp(answerId) {
    var questionId = $("#questionId").text();
    var userAnswerDTO = {};
    userAnswerDTO.answerId = answerId;
    userAnswerDTO.userId = JSON.parse(sessionStorage.getItem("user")).id;
    $.ajax({
        type: "POST",
        url: "/answer/up-vote-answer",
        data: JSON.stringify(userAnswerDTO),
        contentType: "application/JSON",
        success: function () {
            window.location = "http://localhost:8080/answer/answer/" + questionId;
        },
        error: function () {
        }
    })
}

function thumbsDown(answerId) {
    var questionId = $("#questionId").text();
    var userAnswerDTO = {};
    userAnswerDTO.answerId = answerId;
    userAnswerDTO.userEmail = JSON.parse(sessionStorage.getItem("user")).email;
    $.ajax({
        type: "POST",
        url: "/answer/down-vote-answer",
        data: JSON.stringify(userAnswerDTO),
        contentType: "application/JSON",
        success: function () {
            window.location = "http://localhost:8080/answer/answer/" + questionId;
        },
        error: function () {
        }
    })
}