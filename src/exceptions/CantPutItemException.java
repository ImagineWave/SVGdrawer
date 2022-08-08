package exceptions;

public class CantPutItemException extends RuntimeException{
    public CantPutItemException(String message){
        super(message);
    }
}
