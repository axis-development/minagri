$(function () {
    function updateSelectData($data, $this) {
        //$this.closest('.input-group').find('select').append('<option value="' + $data.id + '">' 
        //+ $data.value + '</option>');
        $selectElem = $this.closest('.input-group').find('select.renderSelect');
        /*$multiple = $selectElem.attr('data-multiple')
         if ($multiple.length) {
         $.each($('' + $selectElem.attr('data-class') ), function() )
         }*/
        resetSelectOption($selectElem);
        $selectElem.append($data);
        if ($selectElem.hasClass('chosen-select')) {
            $selectElem.trigger('chosen:updated');
        }
    }

    $('.saveToDatabase').click(function (e) {

        //e.preventDefault();    
        $('#modalDataVal').attr('code', $(this).attr('code'));
        //$('#modal-data-form').modal('show');
    });

    /*$('#modal-data-form').on('hide', function () {
     $('#modalDataVal').attr('code', '');
     });*/

    $dataSaved = true;
    $('.modalSaveDataButton').click(function (e) {

        e.preventDefault();
        if ($dataSaved) {
            $this = $(this);
            $modalDataVal = $('#modalDataVal');
            $code = $modalDataVal.attr('code');

            if ($code === 'GENERALDISCOUNT' && $modalDataVal.attr('value') > 100) {
                displayErrorMessage($dicountcannotgreaterthanhun);
                return false;
            }

            $dataSaved = false;
            $.ajax({
                method: 'post',
                url: '/setup/add-ajax',
                dataType: 'json',
                data: {
                    'pk': $code, 'value': $modalDataVal.val(),
                    'return-data': true
                },
                validate: function (v) {
                    if ($.trim(v) == '') {
                        return 'Required field!';
                    }
                },
                success: function (response) {
                    if (!response.success) {
                        displayErrorMessage(response.msg);
                        return false;
                    }
                    $('#modalDataVal').val('').attr('code', '');
                    $('#modal-data-form').modal('hide');
                    updateSelectData(getJsonData(response.data), $('.saveToDatabase[code="' + $code + '"]'));
                },
                error: function () {
                    console.log('error');
                },
                complete: function () {
                    $dataSaved = true;
                }
            });
        }
    });

    $('.modalSupplierSaveDataButton').click(function (e) {

        e.preventDefault();
        if ($dataSaved) {
            $this = $(this);
            $modalDataVal = $('#modalDataVal');
            $code = $modalDataVal.attr('code');

            $supplierName = $('#supplierName').val();
            $supplierCode = $('#supplierCode').val();

            if ($supplierName == '' || $supplierCode == '') {
                displayErrorMessage($allfieldrequiredmsg);
                return false;
            }

            $dataSaved = false;
            $.ajax({
                method: 'post', url: '/supplier/add',
                dataType: 'json',
                data: {
                    'biz_name': $supplierName, 'supplier_code': $supplierCode,
                    'return-data': true, 'ajax-submit': true
                },
                validate: function (v) {
                    if ($.trim(v) == '') {
                        return 'Required field!';
                    }
                },
                success: function (response) {
                    if (!response.success) {
                        displayErrorMessage(response.msg);
                        return false;
                    }
                    $('#supplierName').val('');
                    $('#supplierCode').val('');
                    $('#supplier-modal-data-form').modal('hide');
                    $('.supplierRow .refreshDataField').trigger('click');
                    //updateSelectData(getJsonData(response.data), $('.saveToDatabase[code="' + $code + '"]'));
                },
                error: function () {
                    console.log('error');
                },
                complete: function () {
                    $dataSaved = true;
                }
            });
        }
    });


    $('.modalSaveLeadStageButton').click(function (e) {
        e.preventDefault();
        $this = $(this);
        $modalDataVal = $('#modalDataVal');

        $opportunityTypeId = $('#opportunityTypeId').val();
        $leadstageValue = $('#leadstageValue').val();
        $leadStageId = $('#leadStageId').val();

        if ($opportunityTypeId == '' || $leadstageValue == '') {
            displayErrorMessage($allfieldrequiredmsg);
            return false;
        }

        showLoader();
        $.ajax({
            method: 'post',
            url: '/setup/add-ajax',
            dataType: 'json',
            data: {
                'leadstageValue': $leadstageValue,
                'opportunityTypeId': $opportunityTypeId,
                'action': 'saveleadstage',
                'leadStageId': $leadStageId,
                'return-data': true
            },
            validate: function (v) {
                if ($.trim(v) == '') {
                    return 'Required field!';
                }
            },
            success: function (response) {
                if (!response.success) {
                    displayErrorMessage(response.msg);
                    hideLoader();
                    return false;
                }
                $('#opportunityTypeId').val('');
                $('#leadstageValue').val('');
                $('#leadStageId').val('');
                if (response.objectClass != '') {
                    $('.' + response.objectClass).html(response.view);
                } else {
                    updateSetupContent(response);
                }
                hideLoader();
                $('#modal-leadstage-data-form').modal('hide');
            },
            error: function () {
                console.log('error');
            },
            complete: function () {
                $dataSaved = true;
            }
        });
    });


    $('.modalSaveCurrencyButton').click(function (e) {
        e.preventDefault();
        $this = $(this);
        $modalDataVal = $('#modalDataVal');
        $code = $modalDataVal.attr('code');

        $currencyCode = $('#currencyCode');
        $currencyValue = $('#currencyValue');
        $currencySymbol = $('#currencySymbol');

        if ($currencyCode == '' || $currencyValue == '' || $currencySymbol == '') {
            displayErrorMessage($allfieldrequiredmsg);
            return false;
        }

        showLoader();
        $.ajax({
            method: 'post',
            url: '/setup/add-currency',
            dataType: 'json',
            data: {
                'currencyCode': $currencyCode.val(),
                'currencyValue': $currencyValue.val(),
                'currencySymbol': $currencySymbol.val(),
                'return-data': true
            },
            validate: function (v) {
                if ($.trim(v) == '') {
                    return 'Required field!';
                }
            },
            success: function (response) {
                if (!response.success) {
                    displayErrorMessage(response.msg);
                    hideLoader();
                    return false;
                }
                $('#currencyCode').val('');
                $('#currencyValue').val('');
                $('#currencySymbol').val('');
                updateSetupContent(response);
                hideLoader();
                $('#modal-currency-data-form').modal('hide');
            },
            error: function () {
                console.log('error');
            },
            complete: function () {
                $dataSaved = true;
            }
        });
    });

    $dataFetched = true;
    $('.refreshDataField').click(function (e) {
        e.preventDefault();
        if ($dataFetched) {
            $this = $(this);
            $dataFetched = false;
            //$code = $this.attr('code');

            $selectedSupplier = $('#supplier').val();
            $url = $this.attr('data-url');

            $.ajax({
                method: 'post',
                url: $url,
                dataType: 'json',
                data: {'selectedSupplier': $selectedSupplier},
                validate: function (v) {
                    if ($.trim(v) == '') {
                        return 'Required field!';
                    }
                },
                success: function (response) {
                    if (!response.success) {
                        return response.msg;
                    }
                    updateSelectData(getJsonData(response.data), $this);
                },
                error: function () {
                    console.log('error');
                },
                complete: function () {
                    $dataFetched = true;
                }
            });
        }
    });
});