(function ($) {
	"use strict";

    jQuery(document).ready(function($){


        $(".embed-responsive iframe").addClass("embed-responsive-item");
        $(".carousel-inner .item:first-child").addClass("active");
        
        $('[data-toggle="tooltip"]').tooltip();
        
        $('body').videoBG({
		position:"fixed",
		zIndex:-1,
		mp4:'/img/loginscreen/bg-video.mp4',
		ogv:'/img/loginscreen/bg-video.ogv',
		webm:'/img/loginscreen/bg-video.webm',
		poster:'/img/loginscreen/bg-video.png',
		fullscreen:true,
	});
        
    });


    jQuery(window).load(function(){

        
    });


}(jQuery));


function getJsonData(data)
{
    try {
        response = $.parseJSON(data);
    } catch (e) {
        response = data;
    }
    return response
}