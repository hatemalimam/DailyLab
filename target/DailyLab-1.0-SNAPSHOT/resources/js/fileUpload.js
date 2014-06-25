/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    setTimeout(fileUpload, 1000);

})

function fileUpload() {
   
    PF('fileUpload').jq.fileupload({
        add: function(e, data) {
            $this = PF('fileUpload');
            $this.chooseButton.removeClass('ui-state-hover ui-state-focus');
            if ($this.files.length === 0) {
                $this.enableButton($this.uploadButton);
                $this.enableButton($this.cancelButton);
            }

            if ($this.cfg.fileLimit && ($this.uploadedFileCount + $this.files.length + 1) > $this.cfg.fileLimit) {
                $this.clearMessages();
                $this.showMessage({
                    summary: $this.cfg.fileLimitMessage
                });

                return;
            }

            var file = data.files ? data.files[0] : null;
            if (file) {
                var validMsg = $this.validate(file);

                if (validMsg) {
                    $this.showMessage({
                        summary: validMsg,
                        filename: file.name,
                        filesize: file.size
                    });
                }
                else {
                    $this.clearMessages();                                                                               

                    var row = $('<tr></tr>').append('<td class="ui-fileupload-preview"></td>')
                            .append('<td>' + file.name + '</td>')
                            .append('<td class="title"><label>Title: <input name="title['+ file.name +']"></label></td>')
                            .append('<td>' + $this.formatSize(file.size) + '</td>')
                            .append('<td class="ui-fileupload-progress"></td>')
                            .append('<td><button class="ui-fileupload-cancel ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only"><span class="ui-button-icon-left ui-icon ui-icon ui-icon-close"></span><span class="ui-button-text">ui-button</span></button></td>')
                            .appendTo($this.filesTbody);

                    //preview
                    if ($this.isCanvasSupported() && window.File && window.FileReader && $this.IMAGE_TYPES.test(file.name)) {
                        var imageCanvas = $('<canvas></canvas')
                                .appendTo(row.children('td.ui-fileupload-preview')),
                                context = imageCanvas.get(0).getContext('2d'),
                                winURL = window.URL || window.webkitURL,
                                url = winURL.createObjectURL(file),
                                img = new Image();

                        img.onload = function() {
                            var imgWidth = null, imgHeight = null, scale = 1;

                            if ($this.cfg.previewWidth > this.width) {
                                imgWidth = this.width;
                            }
                            else {
                                imgWidth = $this.cfg.previewWidth;
                                scale = $this.cfg.previewWidth / this.width;
                            }

                            var imgHeight = parseInt(this.height * scale);

                            imageCanvas.attr({width: imgWidth, height: imgHeight});
                            context.drawImage(img, 0, 0, imgWidth, imgHeight);
                        }

                        img.src = url;
                    }

                    //progress
                    row.children('td.ui-fileupload-progress').append('<div class="ui-progressbar ui-widget ui-widget-content ui-corner-all" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="ui-progressbar-value ui-widget-header ui-corner-left" style="display: none; width: 0%;"></div></div>');

                    file.row = row;

                    file.row.data('filedata', data);
                    $this.files.push(file);

                    if ($this.cfg.auto) {
                        $this.upload();
                    }
                }
            }
        }});



}