package ee.piperal.movie_rental.controllers;


import ee.piperal.movie_rental.entity.Movie;
import ee.piperal.movie_rental.entity.Person;
import ee.piperal.movie_rental.repository.MovieInterface;
import ee.piperal.movie_rental.repository.PersonInterface;
import ee.piperal.movie_rental.service.MovieService;
import ee.piperal.movie_rental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentController {

    @Autowired
    PersonInterface personInterface;

    @Autowired
    RentService personService;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieInterface movieInterface;

    @GetMapping("/people")
    public List<Person> getPeople(){
            return personInterface.findAll();
    }

    @PostMapping("/addcustomer")
    public void addCustomer(@RequestBody Person person){
        personService.personValidation(person);
        personInterface.save(person);
    }

    @DeleteMapping("/delcustomer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        personInterface.deleteById(id);
    }

    @PutMapping("/customer/rent/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody List<Long> movieIds, int days){

        Person person = personInterface.findById(id).orElseThrow();
        personService.isRenting(person);

        List<Movie> rentedMovies = person.getMoviesRented();
        for (Long movieId : movieIds) {
            rentedMovies.add(movieInterface.findById(movieId).orElseThrow());
        }
        double total = 0;
        person.setMoviesRented(rentedMovies);
        person.setDaysRented(days);
        person.setRenting(1);

        //Võtab "movieIDs" list ja lisab iga filmi mis on listis, kuna iga klient laentuada saab ainult ükskord
        for(Movie movie : rentedMovies){

        //Pidin panema siia mitte serivcisse, kuna muidu ei luba Id-ga otsida
            if(movie.getMovieRented() != 0){
                throw new RuntimeException("One of the movies is already being rented");
            }
            String movieType = movie.getMovieType();
            double moviePrice = movie.getMoviePrice();
            movie.setMovieRented(1);

            switch (movieType){
                case "new":
                    total += moviePrice * days;
                    break;
                case "regular":
                    for(int i = 0;i < 3;i++){
                        total += moviePrice;
                    }
                    for(int i = 3;i < days;i++){
                        total += moviePrice * (days - 3);
                    }
                    break;
                case "old":
                    if(days < 5){
                        for(int i = 0;i < 5;i++){
                            total += moviePrice;
                        }
                    }
                    else{
                        for(int i = 1;i < 5;i++){
                            total += moviePrice;
                        }
                        for(int i = 5;i < days;i++){
                            total += moviePrice * (days - 3);
                        }
                    }
                    break;

            }
        }
        person.setRentCharge(total);
        personInterface.save(person);

    }

    @PutMapping("/customer/return/{id}")
    public void returnCustomer(@PathVariable Long id, int returned){
        Person person = personInterface.findById(id).orElseThrow();
        double lateCharge = person.getLateCharge();
        int days = person.getDaysRented();
        int daysOver = returned - days;
        List<Movie> rentedMovies = person.getMoviesRented();

        if(returned > days){
            for(Movie movie : rentedMovies){
                double moviePrice = movie.getMoviePrice();
                movie.setMovieRented(0);
                lateCharge += moviePrice * daysOver;
                person.setLateCharge(lateCharge);
        }
        }
        else{
            person.setMoviesRented(null);
            person.setTotal(0);
            person.setRentCharge(0);
            person.setLateCharge(0);
            person.setDaysRented(0);
            person.setRenting(0);
            for(Movie movie : rentedMovies){
                movie.setMovieRented(0);
    }}
        person.setTotal(person.getRentCharge() + lateCharge);
        personInterface.save(person);
}
    //See on rohkem debug mõttes siin, sellega saab kasutaja kõik makstud ja filmid tagastatud
    @PutMapping("/customer/pay/{id}")
    public void customerPay(@PathVariable Long id){
        Person person = personInterface.findById(id).orElseThrow();
        List<Movie> rentedMovies = person.getMoviesRented();
        for(Movie rentedMovie : rentedMovies){
            rentedMovie.setMovieRented(0);
        };
        person.setMoviesRented(null);
        person.setTotal(0);
        person.setRentCharge(0);
        person.setLateCharge(0);
        person.setDaysRented(0);
        person.setRenting(0);
        personInterface.save(person);
    }


}
