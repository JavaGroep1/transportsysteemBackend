package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.Models.Route.Route_;
import com.joep.backofficeapi.Util.RouteUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"*"})
public class RouteController {

    @GetMapping(value = "/route", params = {"startingpoint", "destination"})
    public ResponseEntity<Route_> getRoute(String startingpoint, String destination) throws IOException, InterruptedException {
        var route= RouteUtility.getRoute(startingpoint, destination);
        assert route != null;
        return ResponseEntity.ok(route);
    }
}
