package ee.piperal.mockapp.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;


@RestController
public class RefereeController {
    @GetMapping("/kohtunikud")
    public String getRef() throws IOException {
        String url = "https://69e9ecab15c7e2d5126905f6.mockapi.io/sportlased/kohtunikud";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }

    @GetMapping("/asukohad")
    public String getLoc() throws IOException {
        String url = "https://69e9ecab15c7e2d5126905f6.mockapi.io/sportlased/asukohad";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }
}
