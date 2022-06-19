function add_resource($flag, $fieldsetElement) {
    $addressFieldset = $('#addressFieldset');
    $element = $addressFieldset.find('fieldset');
    var currentCount = $element.length;
    $lastFieldset = $addressFieldset.find('fieldset:last');

    if ($flag) {
        var row = $fieldsetElement.clone(true);
        $buttonElement = row.find('.removeResource');//$addressFieldset.find('fieldset:last')
        if ($buttonElement.length) {
            $buttonElement.attr('class', 'addResource pull-right');
        }
    } else {
        var row = $lastFieldset.clone(true);
    }
    //console.log(currentCount);
    //console.log(template.attr('id'));
    //template = template.find('input, select')//.replace(/_index_/g, currentCount)
    $.each(row.find('input, select, textarea'), function () {
        $(this).attr('name', $(this).attr('name').replace(/\d+/, currentCount));
        //console.log($(':input') + '---' + $(this).attr('name') + '----' + $(this).val());
        if (!$flag) {
            $(this).val('');
        }
        if ($(this).hasClass('defaultAddressCheck') && ($(this).attr('type') == 'checkbox')) {
            $(this).attr('checked', false);
        }
    });
    $.each(row.find('label'), function () {
        $(this).attr('for', $(this).attr('for').replace(/\d+/, currentCount));
    });
    $.each(row.find('button'), function () {
        if ($(this).attr('name') != undefined) {
            $(this).attr('name', $(this).attr('name').replace(/\d+/, currentCount));
        }
    });
    /*$.each(row.find('button.fileUploadButton'), function (i, v) {
        $(this).attr('source', $(this).attr('source').replace(/\d+/, currentCount));
        $(this).addClass('hide');
    });*/
    $buttonElement = $lastFieldset.find('.addResource');//$addressFieldset.find('fieldset:last')
    $buttonElement.attr('class', 'hide removeResource pull-right');
    //$buttonElement.find('i').attr('class', 'fa fa-times');
    row.insertAfter($lastFieldset);
    $lastFieldset.after('<hr/>');
    return false;
}

$(function () {
    $(document).on('click', '.addResource', function (e) {
        e.preventDefault();
        add_resource(false, '');
    });

    $(document).on('click', '.addressCopyButton', function (e) {
        e.preventDefault();
        add_resource(true, $(this).closest('fieldset'));
    });

    $(document).on('click', '.defaultAddressCheck', function (e) {
        //e.preventDefault();
        $this = $(this);
        $addressType = $this.closest('.addressFieldset').find('.defaultAddressTypeCheck');
        $addressVal = $addressType.val();
        $allFieldset = $('.addressFieldset').filter(function () {
            $addType = $(this).find('.defaultAddressTypeCheck');
            return $addType.val() == $addressVal
        });
        if ($addressVal) {
            if ($this.is(':checked')) {
                //console.log('checked');
                $allFieldset.find('.defaultAddressCheck').not($this).attr('checked', false);
                //$('.defaultAddressCheck').attr('checked', false);
                //$this.attr('checked', true);
            } else {
                //console.log('unchecked');
                $allFieldset.find('.defaultAddressCheck').attr('checked', false);
                $allFieldset.first().find('.defaultAddressCheck').attr('checked', true);
            }
        } else {
            e.preventDefault();
        }
    });

    /*$(document).on('click', '.removeResource', function(e) {
        e.preventDefault();
        $(this).closest('fieldset').remove();
    });*/

    /*$(document).on('change', 'select[name^="resources["]', function() {
        $this          = $(this);       
        $buttonElement = $('button[source="' + $this.attr('name') + '"]');
        $integer = parseInt($this.attr('name').match(/\d+/));
        $element = $('input[name="resources[' + $integer + '][value]"]');
        //console.log($this.attr('name') + '---' + $integer + '---' + $element.attr('name') + '---' + $buttonElement.attr('source'));
        if ($.trim($this.val()) == 'document') {
            $buttonElement.removeClass('hide');
            //$elementName = 'resources[' + $integer  + '][value]';
            $('#saveModalBtn').attr('source', $integer);
        } else {
            $('#saveModalBtn').attr('source', '');
            $tmpElement = $('input[name="resources[' + $integer + '][tmp_value]"]');
            $element.val('');
            $tmpElement.val('');
            $buttonElement.addClass('hide');
        }
    });

    $(document).on('click', 'button.fileUploadButton', function() {
        //$this = $(this).closest('select'); 
        $source = $(this).attr('source');
        $('select[name="' + $source + '"]').trigger('change');            
    });

    $('#saveModalBtn').click(function(e) {
        e.preventDefault();
        $source     = $(this).attr('source');
        $fileValue  = $('input[name="file"]').val();
        $tmpFileVal = $('input[name="file"]').attr('tmp_value');
        //console.log($source);
        if ($source && $fileValue) {
            $element    = $('input[name="resources[' + $integer + '][value]"]');
            $tmpElement = $('input[name="resources[' + $integer + '][tmp_value]"]');
            $element.val($tmpFileVal);     
            $tmpElement.val($fileValue);
        }
        $('#myModal').modal('hide');
    });

    $('#myModal').on('hide.bs.modal', function (e) {
        $source    = $(this).attr('source');    
        $('tbody.files').html('');
        $('#saveModalBtn').attr('source', '');
    });*/
});