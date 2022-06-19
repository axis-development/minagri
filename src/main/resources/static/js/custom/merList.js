function bulkRequest($url,$validate)
{	
    $url = defaultFor($url, '/customer/bulk-request');
    $bulkProcess = true;
    if ($bulkProcess) {
        $values = getCheckboxValues($('input[name^="checkboxId["]'));
        if (!$values.length && $validate == true) {
        	displayErrorMessage($selRecordText);
            $('#bulkAction').val('');
            return ;
        }
        $action   = $('#bulkAction').val();
        $user     = $('#salesUser').val();
        $disqualifiedStatus    = $('#disqualifiedStatus').val();
        $customer = getCheckboxValues($('input[name^="checkboxId["]'), 'data-customer');
        showLoader();        
        $.ajax({
            method: 'post',
            url: $url,
            data: {'val': $values, 'action': $action, 'user': $user, 'disqualifiedStatus': $disqualifiedStatus, 'customer' : $customer},
            success: function($data) {
                //console.log($.parseJSON($data));
                if ($.parseJSON($data) == true) {
                    location.reload();
                    $userElement = $('#salesUser');
                    if ($userElement.length) {
                        $userElement.parent('div').addClass('hide');
                        $userElement.val('');
                    }
                    $('#bulkAction').val('');
                    $('input[name^="checkboxId["]').attr('checked', false);                    
                    hideLoader();
                }
                //$bulkProcess = true;
            },
            error : function($error) {
                console.log($error);
                //$bulkProcess = true;
            },
            complete: function() {
                $bulkProcess = true;
                hideLoader();
            }
        }); 
    }
}
function bulkRequestWithoutLoadPage($url,$validate,$quote='')
{	
    $url = defaultFor($url, '/customer/bulk-request');
    $bulkProcess = true;
    if ($bulkProcess) {
        $values = getCheckboxValues($('input[name^="checkboxId["]'));
        if (!$values.length && $validate == true) {
        	displayErrorMessage($selRecordText);
            $('#bulkAction').val('');
            return ;
        }
        $action   = $('#bulkAction').val();
        $user     = $('#salesUser').val();
        $customer = getCheckboxValues($('input[name^="checkboxId["]'), 'data-customer');
        showLoader();        
        $.ajax({
            method: 'post',
            url: $url,
            data: {'val': $values, 'action': $action, 'user': $user, 'customer' : $customer,'quote': $quote},
            success: function($data) {
                //console.log($.parseJSON($data));
                if ($.parseJSON($data) == true) {
                    //location.reload();
                    $userElement = $('#salesUser');
                    if ($userElement.length) {
                        $userElement.parent('div').addClass('hide');
                        $userElement.val('');
                    }
                    $('#bulkAction').val('');
                    $('input[name^="checkboxId["]').attr('checked', false);                    
                    hideLoader();
                }
                //$bulkProcess = true;
            },
            error : function($error) {
                console.log($error);
                //$bulkProcess = true;
            },
            complete: function() {
                $bulkProcess = true;
                hideLoader();
            }
        }); 
    }
}
$(function() {
    $('#sortHeading tr th').find('a').click(function(e) {
        e.preventDefault();
        //$('#pageNumber').val('1');
        $order  = $(this).attr('order');
        $direct = $(this).attr('direct');
        $form   = $(this).closest('form');
        $form.find('#order').val($order);
        $form.find('#direct').val($direct);
        $form.submit();       
    });
    
    $('#listSubmit').click(function(e) {
        e.preventDefault();
        $('#pageNumber').val('1');
        $(this).closest('form').submit();       
    });
    
    $('#paginationUl').find('a').click(function(e) {
        e.preventDefault();
        $page = $(this).attr('page');
        if ($page) {
            $('#pageNumber').val($page);
            $(this).closest('form').submit();
        }        
    });    
    $('#resultPerPage, #verify, #contractOnly, #closedOrder, #cancelStatus, #completedChkOnly, #fullPaidOrdersOnly, #pendingsOnly').change(function() {//#contractOnly for quote Only    	
        $('#pageNumber').val('1');
        $(this).closest('form').submit();
    });
    $('#disqualifiedOnly').change(function() {//#contractOnly for quote Only    	
        $('#pageNumber').val('1');
        if($(this).prop('checked') == true) {
        	$(this).closest('form').attr('action',$(this).closest('form').attr('action')+'?reset=1&disqualifiedOnly=1');
        } else {
        	$(this).closest('form').attr('action',$(this).closest('form').attr('action')+'?reset=1&disqualifiedOnly=');
        }
        
        $(this).closest('form').submit();
    });    
    $('#clientsOnly').change(function() {//#contractOnly for quote Only    	
        $('#pageNumber').val('1');
        if($(this).prop('checked') == true) {
        	$(this).closest('form').attr('action',$(this).closest('form').attr('action')+'?reset=1&clientsOnly=1');
        } else {
        	$(this).closest('form').attr('action',$(this).closest('form').attr('action')+'?reset=1&clientsOnly=');
        }
        
        $(this).closest('form').submit();
    });
});

$(function() {
    $('#reqsortHeading tr th').find('a').click(function(e) {
        e.preventDefault();
        //$('#pageNumber').val('1');
        $order  = $(this).attr('order');
        $direct = $(this).attr('direct');
        $form   = $(this).closest('form');
        $form.find('#reqorder').val($order);
        $form.find('#reqdirect').val($direct);
        $form.submit();       
    });
    
    $('#reqlistSubmit').click(function(e) {
        e.preventDefault();
        $('#reqpageNumber').val('1');
        $(this).closest('form').submit();       
    });
    
    $('#reqpaginationUl').find('a').click(function(e) {
        e.preventDefault();
        $page = $(this).attr('page');
        if ($page) {
            $('#reqpageNumber').val($page);
            $(this).closest('form').submit();
        }        
    });
    
    $('#reqresultPerPage, #reqverify, #reqcontractOnly, #reqclosedOrder, #reqcompletedChkOnly').change(function() {//#contractOnly for quote Only
        $('#reqpageNumber').val('1');
        $(this).closest('form').submit();
    });
});


function showprocessbar($quoteId){
    $this = $(this);
    showLoader();
    $.ajax({
       method: 'post',
       url: '/po-req/get-progress-bar',
       data: {'id' : $quoteId },
       success: function($data) {
           $result = getJsonData($data);
           $('.processbardiv').html($result.html);
           $('.processbardiv').show();
           hideLoader();
       },
       error : function($error) {
           console.log($error);
           hideLoader();
       },
       complete: function() {
           //$productElement.removeAttr('disabled');
    	   hideLoader();

       }
   });
	return false;
}
