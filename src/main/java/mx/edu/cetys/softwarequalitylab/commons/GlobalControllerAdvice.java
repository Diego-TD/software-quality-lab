package mx.edu.cetys.softwarequalitylab.commons;

import mx.edu.cetys.softwarequalitylab.pets.PetController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalControllerAdvice {
        // TODO handle global exceptions
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiResponse<Void> handleException(Exception e) {
//        return new ApiResponse<>("Internal server error", null, e.getMessage());
//    }
}
