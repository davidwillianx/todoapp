package br.com.todoapp.models.domains;

import br.com.todoapp.models.serializers.TaskSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="todoapp_task")
@JsonSerialize(using = TaskSerializer.class)
public class Task extends ModelDomain {

    private String label;
    private String description;
    private Calendar created  = Calendar.getInstance();
    private Status status = Status.NOT_DONE;

    @ManyToOne
    private User creator;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public boolean isDone(){
        return this.status.equals(Status.DONE);
    }
}
