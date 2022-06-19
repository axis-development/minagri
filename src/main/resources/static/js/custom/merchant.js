$('.create_quote').click(function(){
	if($('#customerId').val() != '') {
		window.location.href=$serverUrl+"/quote/add/"+$('#customerId').val();
	} else if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/quote/edit/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/quote/index";
	}
});
$('.edit_quote').click(function(){
	if($('#customerId').val() != '') {
		window.location.href=$serverUrl+"/quote/edit?quote_customer_id="+$('#customerId').val();
	} else if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/quote/edit/"+$('#quoteId').val();
	} 
});
$('.view_quote').click(function(){
	if($('#customerId').val() != '') {
		window.location.href=$serverUrl+"/quote/view?quote_customer_id="+$('#customerId').val();
	} else if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/quote/view/"+$('#quoteId').val();
	} 
});
$('.manage_lead').click(function(){
	if($('#customerType').val() == 'lead') {
		if($('#customerId').val() != '') {
			window.location.href=$serverUrl+"/crm/edit/"+$('#customerId').val();		
		} else {
			window.location.href=$serverUrl+"/crm/add/";
		}	
	}
});
$('.create_invoice').click(function(){
	if($('#customerId').val() != '') {
		window.location.href=$serverUrl+"/quote/add/"+$('#customerId').val();
	} else if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/quote/edit/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/quote/index";
	}
});
$('.collect_payment').click(function(){
	if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/account-rec/add/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/account-rec/index";
	}
});
$('.follow_order').click(function(){
	if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/order/add/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/order/index";
	}
});
$('.send_po_supplier').click(function(){
	if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/po-order/add/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/po-order/index";
	}
});
$('.payment_to_supplier').click(function(){
	if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/account-pay/add/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/account-pay/index";
	}
});
$('.receive_inventory').click(function(){
	if($('#quoteId').val() != '') {
		window.location.href=$serverUrl+"/stock-order/add/"+$('#quoteId').val();
	} else {
		window.location.href=$serverUrl+"/stock-order/index";
	}
});

$('#searchAllCustomer').focusout(function(){
	if($(this).val() == '') {
		$('#customerId').val('');
		$('#quoteId').val('');
	}
})

$(function() {
  function split( val ) {
    return val.split( /,\s*/ );
  }
  function extractLast( term ) {
    return split( term ).pop();
  }

  $( "#searchAllCustomer" )
    // don't navigate away from the field on tab when selecting an item
    .bind( "keydown", function( event ) {
      if ( event.keyCode === $.ui.keyCode.TAB &&
          $( this ).autocomplete( "instance" ).menu.active ) {
        event.preventDefault();
      }
    })
    .autocomplete({
      source: function( request, response ) {
        $.getJSON( "/customer/auto-complete", {
          term: extractLast( request.term ),
          type: 'searchId'
        }, response );
      },
      search: function() {
        // custom minLength
        var term = extractLast( this.value );
        if ( term.length < 1 ) {
          return false;
        }
      },
      focus: function() {
        // prevent value inserted on focus
        return false;
      },
      select: function( event, ui ) {
        var terms = split( this.value );
        // remove the current input
        terms.pop();
        // add the selected item
        terms.push( ui.item.value );
        var str = terms.toString();
        var n = str.indexOf('#');
        if(n == -1) {
        	$('#customerId').val(ui.item.id);
        	$('#quoteId').val('');
        	$('#customerType').val(ui.item.customerType);        	
        } else {
        	$('#customerId').val('');
        	$('#quoteId').val(ui.item.id);
        	$('#customerType').val('');
        }
        // add placeholder to get the comma-and-space at the end
        this.value = terms;
        return false;
      }
    });
});
 $.ui.autocomplete.prototype._renderItem = function( ul, item){
	  var term = this.term.split(' ').join('|');
	  var re = new RegExp("(" + term + ")", "gi") ;
	  var t = item.label.replace(re,"<strong>$1</strong>");
	  return $( "<li></li>" )
	     .data( "item.autocomplete", item )
	     .append( "<a>" + t + "</a>" )
	     .appendTo( ul );
};
	
	
$('.firstblock a').click(function(){
	$redirectLink = $(this).attr('data-href');
    showLoader();
    $.ajax({
        url: $serverUrl+'/merchant?updateVisitedLink=true&visitedLink='+$(this).attr('date-link'),
        type: 'get',
        success: function($result) {
        	$data = getJsonData($result);
        	hideLoader()
        	//window.location.href=$redirectLink;
        	//window.open($redirectLink, '_blank', 'location=yes,scrollbars=yes,status=yes');
        	return true;
        },
        error: function($error) {
            console.log($error);  
        },
        complete: function() {            
        	//hideLoader();        	
        }
    });    
});	