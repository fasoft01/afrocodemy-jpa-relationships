package zw.co.afrocodemy.afrocodemyjparelationships.service.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message){
        super(message);
    }
}
