package exceptions;

public class NegativeCordsException extends RuntimeException{
    public NegativeCordsException(String message){
        super(message);
    }
}
