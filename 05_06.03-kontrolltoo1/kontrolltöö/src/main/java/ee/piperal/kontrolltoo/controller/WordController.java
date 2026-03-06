package ee.piperal.kontrolltoo.controller;


import ee.piperal.kontrolltoo.entity.Word;
import ee.piperal.kontrolltoo.repository.WordInterface;
import ee.piperal.kontrolltoo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

@RestController
public class WordController {

    @Autowired
    WordInterface wordInterface;
    @Autowired
    WordService wordService;

    @GetMapping("/all")
    private List<Word> all(){
        return wordInterface.findAll();
    }

    @GetMapping("/all/last")
    private List<Word> last(){
        List<Word> words = wordInterface.findAll();
        for(Word word : words){
            int l = word.getWord().length();
            char last = word.getWord().charAt(l-1);
            word.setWord(String.valueOf(last));
        }
        return words;
    };

    @GetMapping("/all/length")
    private List<String> length(){
        List<Word> words = wordInterface.findAll();
        List<String> wordLengths = new ArrayList<>();
        for(Word word : words){
            int length = word.getWord().length();
            wordLengths.add("Sõna '" + word.getWord() + "' pikkus on " + length);
        }
        return wordLengths;
    };

    @GetMapping("/all/reverse")
    private List<Word> reverse(){
        List<Word> words = wordInterface.findAll();
        for(Word word : words){
            StringBuilder builder = new StringBuilder();
            builder.append(word.getWord());
            builder.reverse();
            word.setWord(builder.toString());
        }
        return words;
    };

    @PostMapping("/add")
    private void addWord(@RequestBody Word word){
        wordService.validator(word);
        wordInterface.save(word);
    }


}
