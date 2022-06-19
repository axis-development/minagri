$(document).ready(function () {
    $.ajax({
        url: window.location.origin+"/quote/fetchCustomer/",
        method: "GET",
        dataType: 'json',
        success: function (customer) {
            $('#customerName').append(customer.firstName+ " "+customer.lastName);
            $('#customerCompany').append(customer.companyName);
            $('#customerStatus').append(customer.status);
            $('#customerMail').append(customer.mail);
            $('#customerPhone').append(customer.mobile);
            $('#customerOtherContact').append(customer.otherContact);
            $('#customerOtherContactPoistion').append(customer.otherContactPosition);
            $('#customerBirthDate').append(customer.birthDate);
            if(customer.otherContactPosition === 'Vendeur') {
                $('#customerVendor').append(customer.otherContact);
            }

        }
    });

});



