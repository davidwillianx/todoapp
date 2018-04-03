package br.com.todoapp.models.repositories;

import br.com.todoapp.models.domains.Task;
import br.com.todoapp.models.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {
    List<Task> findByCreator(User creator);

    @Query("SELECT t FROM Task  t JOIN t.creator c WHERE c.id = :userId AND t.id = :taskId")
    Task findOneByCreator(@Param("taskId") int taskId, @Param("userId") int userId);
}
