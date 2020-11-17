package com.joep.backofficeapi.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joep.backofficeapi.Exceptions.RouteInvalidException;
import com.joep.backofficeapi.Models.Route.Route;
import com.joep.backofficeapi.Models.Route.Route_;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class RouteUtility {
    private static String API_KEY = "fwEl8C7Wi53YNXdSo9ljSZrpU6MUN1Zb";
    public static double dieselPrice = 1.27;

    private static double KmPerLiterToMilesPerGallon(double KmPerLiter){
        return KmPerLiter * 2.352;
    }
    public static Route_ getRoute(String startAdress, String destination, VehicleCategory vehicleCategory, double KmPerLiter) throws IOException, InterruptedException, RouteInvalidException {
        if (StringUtils.isBlank(startAdress) || StringUtils.isBlank(destination)) throw  new RouteInvalidException();
        HttpClient client = HttpClient.newHttpClient();
        String CarGet = MessageFormat.format("http://www.mapquestapi.com/directions/v2/route?key={0}" +
                        "&from={1}&to={2}" +
                        "&locale=nl_NL"+
                        "&highwayEfficiency={3}",
                API_KEY,
                startAdress,
                destination,
                KmPerLiterToMilesPerGallon(KmPerLiter)).replaceAll(" ", "%20");;

        String BikeGet = MessageFormat.format("http://www.mapquestapi.com/directions/v2/route?key={0}" +
                        "&from={1}" +
                        "&to={2}" +
                        "&locale=nl_NL"+
                        "&cyclingRoadFactor=100",
                API_KEY,
                startAdress,
                destination).replaceAll(" ", "%20");
        HttpRequest request;
        if (vehicleCategory == VehicleCategory.Bike){
            request = HttpRequest.newBuilder()
                    .uri(URI.create(BikeGet))
                    .build();
        }
        else {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(CarGet))
                    .build();
        }

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
