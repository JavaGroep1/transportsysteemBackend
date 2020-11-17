package com.joep.backofficeapi.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joep.backofficeapi.Models.Autosuggest.Example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class LocationUtility {
//    private static String API_KEY = "fwEl8C7Wi53YNXdSo9ljSZrpU6MUN1Zb";

    public static Example getLocation(String Address) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String get =
                MessageFormat.format("https://autosuggest.search.hereapi.com/v1/autosuggest?limit={0}&at={1}&q={2}",
                        5,
                        "52.090943,5.113132",
                        Address);
        String encodedGet = get.replaceAll(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " +   HereAuthUtil.getToken())
                .uri(URI.create(encodedGet))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.toString());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.body(), Example.class);

        }
        catch (Throwable e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
