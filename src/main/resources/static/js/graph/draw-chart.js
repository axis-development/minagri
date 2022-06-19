var _baseObj = {
    _set : function(property,value){
        if(typeof property == 'object'){
            this[property].push(value);
        }else{
            this[property] = value;
        }
    },   
    _get : function(property){
            if(this._hasAttribute(property))
                return this[property];
            return null;
    },
    _copy : function(childObj){
        var that = this;
        forEachIn(childObj,function(index,value){
            if(that._hasAttribute(index)){
                that._set(index,value);
            }else{
                if(! that._hasProperty(index) ){
                    that._set(index,value);
                }
            }
        })
    },
    _hasProperty : function(property){
        if(typeof this[property] == 'undefined' || typeof this[property] != 'function')
            return false;
        return true;
    },
    _hasAttribute  : function(attr){
        if(typeof this[attr] == 'undefined')
            return false;
        return true;
    }
};
var DrawChart = $.extend({
    _default : {
        container : "",
        callback : function(){},
        cssClass : "graph-class",
        cavasElement : "chart-canvas",
        canvasWidth : 512,
        canvasHeight : 300,
        mainContainer : "drawchart-continer",
        chartData : null,
        chartCategories : null,
    },
    _userParam : {},
    _render  : function(options){
        var userparam = this._userParam  = $.extend(this._default, options);
        var graphObject = userparam.callback(userparam.container, userparam.chartCategories, userparam.chartData);
        this._createCanvas(graphObject);
    },
    _createCanvas : function(graphObject){
       
       var userParam = this._userParam;
       var chart_svg = graphObject.getSVG();
        
       // create parent div
       if( !$(userParam.mainContainer).length ){
            $("body").append('<div id="' + userParam.mainContainer +'" style="display:none"></div>');
       }
       
       var chart_canvas = null;
       // create canvas
       if( $(userParam.cavasElement).length ){
           chart_canvas = document.getElementById(userParam.cavasElement); 
       }else{
           $.when( $("#" + userParam.mainContainer).append('<canvas id = "' + userParam.cavasElement+ '" height = "' + userParam.canvasHeight + '" width="' + userParam.canvasWidth + '"></canvas>')).then( chart_canvas = document.getElementById(userParam.cavasElement) );
            
       }
       
       canvg(chart_canvas, chart_svg, {scaleWidth: userParam.canvasWidth, scaleHeight: userParam.canvasHeight});
       var chart_img = chart_canvas.toDataURL('image/png');
       $("#" + userParam.mainContainer).append('<img class="' + userParam.cssClass + '" src="' + chart_img + '" />'); 
    },
    _submit : function(url){
        var userParam = this._userParam;
        
        var myForm = document.createElement("form");
        myForm.method = 'post';
        myForm.action =  url;
        
        var dataInput;
        
        $("#" + userParam.mainContainer).find("." + userParam.cssClass).each(function(){
            dataInput= document.createElement("input") ;
            dataInput.setAttribute("name", "imgdata[]") ;
            dataInput.setAttribute("value",$(this).attr('src'));
            dataInput.setAttribute("type", "hidden");
            myForm.appendChild(dataInput);
        });
        
        document.body.appendChild(myForm) ;
        myForm.submit() ;
        document.body.removeChild(myForm) ;
    }
},_baseObj)