$(document).ready(function () {
    $.ajax({
        url: 'fetchCustomers',
        method: "GET",
        dataType: 'json',
        success: function (customers) {
            var table = $('#dataTable').dataTable({
                "aaData": customers,
                "columns": [
                    {
                        "data": "id",
                        "render": function (data, type, row) {
                            return '<td><input class="checkAll"  type="checkbox" value="'+data+'"></td>';
                        }
                    },
                    {
                        "data": "firstName",
                        "render": function (data, type, row) {
                            return '<td>'+data+' '+row.lastName+'</td>';
                        }
                    },
                    {"data": "id"},
                    {"data": "companyName"},
                    {"data": "currentUser"},
                    {"data": "otherContact"},
                    {"data": "clientSince"},
                    {"data": "recurrence"},
                    {"data": "critique"},
                    {
                        "data": "id",
                        "render": function (data, type, row) {
                            return '<td><div class="visible-md visible-lg visible-sm visible-xs action-buttons">' +
                                '<a class="green" href="/customer/view/'+row.id+'" title="View Customer"><i class=" icon-eye-open bigger-130"></i></a>' +
                                '<a class="blue" href="/customer/edit/'+row.id+'" title="Edit Customer"><i class="icon-edit bigger-130"></i></a>' +
                                '<a class="orange" href="/quote/add/'+row.id+'" title="Ajouter Devis"><i class=" icon-plus bigger-130"></i></a>';
                        }
                    }
                ],
                "bLengthChange": false, //thought this line could hide the LengthMenu
                "bInfo": false,
                "bFilter": true
            });
            $("#searchUser").click(function () {
                $('#userTable').DataTable().draw();

            });
        }
    });
});



