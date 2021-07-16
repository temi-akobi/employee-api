package za.co.CrudApi.exception;

public class InvalidIDException extends RuntimeException{

    public InvalidIDException(String message){
        super(message);
    }

}
