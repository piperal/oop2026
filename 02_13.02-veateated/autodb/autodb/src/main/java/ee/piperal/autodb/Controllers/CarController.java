package ee.piperal.autodb.Controllers;

import ee.piperal.autodb.Repositories.CarRepository;
import ee.piperal.autodb.Services.CarService;
import ee.piperal.autodb.Entitites.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @GetMapping("cars")
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    @PostMapping("cars/add")
    public Car addCar(@RequestBody Car car){
        carService.checker(car);
        return carRepository.save(car);
    }
    @DeleteMapping("cars/del/{id}")
    public List<Car> deleteCar(@PathVariable Long id){
        carRepository.deleteById(id);
        return carRepository.findAll();
    }
}
