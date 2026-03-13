package mx.edu.cetys.softwarequalitylab.pets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetsController {


    // GET localhost:8080/pets -- TODOS los pets, TODO: pagination
    // GET localhost:8080/pets/{id} -- pet by id
    // POST localhost:8080/pets -- New pet with RequestBody {json body} - DTO/Record/POJO
    // PUT localhost:8080/pets/{id} -- Update pet by id
    // DELETE localhost:8080/pets/{id} -- Flag available: yes/no

    record Pet(String name, String color, String race){}
    private record PetResponse(String info, Pet pet) {}

    @GetMapping("/hello")
    public PetResponse pets() {
        // return hello world as json
        return new PetResponse("Hello world", null);
    }
}
