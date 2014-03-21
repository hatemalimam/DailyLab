/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $(PrimeFaces.escapeClientId('form:tableId'))
            .on(
                    "change",
                    "input[type='checkbox'][name*='edit'], input[type='checkbox'][name*='create'], input[type='checkbox'][name*='delete']",
                    function() {
                        var tr = $(this).parent().parent();
                        var view = tr
                                .find("input[type='checkbox'][name*='view']");
                        var create = tr
                                .find("input[type='checkbox'][name*='create']");
                        var edit = tr
                                .find("input[type='checkbox'][name*='edit']");
                        var deleteBox = tr
                                .find("input[type='checkbox'][name*='delete']");
                        if ($(this).is(':checked')) {
                            view.prop("checked", true);
                        } else {
                            if (create.is(':checked') || edit.is(':checked')
                                    || deleteBox.is(':checked')) {
                                view.prop("checked", true);
                            } else
                                view.prop("checked", false);
                        }
                    });

    $(PrimeFaces.escapeClientId('form:tableId')).on(
            "change",
            "input[type='checkbox'][name*='view']",
            function() {
                var tr = $(this).parent().parent();
                var view = tr.find("input[type='checkbox'][name*='view']");
                var create = tr.find("input[type='checkbox'][name*='create']");
                var edit = tr.find("input[type='checkbox'][name*='edit']");
                var deleteBox = tr
                        .find("input[type='checkbox'][name*='delete']");
                if ($(this).is(':not(:checked)')) {
                    create.prop("checked", false);
                    edit.prop("checked", false);
                    deleteBox.prop("checked", false);
                }
            });
})

