$(document).ready(function () {
    $("#submitQuestion").click(function (e) {
        let question = {
            title: $("#title").val(),
            question: $("#question").val()
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/questions/ask-question",
            data: JSON.stringify(question),
            contentType: "application/JSON",
            success: function (data) {
                let message="You have asked a question. Now wait someone to answer it :)";
                $("askedQuestion").html(message);
                document.getElementById('id01').style.display='none';
                $("#title").val("");
                $("#question").val("");
            },
            error: function (data) {
                $("#errorMessage").html(data.responseText);
            }
        });
    });
});