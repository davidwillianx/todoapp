package br.com.todoapp.models.services;

import br.com.todoapp.models.domains.Task;
import br.com.todoapp.models.domains.User;
import br.com.todoapp.models.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> searchAllByCreator(User creator){
       return taskRepository.findByCreator(creator);
    }

    public Task register(Task task){
        return taskRepository.save(task);
    }

    public Task searchById(int taskId, int creatorId){
        return taskRepository.findOneByCreator(taskId, creatorId);
    }

    public Task update(Task task){
       return this.register(task);
    }

}
