package ee.piperal.sportlasetulemused.service;


import ee.piperal.sportlasetulemused.entity.Athlete;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

    public void validator(Athlete athlete){
        if(athlete.getId()!=null){
            throw new RuntimeException("Cant add with id");
        }
        if(athlete.getFirstName().isEmpty()){
            throw new RuntimeException("Cant add without first name");
        }
        if(athlete.getLastName().isEmpty()){
            throw new RuntimeException("Cant add without last name");
        }
        if(athlete.getNumber() == 0){
            throw new RuntimeException("Cant add without number");
        }
        if(athlete.getField().isEmpty()){
            throw new RuntimeException("Cant add without field");
        }

    }
}
