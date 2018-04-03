package br.com.todoapp.controllers.resources;

import br.com.todoapp.models.domains.Task;
import br.com.todoapp.models.domains.User;
import br.com.todoapp.models.services.TaskService;
import br.com.todoapp.models.services.UserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {

    @Autowired
    private UserAuthenticatedService userAuthService;

    @Autowired
    private TaskService taskService;

    //@TODO ALLL ELEMENTS HERE GONNA NEED USER AUTHENTICATED, SO SHALL WE CREATE AN INTERCEPTOR TO DO THIS THING TO US

    @GetMapping
    public ResponseEntity showAllByCreator(){

        User user = userAuthService.getUser().get().getAppUser();
        List<Task> tasks = taskService.searchAllByCreator(user);

        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Task taskToAdd){
        User user = userAuthService.getUser().get().getAppUser();
        taskToAdd.setCreator(user);

        taskService.register(taskToAdd);

        return new ResponseEntity(taskToAdd, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Task taskToUpdate){

     User user = userAuthService.getUser().get().getAppUser();
     Task taskFound =  taskService.searchById(taskToUpdate.getId(), user.getId());

     if(taskFound == null) return new ResponseEntity(Collections.EMPTY_LIST, HttpStatus.NOT_FOUND);

     taskFound.setStatus(taskToUpdate.getStatus());

     taskService.update(taskFound);

     return new ResponseEntity(taskFound, HttpStatus.OK);

    }


}
