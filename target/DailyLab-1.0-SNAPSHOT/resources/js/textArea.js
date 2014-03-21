/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {         
    $('.ui-inputtextarea').flexible();       
});


function setSelectedText() {
   var range = $('.ui-inputtextarea').textrange();  
   setSelectedTextCommand([{name: 'selectedText', value: range.text}]);
}


