function displayEmail() {
    let email = sessionStorage.getItem('loggedUser');
    document.getElementById("displayLoggedUser").innerText = email;
    console.log(email);
}

function submitQuestion() {
    var question = {};
    question.title = $("#title").val();
    question.question = $("#question").val();
    question.email = sessionStorage.getItem('loggedUser');
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addQuestion",
        data: JSON.stringify(question),
        success: function (data) {
            $('#myModal').modal('hide');
            window.location.replace("http://localhost:8080/home");


        },
        error: function (e) {
            document.getElementById("errorMessage").innerText = e.responseText;
        }
    })

}

function logOut() {
    sessionStorage.removeItem('loggedUser');
}

function deleteQuestion(id) {
    var questionDTO = {};
    questionDTO.questionId = id;
    questionDTO.email = sessionStorage.getItem('loggedUser');
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/delete-question",
        data: JSON.stringify(questionDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");


        },
        error: function (e) {
            togglePopup();
            document.getElementById("errorMessages").innerText = e.responseText;
        }
    })

}

function togglePopup() {
    document.getElementById("popup-1").classList.toggle("active");
}

function editQuestion(id) {
    var questionDTO = {};
    var editQuestion="#question-" + id;
    var edtTitle="#title-" + id;
    questionDTO.question = document.getElementById(editQuestion).value;
    questionDTO.title = document.getElementById(edtTitle).value;
    questionDTO.email = sessionStorage.getItem('loggedUser');
    questionDTO.questionId = id;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/edit-question",
        data: JSON.stringify(questionDTO),
        success: function (data) {
            window.location.replace("http://localhost:8080/home");
        },
        error: function (e) {
            document.getElementById("#editError-" + id).innerText = e.responseText;

        }
    });
}
