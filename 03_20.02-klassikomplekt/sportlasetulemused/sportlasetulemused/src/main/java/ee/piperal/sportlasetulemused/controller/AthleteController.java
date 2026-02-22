package ee.piperal.sportlasetulemused.controller;

import ee.piperal.sportlasetulemused.entity.Athlete;
import ee.piperal.sportlasetulemused.repository.AthleteRepository;
import ee.piperal.sportlasetulemused.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private AthleteService athleteService;

    @GetMapping("all")
    public List<Athlete> getAll(){
        return athleteRepository.findAll();
    }

    @GetMapping("results/{id}")
    public String getResults(@RequestParam Long id){
        double[] repoResults = athleteRepository.findById(id).orElseThrow().getResults();
        double result = 0;
        for (double repoResult : repoResults) {
            result += repoResult;
        }
        return (athleteRepository.findById(id).orElseThrow().getLastName() + ", Spordiala: " + athleteRepository.findById(id).orElseThrow().getField() + ", Tulemus: " +   result);
    };

    @PostMapping("/add")
    public Athlete addAthlete(@RequestBody Athlete athlete){
        athleteService.validator(athlete);
        return athleteRepository.save(athlete);
    }

    @DeleteMapping("/delete/{id}")
    public List<Athlete> delAthlete(@PathVariable Long id){
        athleteRepository.deleteById(id);
        return athleteRepository.findAll();
    };

    @PutMapping("/results/update/{id}")
    public List<Athlete> editAthlete(@RequestBody Athlete athlete){
        if(athlete.getId() == null){
            throw new RuntimeException("Cannot edit without id");
        }
        if(!athleteRepository.existsById(athlete.getId())){
            throw new RuntimeException("Athlete with this id does not exist");
        }
        athleteRepository.save(athlete);
        return athleteRepository.findAll();
    }
}
