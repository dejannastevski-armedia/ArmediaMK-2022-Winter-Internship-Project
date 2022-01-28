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
        success: function(){
            $('#myModal').modal('hide');
        },
        error: function (e){
            document.getElementById("answersErrorMessage").innerHTML = e.responseText;
        }
    });
}