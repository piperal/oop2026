package ee.piperal.movie_rental.service;


import ee.piperal.movie_rental.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    public void personValidation(Person person){

        if(person.getId() != null){
            throw new RuntimeException("Can't add with id");
        }
        if(person.getFirstName() == null || person.getFirstName().isEmpty()){
            throw new RuntimeException("Can't add without first name");
        }
        if(person.getMoviesRented() != null){
            throw new RuntimeException("Can't add movies here");
        }
        if(person.getDaysRented() != 0 || person.getDaysReturned() != 0){
            throw new RuntimeException("Can't add rentals here");
        }
        if(person.getTotal() != 0 || person.getLateCharge() != 0){
            throw new RuntimeException("Can't add total here");
        }
    }

    public void isRenting(Person person){
        if(person.getRenting() == 1){
            throw new RuntimeException("Person is already renting");
        }
    }
}
