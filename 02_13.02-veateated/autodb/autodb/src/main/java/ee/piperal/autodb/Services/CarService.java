package ee.piperal.autodb.Services;

import ee.piperal.autodb.Entitites.Car;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    public void checker(Car car){
        if(car.getId() != null){
            throw new RuntimeException("Cannot sign up with id");
        }
        if(car.getPlate().isEmpty()){
            throw new RuntimeException("Cannot sign up without licence plate");
        }
        if(car.getModel().isEmpty()){
            throw new RuntimeException("Cannot sign up without model");
        }
        if(car.getColor().isEmpty()){
            throw new RuntimeException("Cannot sign up without color");
        }
        if(car.getMark().isEmpty()){
            throw new RuntimeException("Cannot sign up without mark");
        }
    }
}
