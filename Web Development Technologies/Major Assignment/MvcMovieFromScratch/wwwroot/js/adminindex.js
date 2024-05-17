$(document).ready(function () {
    $(".actionrow").children("button").on("click", function () {
        var id = $(this).attr("data-id");
        $.ajax({
            type: "PUT",
            url: "http://localhost:61270/api/Admin/"+id+"",
            success: function (response) {
                $("#statmsg").css("color:black");
                $("#statmsg").text("Successfully locked account " + id + "");

            },
            error: function (response) {
                $("#statmsg").css("color:red");
                $("#statmsg").text("Error occured in operation");
            }
        });
    });
});