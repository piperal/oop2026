package ee.piperal.kontrolltoo.service;


import ee.piperal.kontrolltoo.entity.Word;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WordService {

    public void validator(Word word){
        if(word.getId() != null){
            throw new RuntimeException("Cant add ID here");
        }
        if(Objects.equals(word.getWord(), "")){
            throw new RuntimeException("Word cant be empty");
        }
    }
}
