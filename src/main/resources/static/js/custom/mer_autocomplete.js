$(function () {  
    $autoCompleteElement = $("input[data-type='auto-complete']");
    $autoCompleteElement.typeahead({
        items: 4,
        minLength: 1,
        /*updater: function (item) {
            /* do whatever you want with the selected item *-/
            console.log("selected " + item);
        },*/
        /*sorter: function (items) {
            if (items.length == 0) {
                var noResult = new Object();
                items.push(noResult);
            }
            return items;
        },*/
        /*highlighter: function (item) {
            if (n.indexOf(item) == -1) {
                return "<span>No Match Found.</span>";
            } else {
                return "<span>" + item + "</span>";
            }
        },*/
        source: function (query, process) {
            return $.ajax({
                url: this.$element.attr('data-url'),
                type: 'post',
                data: { },
                dataType: 'json',
                success: function (result) {
                    /*var resultList = result.map(function (item) {
                        var aItem = { id: item.category, name: item.label };
                        return aItem;
                    });
                    return process(resultList);*/
                    return process(result);
                },
                error : function (error) {
                    console.log('error')
                }
                /*return process(resultList);
                    return process(result);
                }*/
            });

            /*return $.get($url, { query: query }, function (data) {
                return process(data);
            });*/
        },
        /*matcher: function (obj) {
            var item = obj;//JSON.parse(obj);
            //return ~item.name.toLowerCase().indexOf(this.query.toLowerCase())
            if (!$.isEmptyObject(item)) {
                return ~item.toLowerCase().indexOf(this.query.toLowerCase())
            } else {
                return true;
            }
        },*/
        highlighter: function (obj) {
            var item = obj;//JSON.parse(obj);
            if (!$.isEmptyObject(item)) {
                var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
                return item.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {//item.name
                    return '<strong>' + match + '</strong>'
                })
            } else {
                return true;
            }
        },
        /*ajax: {
                method: 'post',
                url: $url,
                //data: {},
                //dataType: 'json',
                triggerLength:1,
                loadingClass: "loading-circle",
                preDispatch: function (query) {
                    showLoadingMask(true);
                    return {
                        search: query
                    }
                },
                preProcess: function (data) {
                    showLoadingMask(false);
                    if (data.success === false) {
                        // Hide the list, there was some error
                        return false;
                    }
                    // We good!
                    return data.mylist;
                },
                /*success: function (n) {
                    return query(n);
                    /*var r = $.ui.autocomplete.filter(n, e.term);
                    if (r.length > 0) {
                        t(r)
                    } else {
                        t([{
                            label: "No Results",
                            category: ""
                        }])
                    }*-/
                },
                error: function () {
                    console.log("error")
                }*-/
            //return dataSource;
        }*/
    });



/*$.widget('custom.catcomplete', $.ui.autocomplete, {
    _renderMenu: function (e, t) {
        var n = this,
            r = "";
        $.each(t, function (t, i) {
            if (i.category != r) {
                //e.append("<li class='ui-autocomplete-category'><span class='custom-ui-category-" + i.class + "'></span>" + i.category + "</li>");
                r = i.category
            }
            n._renderItemData(e, i)
        })
    }
});

$(function () {
    $autoCompleteElement = $("input[data-type='auto-complete']");
    $autoCompleteElement.catcomplete({
        delay: 0,
        minLength: 2,
        source: function (e, t) {
            $.ajax({
                type: 'post',
                url: $url,
                data: {},
                dataType: 'json',
                success: function (n) {
                    var r = $.ui.autocomplete.filter(n, e.term);
                    if (r.length > 0) {
                        t(r)
                    } else {
                        t([{
                            label: "No Results",
                            category: ""
                        }])
                    }
                },
                error: function () {
                    console.log("error")
                }
            })
        },
        select: function (e, t) {
            if (t.item.category == '') {
                $autoCompleteElement.val('');
                return false
            } else {
                //window.location.href = t.item.url
            }
        },
        change: function (e, t) {
            if (t.item === null) {}
        },
        /*open: function (e, t) {
            var n = e.target.clientWidth;
            $(this).catcomplete('widget').addClass('custom_auto_css');
            $(this).catcomplete('widget').css({
                "min-width": "205px",
                "max-height": "none",
                height: "auto",
                "border-radius": "0px"
            })
        }*--/
    }); */
});



/*$(function () {      
    $autoCompleteElement = $("input[data-id='ac_textbox']");
    $autoCompleteElement.typeahead({
        items: 4,
        minLength: 1,       
        source: function (query, process) {
            return $.ajax({
                url: this.$element.attr('data-url'),
                type: 'post',
                data: {'query': query,'field': this.$element.attr('data-field') },
                dataType: 'json',
                success: function (result) {            
                    return process(result);
                },
                error : function (error) {
                    console.log('error')
                }
            });
        },        
        highlighter: function (obj) {
            var item = obj;//JSON.parse(obj);
            if (!$.isEmptyObject(item)) {
                var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
                return item.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {//item.name
                    return '<strong>' + match + '</strong>'
                })
            } else {
                return true;
            }
        },                
    });
});	
*/

$(function() {
	  function split( val ) {
	    return val.split( /,\s*/ );
	  }
	  function extractLast( term ) {
	    return split( term ).pop();
	  }

	  $("input[data-id='ac_textbox']").each(function(i, el) {		  
		  el = $(el);
		  el.bind( "keydown", function( event ) {
		      if ( event.keyCode === $.ui.keyCode.TAB &&
		          $( this ).autocomplete( "instance" ).menu.active ) {
		        event.preventDefault();
		      }
		    })
		    .autocomplete({
		      source: function( request, response ) {
		        $.getJSON( "/customer/auto-complete", {
		          query: extractLast( request.term ),
		          field: el.attr('data-field')
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
		        // add placeholder to get the comma-and-space at the end
		        this.value = terms;
		        return false;
		      }
		    });
		});
	})	

 $.ui.autocomplete.prototype._renderItem = function( ul, item){
	  var term = this.term.split(' ').join('|');
	  var re = new RegExp("(" + term + ")", "gi") ;
	  var t = item.label.replace(re,"<strong>$1</strong>");
	  return $( "<li></li>" )
	     .data( "item.autocomplete", item )
	     .append( "<a>" + t + "</a>" )
	     .appendTo( ul );
};

