function saveAnswer()
{
    var answerDTO = {};
    answerDTO.answer = $("#answerform").val();
    answerDTO.email = JSON.parse(sessionStorage.loggedInUser).email;
    answerDTO.questionId = $("#questionID").text();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/answer/save",
        data: JSON.stringify(answerDTO),
        success: function () {
            window.location.replace("http://localhost:8080/answer/view-answer/" + answerDTO.questionId)
        },
        error: function (e) {
            document.getElementById("answersErrorMessage").innerHTML = e.responseText;
        }
    });
}