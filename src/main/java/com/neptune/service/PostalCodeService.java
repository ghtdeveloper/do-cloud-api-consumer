package com.neptune.service;

import com.neptune.dto.response.postalcode.PostalCodeResponse;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor
public class PostalCodeService {

    private Client client;
    private static final String API_URL = "https://api.tau.com.mx/dipomex/v1/codigo_postal";
    private static final String API_KEY = "6e0224cd36265378bad6a1abb262203f4f22c812";

    @PostConstruct
    public void init() {
        this.client = JerseyClientBuilder.createClient();
    }

    public PostalCodeResponse fetch(String postalCode) {
        JerseyWebTarget target = (JerseyWebTarget) client.target(API_URL).queryParam("cp", postalCode);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("APIKEY", API_KEY)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(PostalCodeResponse.class);
        } else {
            log.error("Failed to fetch data from API. Status code: " + response.getStatus());
            return null;
        }
    }


}
