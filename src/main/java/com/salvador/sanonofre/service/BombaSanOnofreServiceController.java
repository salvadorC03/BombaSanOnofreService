/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salvador.sanonofre.service;

import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import tools.jackson.databind.ObjectMapper;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

/**
 *
 * @author Administrador
 */
@RestController
@CrossOrigin
public class BombaSanOnofreServiceController {

    private final String BDV_ENDPOINT_URL = "https://www.bancodevenezuela.com/files/tasas/tasas2.json";

    @GetMapping("/bcv")
    public String getBcvRate() throws Exception {
        var client = newHttpClient();
        var request = newBuilder(new URI(BDV_ENDPOINT_URL))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        var result = client.send(request, ofString()).body();

        var json = new ObjectMapper().readTree(result);

        return json.at("/mesacambio/bcv/dolares").stringValue();
    }
}
