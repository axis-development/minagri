$(document).ready(function () {
    $(document).on('click', '#saveSubmitData', function () {
        jQuery("#insertForm").validate({
            submitHandler: function (form) {
                //var formData = JSON.stringify($("#insertForm").serializeArray());
                var jsonObj = {};
                jsonObj ["bizName"] = $('#bizName').val();
                jsonObj ["email"] = $('#email').val();
                jsonObj ["phone"] = $('#phone').val();
                jsonObj ["gsm"] = $('#gsm').val();
                jsonObj ["vatNumber"] = $('#vatNumber').val();
                var jsonAdd = {};
                jsonAdd ["addressType"] = $('#addressType').val();
                jsonAdd ["streetNumber"] = $('#streetNumber').val();
                jsonAdd ["streetName"] = $('#streetName').val();
                jsonAdd ["city"] = $('#city').val();
                jsonAdd ["country"] = $('#country').val();
                jsonAdd ["postalCode"] = $('#postalCode').val();
                var isDefaultBool = ($('#isDefault').val() === 'on');
                jsonAdd ["isDefault"] = isDefaultBool;
                jsonObj ["address"] = jsonAdd;
                $.ajax({
                    url: "saveBizProfil",
                    method: "POST",
                    data: JSON.stringify(jsonObj),
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    success: function (dataValue) {
                        $('#alertSuccessMessage').modal('show');
                    },
                    error: function (xhr, textStatus, errorMessage) {
                       $(".modal-body").html( "<p>"+xhr.responseText+"</p>" );
                        $('#alertErrorMessage').modal('show');
                    }
                });
            }
        })
    });
});


