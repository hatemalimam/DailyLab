/* 
 * 
 * PrimeFaces Spinner Widget.
 * Fix spinning up and down with a Local
 * 
 * to run this make sure you set the local in the cfg of the widget
 * Example: PF('spinnerWV').cfg['local'] = 'de-DE';
 * 
 * Tested on: PrimeFaces 5.0
 * 
 * Browser compatibility (https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Global_Objects/Number/toLocaleString)
 * Feature	Chrome	Firefox (Gecko)	  Internet Explorer	Opera	Safari (WebKit)
 * locales	24	29 (29)                  11             15      Not supported
 * @auther: hatemalimam.com
 * 
 */
$(document).ready(function() {

    PrimeFaces.widget.Spinner.prototype.format = function() {
        if (this.value !== null) {
            var value = this.value;

            if (this.cfg.prefix)
                value = this.cfg.prefix + value;

            if (this.cfg.suffix)
                value = value + this.cfg.suffix;

            if (this.cfg.local !== null) {
                value = value.toString().replace(',', '.')
                value = parseFloat(value).toLocaleString(this.cfg.local);
            }

            this.input.val(value);
        }
    }

    PrimeFaces.widget.Spinner.prototype.spin = function(dir) {
        var step = this.cfg.step * dir,
                currentValue = this.value ? this.value : 0,
                newValue = null;

        if (this.cfg.precision)
            newValue = parseFloat(this.toFixed(currentValue + step, this.cfg.precision));
        else
            newValue = parseInt(currentValue + step);

        if (this.cfg.min !== undefined && newValue < this.cfg.min) {
            newValue = this.cfg.min;
        }

        if (this.cfg.max !== undefined && newValue > this.cfg.max) {
            newValue = this.cfg.max;
        }

        this.input.val(newValue);
        this.value = newValue;
        this.format();
        this.input.attr('aria-valuenow', this.input.val());
    };

    PrimeFaces.widget.Spinner.prototype.updateValue = function() {
        var value = this.input.val();
        value = value.toString().replace(',', '.')
        if ($.trim(value) === '') {
            if (this.cfg.min !== undefined)
                this.value = this.cfg.min;
            else
                this.value = null;
        }
        else {
            if (this.cfg.precision) {
                value = parseFloat(value);                
            }
            else {
                value = parseInt(value);                
            }

            if (!isNaN(value)) {
                if (this.cfg.max !== undefined && value > this.cfg.max) {
                    value = this.cfg.max;
                }

                if (this.cfg.min !== undefined && value < this.cfg.min) {
                    value = this.cfg.min;
                }

                this.value = value;
            }
        }
    };



    PrimeFaces.widget.Spinner.prototype.bindEvents = function() {
        var $this = this;

        this.jq.children('.ui-spinner-button')
                .on('mouseover.spinner', function() {
                    $(this).addClass('ui-state-hover');
                })
                .on('mouseout.spinner', function() {
                    $(this).removeClass('ui-state-hover ui-state-active');

                    if ($this.timer) {
                        clearInterval($this.timer);
                    }
                })
                .on('mouseup.spinner', function() {
                    clearInterval($this.timer);
                    $(this).removeClass('ui-state-active').addClass('ui-state-hover');
                    $this.input.trigger('change');
                })
                .on('mousedown.spinner', function(e) {
                    var element = $(this),
                            dir = element.hasClass('ui-spinner-up') ? 1 : -1;

                    element.removeClass('ui-state-hover').addClass('ui-state-active');

                    if ($this.input.is(':not(:focus)')) {
                        $this.input.focus();
                    }

                    $this.repeat(null, dir);

                    //keep focused
                    e.preventDefault();
                });

        this.input.on('keydown.spinner', function(e) {
            var keyCode = $.ui.keyCode;

            switch (e.which) {
                case keyCode.UP:
                    $this.spin(1);
                    break;

                case keyCode.DOWN:
                    $this.spin(-1);
                    break;

                case keyCode.ENTER:
                case keyCode.NUMPAD_ENTER:
                    $this.updateValue();
                    $this.format();
                    break;

                default:
                    //do nothing
                    break;
            }
        })
                .on('keyup.spinner', function(e) {
                    $this.updateValue();

                    var keyCode = $.ui.keyCode;
                    if (e.which === keyCode.UP || e.which === keyCode.DOWN) {
                        $this.input.trigger('change');
                    }
                })
                .on('blur.spinner', function(e) {
                    $this.format();
                })
                .on('mousewheel.spinner', function(event, delta) {
                    if ($this.input.is(':focus')) {
                        if (delta > 0)
                            $this.spin(1);
                        else
                            $this.spin(-1);

                        return false;
                    }
                });
    }

});
