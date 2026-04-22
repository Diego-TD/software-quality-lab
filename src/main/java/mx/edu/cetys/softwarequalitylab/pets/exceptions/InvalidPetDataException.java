package mx.edu.cetys.softwarequalitylab.pets.exceptions;

public class InvalidPetDataException extends RuntimeException {

    public InvalidPetDataException(String message){
        super(message);
    }
}
