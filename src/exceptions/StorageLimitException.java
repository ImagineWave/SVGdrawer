package exceptions;

import storages.Bag;

public class StorageLimitException extends RuntimeException{

    public StorageLimitException(String message){
        super(message);

    }
}
