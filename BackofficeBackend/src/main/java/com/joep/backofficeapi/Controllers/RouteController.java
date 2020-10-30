package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.Util.RouteUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"*"})
public class RouteController {
    @GetMapping("/route")
    public ResponseEntity<?> getRoute() throws IOException, InterruptedException {
        var route= RouteUtility.getRoute("Veltackerstraat 3, diessen", "Professor goossenlaan 1, Tilburg");
        assert route != null;
        return ResponseEntity.ok(route);
    }
}
