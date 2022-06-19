$(function () {
    $('#toggleButton').click(function () {
        $iElement = $('.widget-box .widget-header .widget-toolbar a i');
        $element = $('.widget-box');
        $bodyElement = $('.widget-box .widget-body .widget-body-inner')
        if ($(this).attr('value') == 'open') {
            $(this).attr('value', 'close');
            if ($(this).find('i').length) {
                $(this).html($(this).html().replace('Show', 'Close'));
            } else {
                $(this).text($closeToggleText);
            }
            $iElement.attr('class', 'icon-chevron-up');
            $element.removeClass('collapsed');
            if ($bodyElement.length) {
                $bodyElement.css('display', 'block');
            }
        } else {
            $newElement = $('.widget-box.no-widget-box');
            $newBodyElement = $newElement.find('.widget-body .widget-body-inner');
            $(this).attr('value', 'open');
            if ($(this).find('i').length) {
                $(this).html($(this).html().replace('Close', 'Show'));
            } else {
                $(this).text($openToggleText);
            }
            $iElement.attr('class', 'icon-chevron-down');
            $element.addClass('collapsed');
            $newElement.removeClass('collapsed');
            if ($bodyElement.length) {
                $bodyElement.css('display', 'none');
            }
            if ($newBodyElement.length) {
                $newBodyElement.css('display', 'block');
            }
        }
    });

    $('.toggleHeader button').click(function () {
        $this = $(this);
        $toggle = $this.attr('toggle');
        //console.log($toggle);
        if ($toggle !== undefined) {
            $clickLink = $('.widget-box').find('.widget-toolbar').find('a[toggle="' + $toggle + '"]');
            //if($clickLink.closest('widget-box').hasClass('collapsed')) {
            $('html,body').animate({scrollTop: $($clickLink).offset().top}, 'fast');
            if ($clickLink.length) {
                $clickLink.trigger('click');
            }


            //}            
        }
        //console.log($toggle);
        //console.log($a.attr('val'))
    });

    if ((typeof $sendError !== "undefined") && $sendError) {
        $('#toggleButton').trigger('click');
    }
});

function openAllAccordian() {
    $newElement = $('.widget-box.no-widget-box');
    $newBodyElement = $newElement.find('.widget-body .widget-body-inner');
    $iElement = $('.widget-box .widget-header .widget-toolbar a i');
    $element = $('.widget-box');
    $bodyElement = $('.widget-box .widget-body .widget-body-inner')

    $('#toggleButton').attr('value', 'close');
    if ($('#toggleButton').find('i').length) {
        $('#toggleButton').html($('#toggleButton').html().replace('Show', 'Close'));
    } else {
        $('#toggleButton').text($closeToggleText);
    }
    $iElement.attr('class', 'icon-chevron-up');
    $element.removeClass('collapsed');
    if ($bodyElement.length) {
        $bodyElement.css('display', 'block');
    }
}