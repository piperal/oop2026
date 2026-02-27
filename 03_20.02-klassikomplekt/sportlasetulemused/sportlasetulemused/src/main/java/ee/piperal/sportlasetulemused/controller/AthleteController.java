package ee.piperal.sportlasetulemused.controller;

import ee.piperal.sportlasetulemused.entity.Athlete;
import ee.piperal.sportlasetulemused.entity.Results;
import ee.piperal.sportlasetulemused.repository.AthleteRepository;
import ee.piperal.sportlasetulemused.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
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
    public String getResults(@RequestParam Long id, String fieldName){
        List<Results> repoResults = athleteRepository.findById(id).orElseThrow().getResults();
        double result = 0;
        for (Results repoResult : repoResults) {
            if(repoResult.getFieldName().equals(fieldName)){
                result += repoResult.getResult();
            }
        }
       return("Field: " + fieldName + ", Result: " + result);
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
    public void editAthlete(@PathVariable Long id, Results result){
      Athlete athlete = athleteRepository.findById(id).orElseThrow();
        List<Results> newResults = athlete.getResults();
        newResults.add(result);
        athleteRepository.findById(id).map(results ->{results.setResults(newResults);
           return athleteRepository.save(athlete);


   });
}}
