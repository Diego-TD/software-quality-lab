package mx.edu.cetys.softwarequalitylab.pets;

import mx.edu.cetys.softwarequalitylab.pets.exceptions.InvalidPetDataException;
import mx.edu.cetys.softwarequalitylab.pets.exceptions.PetNotFoundException;
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

        return getPetResponseMapper(savedPet);
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

    private void validPetId(Long petId) {
        if (petId == null || petId < 0){
            throw new InvalidPetDataException("Invalid Pet Id");
        }
    }

    private PetController.PetResponse getPetResponseMapper(Pet pet) {
        return new PetController.PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getColor(),
                pet.getRace(),
                pet.getAge());
    }

    // TODO: add code coverage, unit tests and integration tests
    PetController.PetResponse getPetById(Long petId){
        log.info("Starting pet Request Validations, petId={}", petId);
        validPetId(petId);

        var petFromDb = petRepository.findById(petId);

        if (petFromDb.isEmpty()) {
            throw new PetNotFoundException("Pet with id: " + petId+ ", not Found");
        }

        var realPet = petFromDb.get();
        return getPetResponseMapper(realPet);
    }
}
