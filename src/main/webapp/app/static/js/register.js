(function($, globals){

var Register = (function(){

var user = {};
var apiURL = '/api/users/register';

var $form;


function setUp(){
    $('#register-form, #register-submit')
        .submit(userRegister);
}


function userRegister(form){

   form.preventDefault();

   $form = $(this);

   user = parseFormElements();



   var request = $.ajax({
        url: apiURL,
        contentType:'application/json',
        dataType: 'json',
        method: 'POST',
        data:  JSON.stringify(user)
    });

   request.done(registrationSuccess);
   request.fail(registrationFail);
}


function registrationFail(reason){
    $.toast({
        heading: 'Error',
        text: 'Falha ao realizar registro',
        hideAfter: 5000,
        icon: 'error'
    });
}

function  registrationSuccess(successMessage) {

    clearRegistrationForm();

    $.toast({
        heading: 'Success',
        text: 'Registration success!, <a href="/users/access/signin">click here</a> and start to organize yourself!',
        hideAfter: 9000,
        icon: 'success'
    });

}

function clearRegistrationForm(){

    $form.find('input[type=text], input[type=password]')
         .each(function () {
             console.log('Elelement detected', $(this));
             $(this).val('');
         });
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