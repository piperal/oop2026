package ee.piperal.veebipood.controller;

import ee.piperal.veebipood.entity.Product;
import ee.piperal.veebipood.repository.ProductRepository;
import ee.piperal.veebipood.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;//!!!!
import org.springframework.data.domain.Pageable; //!!!!
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
//http://localhost:5173
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "*")

    //http://localhost:5000/products?page=1&size=1&sort=price,asc
    @GetMapping("/products")
    public Page<@NonNull Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/products/admin")
    public List<Product> getProductsAdmin() {
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOne(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> delProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("products/add")
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("Can't add product with id");
        }
        productService.validate(product);
        productRepository.save(product);
        return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("Cannot edit without Id");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

}
