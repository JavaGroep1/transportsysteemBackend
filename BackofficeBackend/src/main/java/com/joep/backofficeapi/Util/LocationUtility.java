package com.joep.backofficeapi.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joep.backofficeapi.Models.Autosuggest.Example;
import com.joep.backofficeapi.Models.Autosuggest.Item;
import com.joep.backofficeapi.Models.Route.Route;
import com.joep.backofficeapi.Models.Route.Route_;

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
                .header("Authorization", "Bearer eyJhbGciOiJSUzUxMiIsImN0eSI6IkpXVCIsImlzcyI6IkhFUkUiLCJhaWQiOiJRMGJDcG9GYVd2R2xyZGNUUUpvYSIsImlhdCI6MTYwNTI3NDYxOCwiZXhwIjoxNjA1MzYxMDE4LCJraWQiOiJqMSJ9.ZXlKaGJHY2lPaUprYVhJaUxDSmxibU1pT2lKQk1qVTJRMEpETFVoVE5URXlJbjAuLjNSdURuQVQzUFFIT21OY0luS3ZWV3cuR0tkbXhOLU1LVXJQY3MxV2ZkZDFhcFVHbFFRcEtCQUkyZmItSm9tQUc0eWRtYnN3QncyZnlqaG84NEgtbFphUENQU1gwYkVmQzFFQ1FTMHUyYzhzX0d1RHVnOUIweEcxM1dsVzRwUi0wUGgyVk5JUERtOVI4TEdDU2t6eWxsX1pxTFJhVS1XOU9QUjV6SnhYX3JRYjNRLklfWFNwVWRPOEpJNllGLUpIS2MzUm13QnNCZnczRkM1NVVmVEowb3Vwemc.rQ7SPWvbyRevgvRF2TySbDjkR-eaJA3-RiSKkYVzLUFgsIYx9Dzkp5oW27KnT7RGeeLqmygf9okAJqis9ADGTKIN0-gn2SXDoeMlf3RtrL345sWTgOpsbt2xGKS9NzLYyzexcmwLj1PsD0n13_UBtb0vNOmM_nAlpICnKj6ll8zRw1he3Riu7__nM-hoeDFM0zvnIx4G-VWDjxHn67XUDAv4lUIFlXmRe5_74jhY5eMSj-iZhAkoDRbtGAxuOUKstbWLx2PeXo4l4JRaE6ddF8DzuuXgDinnM-mlonpKrL-A97Xiq0OcNSdowofsnKuE63tyVTBfAQJePqlQllqnsQ")
                .uri(URI.create(encodedGet))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());



        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.body(), Example.class);
        }
        catch (Throwable e){
            return null;
        }
    }
}
