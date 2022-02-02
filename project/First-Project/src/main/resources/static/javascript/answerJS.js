$(document).ready(function () {
    if (sessionStorage.getItem("loggedUser") == null) {
        $("#userLogged").html("You are not logged in");
    } else {
        $("#userLogged").html("You are logged in as: " + sessionStorage.getItem("loggedUser"));
    }

    $("#logout").click(function () {
        sessionStorage.removeItem("loggedUser");
    })

    $(".CreatedDate").each(function () {
        let createdDate = $(this).text();
        let createdDateSplit = createdDate.split("T");
        $(this).text(createdDateSplit[0]);
    })

    $("#submitAnswer").click(function () {
        let answer = {
            answer: $("#answer").val(), email: sessionStorage.getItem("loggedUser"), id: $("#questionId").text()
        };
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/answer-successful",
            data: JSON.stringify(answer),
            contentType: "application/JSON",
            success: function (data) {
                let message = "You have answered the question. Thanks :)";
                $("#SuccessfullyAnsweredQuestion").html(message);
                document.getElementById('id01').style.display = 'none';
                $("#answer").val("");
                let id = $("#questionId").text();
                window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + id;
            },
            error: function (xhr, status, error) {
                let errorMessage = xhr.responseJSON.message;
                $("#errorMessage").html(errorMessage);
            }
        });
    });

    $(".UpVoteButton").click(function () {
        let questionId = $("#questionId").text();
        let userAnswerStatus = {
            answerId: $(this).text(), userEmail: sessionStorage.getItem("loggedUser")
        }
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/up-vote-answer",
            data: JSON.stringify(userAnswerStatus),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + questionId;
            },
            error: function (xhr, status, error) {
            }
        })
    })

    $(".DownVoteButton").click(function () {
        let questionId = $("#questionId").text();
        let userAnswerStatus = {
            answerId: $(this).text(), userEmail: sessionStorage.getItem("loggedUser")
        }
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/down-vote-answer",
            data: JSON.stringify(userAnswerStatus),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + questionId;
            },
            error: function (xhr, status, error) {
            }
        })
    })

    $(".isDownVoted").parent().parent().find("button").attr("id", "isClicked");

    $(".isUpVoted").parent().parent().find("button").attr("id", "isClicked");
});