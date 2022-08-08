package exceptions;

public class ItemAlreadyStoredException extends RuntimeException{
    public ItemAlreadyStoredException(String message){
        super(message);
    }
}
