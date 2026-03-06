package ee.piperal.kontrolltoo.controller;


import ee.piperal.kontrolltoo.entity.ReverseWord;
import ee.piperal.kontrolltoo.entity.Word;
import ee.piperal.kontrolltoo.repository.ReverseInterface;
import ee.piperal.kontrolltoo.repository.WordInterface;
import ee.piperal.kontrolltoo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;

@RestController
public class WordController {

    @Autowired
    WordInterface wordInterface;

    @Autowired
    ReverseInterface reverseInterface;

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
    private List<String> reverse(){
        List<Word> words = wordInterface.findAll();
        List<String> reverseWords = new ArrayList<>();
        for(Word word : words){
            String reversed =
                    new StringBuilder(word.getWord()).reverse().toString();
            reverseWords.add(reversed);

            ReverseWord reverseWord = new ReverseWord();
            reverseWord.setReverseWord(reversed);
            reverseInterface.save(reverseWord);
        }
        return reverseWords;
    };

    @GetMapping("/common")
    private String common(){
        List<ReverseWord> words = reverseInterface.findAll();

        Map<Character, Integer> countMap = new HashMap<>();

        for (ReverseWord w : words) {

            String word = w.getReverseWord();

            char lastChar = word.charAt(word.length() - 1);

            countMap.put(lastChar,
                    countMap.getOrDefault(lastChar, 0) + 1);
        }

        return (countMap.entrySet().stream().max(Map.Entry.comparingByValue()).get()).toString();
    }


    @PostMapping("/add")
    private void addWord(@RequestBody Word word){
        wordService.validator(word);
        wordInterface.save(word);
    }


}
