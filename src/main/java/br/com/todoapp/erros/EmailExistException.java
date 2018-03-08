package br.com.todoapp.erros;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String error) {
        super(error);
    }
}
