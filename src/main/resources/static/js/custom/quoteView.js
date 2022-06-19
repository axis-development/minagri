$(document).ready(function () {
    var cust = '<option value="">--Select Client --</option>';
    $.ajax({
        url:'getAllCustomers',
        success:function(data){
            $.each(data, function(key,value) {
                cust += '<option value="' + data[key].id +'">'+data[key].firstName +' '+ data[key].lastName + '</option>';
            });
            $('#customerList').html(cust);
        },
        error: function (xhr, textStatus, errorMessage) {

        }
    });
});



