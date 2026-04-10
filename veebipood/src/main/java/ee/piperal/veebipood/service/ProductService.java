package ee.piperal.veebipood.service;

import ee.piperal.veebipood.entity.Product;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ProductService {

    public void validate(Product product){
        if(product.getName() == null){
            throw new RuntimeException("Cannot add product with no name");
        }
        if(product.getDescription() == null){
            throw new RuntimeException("Cannot add product with no description");
        }
        if(product.getPrice() == null ||  product.getPrice() <= 0){
            throw new RuntimeException("Cannot add product without a price/with negative price");
        }
        if(product.getStock() <= 0){
            throw new RuntimeException("Cannot add product with no stock");
        }
    }
}
