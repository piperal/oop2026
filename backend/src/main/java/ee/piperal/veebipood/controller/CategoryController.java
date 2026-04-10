package ee.piperal.veebipood.controller;

import ee.piperal.veebipood.entity.Category;
import ee.piperal.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository CategoryRepository;

    @GetMapping("category")
    public List<Category> getCategory(){
        return CategoryRepository.findAll();
    }

    @DeleteMapping("category/{id}")
    public List<Category> delCategory(@PathVariable Long id){
        CategoryRepository.deleteById(id);
        return CategoryRepository.findAll();
    }

    @PostMapping("category/add")
    public List<Category> addCategory(@RequestBody Category category){
        CategoryRepository.save(category);
        return CategoryRepository.findAll();
    }
}
