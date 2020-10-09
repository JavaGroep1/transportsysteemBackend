package com.joep.backofficeapi.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joep.backofficeapi.Models.Route.Route;
import com.joep.backofficeapi.Models.Route.Route_;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class RouteUtility {
    private static String API_KEY = "fwEl8C7Wi53YNXdSo9ljSZrpU6MUN1Zb";
    private static double dieselPrice = 1.27;

    public static Route_ getRoute(String startAdress, String destination) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String get = MessageFormat.format("http://www.mapquestapi.com/directions/v2/route?key={0}&from={1}&to={2}&locale=nl_NL",
                API_KEY,
                startAdress,
                destination);
        String encodedGet = get.replaceAll(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(encodedGet))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var route = objectMapper.readValue(response.body(), Route.class);
            return route.getRoute();

        }
        catch (Throwable e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static double getRoutePrice(double fuelUsed){
        return fuelUsed * dieselPrice;
    }
}
