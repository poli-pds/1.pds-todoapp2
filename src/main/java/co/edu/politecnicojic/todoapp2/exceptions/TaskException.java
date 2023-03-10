package co.edu.politecnicojic.todoapp2.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TaskException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public TaskException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
