package exceptions;

public class SameItemException extends RuntimeException{
    public SameItemException(String message){
        super(message);
    }
}
