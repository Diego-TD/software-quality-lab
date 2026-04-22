package mx.edu.cetys.softwarequalitylab.pets;

import mx.edu.cetys.softwarequalitylab.pets.exceptions.InvalidPetDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    PetController.PetResponse savePet(PetController.PetRequest petRequest) {
        log.debug("Saving Pet Request: {}", petRequest);


        if (!validatePet(petRequest)){
            return  null; // TODO: handle errors
        }

        Pet newPet = new Pet(petRequest.name(), petRequest.color(), petRequest.race(), petRequest.age());
        Pet savedPet = petRepository.save(newPet);
        log.debug("Saved Pet Response: {}", savedPet);

        return new PetController.PetResponse(savedPet.getId(),  savedPet.getName(), savedPet.getColor(), savedPet.getRace(), savedPet.getAge());
    }


    private boolean validatePet(PetController.PetRequest petRequest) {
        // TODO: increase code coverage

        if (petRequest == null){
            throw new InvalidPetDataException("Invalid Pet Request");
        }

        // name.lenght > 2 characters
        if (petRequest.name().isBlank() || petRequest.name().length() < 3) {
            throw new InvalidPetDataException("Invalid Pet Name");
        }

        // age > 0
        if (petRequest.age() <0 || petRequest.age() > 100) {
            throw new InvalidPetDataException("Invalid Pet Age");
        }

        // color not empty
        if (petRequest.color().isBlank()) {
            throw new InvalidPetDataException("Invalid Pet Color");
        }

        // race not empty
        if (petRequest.race().isBlank()) {
            throw new InvalidPetDataException("Invalid Pet Race");
        }

        return true;
    }

    PetController.PetsResponse getAllPets() {
        return new PetController.PetsResponse(petRepository.findAll());
    }

}
