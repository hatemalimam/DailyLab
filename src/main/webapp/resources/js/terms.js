/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    PF('selectWV').jq.change(function() {
        if (PF('selectWV').isChecked()) {
            PF('buttonWV').jq.show();
        } else {
            PF('buttonWV').jq.hide();
        }
    })
})

