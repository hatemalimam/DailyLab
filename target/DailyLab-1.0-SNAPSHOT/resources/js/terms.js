/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    selectWV.jq.change(function() {
        if (selectWV.isChecked()) {
            buttonWV.jq.show();
        } else {
            buttonWV.jq.hide();
        }
    })
})

