function getJsonData(e){try{response=$.parseJSON(e)}catch(t){response=e}return response}function defaultFor(e,t){return void 0!==e?e:t}function attrExists(e){return void 0!==e&&e}function resetSelectOption(e){e.find("option").length&&e.find("option").filter(function(){return this.value||$.trim(this.value).length}).remove()}function renderSelect(e,t){return e&&$.map(e,function(e){e.id&&e.value&&t.append($("<option/>",{value:e.id,text:e.value,selected:e.selected}))}),t}function getNameValObj(e,t){return t=defaultFor(t,"name"),$dataObj={},$.map(e,function(e){$dataObj[e.getAttribute(t)]=e.value}),$dataObj}function getNameValObjWithCheckbox(e,t){return t=defaultFor(t,"name"),$dataObj={},$.map(e,function(e){"checkbox"==e.getAttribute("type")?e.checked?$dataObj[e.getAttribute(t)]=1:$dataObj[e.getAttribute(t)]=0:$dataObj[e.getAttribute(t)]=e.value}),$dataObj}function getCheckboxValues(e,t){return t=defaultFor(t,"value"),$elemValues=[],$.map(e,function(e){e.checked&&$elemValues.push(e.getAttribute(t))}),$elemValues}function getCheckboxValuesLineNumber(e,t){return t=defaultFor(t,"data-index"),$elemValues=[],$.map(e,function(e){e.checked&&$elemValues.push(e.getAttribute(t))}),$elemValues}function showLoader(){$("div#custPageLoader").removeClass("hide")}function hideLoader(){$("div#custPageLoader").addClass("hide"),$("div#custPageLoader .custom-inner-center").html('<img src="/img/action_animation.gif" style="width:100px"></i><br /><br /><span class="loading_text"></span>'),$(".loading_text").html("")}function numberWithCommas(e){""!=e&&void 0!=e||(e=0),e=e.toString();for(var t=/(-?\d+)(\d{3})/;t.test(e);)e=e.replace(t,"$1,$2");return""!=e?"$"+e:e}function numberWithOutCommas(e){return""!=e&&void 0!=e||(e=0),e=(e=(e=e.toString()).replace("$","")).replace(/,/g,"")}$(function(){$flashMessage=$.trim($("#flashMessageDiv").text()),$flashMessage?($("#alertSuccessMessage .modal-body").html($flashMessage),$("#alertSuccessMessage").modal("show"),setTimeout(function(){$("#alertSuccessMessage").modal("hide")},7e3)):($flashMessage=$.trim($("#errorFlashMessageDiv").text()),$flashMessage&&($("#alertErrorMessage .modal-body").html($flashMessage),$("#alertErrorMessage").modal("show"),setTimeout(function(){$("#alertErrorMessage").modal("hide")},7e3))),$(".numeric").numeric({negative:!1}),$(".integer").numeric({decimal:!1,negative:!1}),$(".allCheckbox").click(function(e){$(this).closest(".checkBoxParent").find("input.checkAll").not(":disabled").attr("checked",$(this).is(":checked"))})});