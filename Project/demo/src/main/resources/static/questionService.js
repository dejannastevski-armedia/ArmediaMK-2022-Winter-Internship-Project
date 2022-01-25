function submitQuestion(){
    var question = {};
    question.title = $("#title").val();
    question.question = $("#question").val();
    $.ajax({
        type:"POST",
        contentType:"application/json",
        url:"/addQuestion",
        data:JSON.stringify(question),
        success: function(data){
            $('#myModal').modal('hide');
        },
        error: function (e){
            document.getElementById("errorMessage").innerText=e.responseText;
        }
    })

}
