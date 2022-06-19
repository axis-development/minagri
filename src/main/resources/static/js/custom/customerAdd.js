$(document).ready(function () {
    $(document).on('click', '#saveSubmitData', function () {
        jQuery("#customerForm").validate({
            submitHandler: function (form) {
                var formData = $("#customerForm").serializeArray();
                var jsonData = parseFormData(formData);
                $.ajax({
                    url: "saveCustomer",
                    method: "POST",
                    data: JSON.stringify(jsonData),
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
    $(document).on('click', '#saveSubmitDataQuote', function () {
        jQuery("#customerForm").validate({
            submitHandler: function (form) {
                var formData = $("#customerForm").serializeArray();
                var jsonData = parseFormData(formData);
                $.ajax({
                    url: "saveCustomer",
                    method: "POST",
                    data: JSON.stringify(jsonData),
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    success: function (dataValue) {
                        window.location.href = "/quote/add?customerId="+dataValue;
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

function parseFormData(formData) {
    var jsonData = {};
    formData.forEach(function (data) {
        if (jsonData[data.name]) {
            if (!jsonData[data.name].push) {
                jsonData[data.name] = [jsonData[data.name]];
            }
            jsonData[data.name].push(data.value);
        } else {
            jsonData[data.name] = data.value;
        }
    });
    return jsonData;
}




