$(function () {
    if (typeof $fileType === 'undefined') {
        $fileType = /(\.|\/)(gif|jpe?g|png|jpg)$/i;
    }
    if ($currentmodule == 'product' || $currentmodule == 'profilepage' || $currentmodule == 'bizprofile') {
        //Product Upload handle
        $('#fileupload').fileupload({
            url: '/file/upload',
            acceptFileTypes: $fileType,
            maxNumberOfFiles: 1,
            //maxFileSize: 400000,
            formData: {'fileType': $fileType, 'max_height': '300', 'max_width': '300'},
        });
        $('#fileupload').bind('fileuploadadd', function (e, data) {
        });
        $('#fileupload').bind('fileuploadprocessfail', function (e, data) {
            if (data.files.error) {
                if ($("#productfileupload .template-download").length) {
                    if ($("#productfileupload .template-upload").length) {
                        $("#productfileupload .template-upload").remove();
                        displayErrorMessage(data.files[data.index].error);
                    }
                } else {
                    $("#productfileupload .template-upload").not(':first').remove();
                    if ($("#productfileupload .template-upload").length > 1) {
                        displayErrorMessage(data.files[data.index].error);
                    }
                }
            }
        });

    } else {
        $('#fileupload').fileupload({
            url: '/file/upload',
            acceptFileTypes: $fileType,
            maxNumberOfFiles: 1,
            maxFileSize: 1000000,
            formData: {'fileType': $fileType},
        });
        $('#fileupload').bind('fileuploadadd', function (e, data) {
        });
        $('#fileupload').bind('fileuploadprocessfail', function (e, data) {
            if (data.files.error) {
                if ($("#fileupload .template-download").length) {
                    if ($("#fileupload .template-upload").length) {
                        $("#fileupload .template-upload").remove();
                        displayErrorMessage(data.files[data.index].error);
                    }
                } else {
                    $("#fileupload .template-upload").not(':first').remove();
                    if ($("#fileupload .template-upload").length > 1) {
                        displayErrorMessage(data.files[data.index].error);
                    }
                }
            }
        });


        $('#fileupload1').fileupload({
            url: '/file/upload',
            acceptFileTypes: $fileType,
            maxNumberOfFiles: 1,
            maxFileSize: 1000000,
            formData: {'fileType': $fileType},
            /*getNumberOfFiles: function () {
                //console.log($("#fileupload .template-download:not('.ui-state-error')").length);            
                $length = this.filesContainer.children().length;
                //console.log($length);
                //this.filesContainer.children().not('.processing').length; to get not processed image
                return $length;
            },*/
        });
        if ($('#fileupload1').length > 0) {
            /*$.ajax({
                url: $('#fileupload1').fileupload('option', 'url'),
                dataType: 'json',
                context: $('#fileupload1')[0]
            }).always(function () {
            }).done(function (result) {
                $(this).fileupload('option', 'done')
                    .call(this, $.Event('done'), {result: result});
            });*/
            $('#fileupload1').bind('fileuploadadd1', function (e, data) {
                /*var maxFiles = 1;
                //console.log('added files');
                console.log(data.files);   
                console.log(data.files.error)
                $.each(data.files, function (i, v) {
                    console.log(v);
                    console.log(v.error);
                });
                var fileCount = data.files.length;
                //console.log(fileCount);
                if (fileCount > maxFiles) {
                    alert("The max number of files is "+maxFiles);
                    return false; 
                }*/
            });
            $('#fileupload1').bind('fileuploadprocessfail', function (e, data) {
                //alert(data.files[data.index].error);
                if (data.files.error) {
                    if ($("#fileupload1 .template-download").length) {
                        if ($("#fileupload1 .template-upload").length) {
                            $("#fileupload1 .template-upload").remove();
                            displayErrorMessage(data.files[data.index].error);
                        }
                    } else {
                        $("#fileupload1 .template-upload").not(':first').remove();
                        if ($("#fileupload1 .template-upload").length > 1) {
                            displayErrorMessage(data.files[data.index].error);
                        }
                    }
                    //alert(data.files[data.index].error);
                }
            });
        }
    }
});

/*jslint unparam: true, regexp: true */
/*global window, $ *-/
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = 'server/php/',
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('Processing...')
            .on('click', function () {
                var $this = $(this),
                    data = $this.data();
                $this
                    .off('click')
                    .text('Abort')
                    .on('click', function () {
                        $this.remove();
                        data.abort();
                    });
                data.submit().always(function () {
                    $this.remove();
                });
            });
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true
    }).on('fileuploadadd', function (e, data) {
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                    .append($('<span/>').text(file.name));
            if (!index) {
                node
                    .append('<br>')
                    .append(uploadButton.clone(true).data(data));
            }
            node.appendTo(data.context);
        });
    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node
                .prepend('<br>')
                .prepend(file.preview);
        }
        if (file.error) {
            node
                .append('<br>')
                .append($('<span class="text-danger"/>').text(file.error));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button')
                .text('Upload')
                .prop('disabled', !!data.files.error);
        }
    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
    }).on('fileuploaddone', function (e, data) {
        $.each(data.result.files, function (index, file) {
            if (file.url) {
                var link = $('<a>')
                    .attr('target', '_blank')
                    .prop('href', file.url);
                $(data.context.children()[index])
                    .wrap(link);
            } else if (file.error) {
                var error = $('<span class="text-danger"/>').text(file.error);
                $(data.context.children()[index])
                    .append('<br>')
                    .append(error);
            }
        });
    }).on('fileuploadfail', function (e, data) {
        $.each(data.files, function (index, file) {
            var error = $('<span class="text-danger"/>').text('File upload failed.');
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});*/