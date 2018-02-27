(function($, globals){

var Register = (function(){

var user = {};
var apiURL = '/user/register';


function setUp(){
    $('#register-form, #register-submit')
        .submit(userRegister);
}


function userRegister(form){

   form.preventDefault();

   user = parseFormElements();



   var request = $.ajax({
        url: apiURL,
        dataType: 'json',
        method: 'POST',
        data: user
    });

   request.done(registrationSuccess);
   request.fail(registrationFail);
}


function registrationFail(reason){
    console.log('we got problems');
    console.log(reason);
}

function  registrationSuccess(successMessage) {

    console.log('We got transaction success');
    console.log(successMessage);

}

function parseFormElements(){
    var user = {};

    user.username = $('#username').val();
    user.email = $('#email').val();
    user.password = $('#password').val();

    return user;

}

return {init: setUp};

})();



$(document).ready(Register.init());


})(jQuery, window);