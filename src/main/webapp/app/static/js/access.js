(function($, globals){


    var apiUrl = '/users/access/signin';
    var user = {};
    var $form;

    var AccessModel = (function(){


        function setUp(){
           $form = $('#login-form').on({
                submit: access
            });
        }

        function access(fe){

           fe.preventDefault();

           user = parseFormElements();

           var accessRequest =  $.ajax({
                url: apiUrl,
                method: 'POST',
                data: user,
                converters: 'text json'
            });


           accessRequest.done(successLogin);
           accessRequest.fail(failLogin);
        }


        function successLogin(successMessage) {

            $.toast({
                heading: 'Success',
                text: 'Access permited! In a few seconds you\'ll be redirected',
                hideAfter: 7000,
                icon: 'success'
            });

            setTimeout(function(){
                globals.location.href = '/users/dashboard';
            }, 7000);

        }

        function failLogin(errorReason) {

            $.toast({
                heading: 'Error',
                text: 'Username or password incorrect. Are you sure that have an account? <a href="/access/users/register">Create account</a>',
                hideAfter: 9000,
                icon: 'error'
            });
        }

        function parseFormElements() {

            var user = {};

            user.email = $('#email').val();
            user.password = $('#password').val();

            return user;
        }


        return {
            init: setUp
        };

    })();



    $(document).ready(AccessModel.init());



})(jQuery, window);