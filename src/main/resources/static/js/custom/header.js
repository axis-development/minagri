$(document).ready(function () {
        $.ajax({
        url: window.location.origin+'/user/currentUser',
        method: "GET",
        dataType: 'json',
        success: function (currentUser) {
        $("#currentUser").append(currentUser.firstName+' '+currentUser.lastName);
    }
    });
});



