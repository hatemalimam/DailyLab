/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    PF('buttonWV').disable();
    PF('selectWV').jq.change(function() {
        if (PF('selectWV').isChecked()) {
            PF('buttonWV').enable();
        } else {
            PF('buttonWV').disable();
        }
    })
})

