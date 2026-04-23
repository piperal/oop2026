package ee.piperal.mockapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


@RestController
public class RefereeController {
    @GetMapping("/kohtunikud")
    public static String getRef() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://69e9ecab15c7e2d5126905f6.mockapi.io/sportlased/kohtunikud");
        URLConnection con = url.openConnection();
        con.setDoOutput(true);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    @GetMapping("/asukohad")
    public static String getLoc() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://69e9ecab15c7e2d5126905f6.mockapi.io/sportlased/asukohad");
        URLConnection con = url.openConnection();
        con.setDoOutput(true);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
