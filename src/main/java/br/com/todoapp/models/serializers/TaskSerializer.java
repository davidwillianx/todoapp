package br.com.todoapp.models.serializers;

import br.com.todoapp.models.domains.Task;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TaskSerializer extends StdSerializer<Task> {

    public TaskSerializer() {
        this(null);
    }

    public TaskSerializer(Class<Task> t) {
        super(t);
    }

    @Override
    public void serialize(Task task, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        DateFormat humanizeDate =  new SimpleDateFormat("dd/MM/yyyy");
        String humanizedDate = humanizeDate.format(task.getCreated().getTime());

          jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", task.getId());
                    jsonGenerator.writeStringField("label", task.getLabel());
                    jsonGenerator.writeStringField("description", task.getDescription());
                    jsonGenerator.writeStringField("created", humanizedDate);
                    jsonGenerator.writeBooleanField("status", task.isDone());
          jsonGenerator.writeEndObject();

    }
}
