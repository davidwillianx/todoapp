(function($, globals){



    var Dashboard  = (function () {

        var $templateTasks;
        var $templateTask;
        var $tasksPanel;
        var $taskNew;
        var $taskLi;


        function setUp(){

            $tasksPanel = $('#tasks-elements');
            $taskNew = $('#form-new-task');
            $templateTasks = Handlebars.compile($('#template-tasks').html());
            $templateTask = Handlebars.compile($('#template-new-task').html());
            Handlebars.registerHelper('statusTask', templateTaskDoneOrNotHelper);
            Handlebars.registerHelper('statusClass', templateUnderlineOrOverlineTaskItem)

            $('body').on('change', '.task-check', updateTaskStatus);
            $taskNew.on('submit', createNewTask);

            searchTasks();
        }


        function createNewTask(form){

            form.preventDefault();

            var description = $('#task-new').val();

            registerNewTask({description: description})
                .done(successNewTaskRegister)
                .fail(failureNewTaskRegister);

        }

        function successNewTaskRegister(responseData) {

            $tasksPanel.append(
                $templateTask({task: responseData})
            );

            $('#task-new').val('');

        }

        function failureNewTaskRegister(errorReason) {

            $.toast({
                heading: 'Failure',
                text: 'Prolema ao registrar task',
                hideAfter: 2000 ,
                icon: 'failure'
            });

        }

        function registerNewTask(task) {

            var promise = $.ajax({
                url: '/api/tasks',
                method: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(task)
            });


            return promise;

        }


        function updateTaskStatus() {

            var isChecked = this.checked ? 1 : 0;
            var currentTask = $(this).val();

            $taskLi  =  $(this).parent();



            requestUpdateTaskStatus({
                id: currentTask,
                status: isChecked
            })
            .done(successTaskUpdateStatus)
            .fail(failureTaskUpdateStatus);

        }

        function requestUpdateTaskStatus(task) {

           var requestPromise = $.ajax({
                url: '/api/tasks',
                method: 'PUT',
                contentType: 'application/json',
                dataType: 'json',
               data: JSON.stringify(task)
            });

           return requestPromise;
        }

        function successTaskUpdateStatus(responseData) {

            var message = 'Task ' + responseData.label + ' atualizada com sucesso!';

            if(responseData.status) $taskLi.removeClass('not-done').addClass('done');
            if(!responseData.status) $taskLi.removeClass('done').addClass('not-done');

            $.toast({
                heading: 'Success',
                text: message,
                hideAfter: 2000 ,
                icon: 'success'
            });

        }

        function  failureTaskUpdateStatus(errorReason) {
            console.log('May we got some problems dude');
            console.log(errorReason);
        }



        function  searchTasks() {

           var requestPromise = $.ajax({
                url: '/api/tasks',
                method: 'GET',
                dataType: 'json'
            });

           requestPromise.done(successRequestTasks);
           requestPromise.fail(failureRequestTasks);
        }


        function successRequestTasks(responseData) {

            $tasksPanel.append(
                $templateTasks({tasks: responseData})
            );

        }

        function  failureRequestTasks(errorReason) {
            console.log('We got some problems dudde, shall we fix this');
            console.log(errorReason);
        }

        function templateTaskDoneOrNotHelper(status){
            return status ? "checked" : "" ;
        }

        function templateUnderlineOrOverlineTaskItem(status){
           return status ? "done" : "not-done";
        }

        return {init: setUp};

    })();



    $('document').ready(Dashboard.init());

})(jQuery, window);