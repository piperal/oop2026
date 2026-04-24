package ee.mihkel.veebipood.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// nt mul on e-pood, klient kellele arendustööd teen, tellib 100st erinevast poest endale tooteid sisse
// Smart-ID, mobiil-id, pakiautomaatide suhtlus, pangasuhtlus, eesti.ee

@CrossOrigin(origins = "*")
@RestController
public class SupplierController {

    @GetMapping("supplier1")
    public String getProductsSupplier1()  {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }

    // https://api.escuelajs.co/api/v1/products
}
