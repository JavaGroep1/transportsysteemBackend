package com.joep.backofficeapi.Util;

import com.joep.backofficeapi.Exceptions.RouteInvalidException;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RouteUtilityTest {

    @Test
    public void GetRoutePriceReturnsCorrectPrice(){
        assertEquals(RouteUtility.getRoutePrice(100), 100*RouteUtility.dieselPrice);
    }

    @Test
    public void RouteThrowsErrorWithBadInput() throws IOException, InterruptedException, RouteInvalidException {
        Assertions.assertThrows(RouteInvalidException.class, () -> {
            RouteUtility.getRoute(" ", "", VehicleCategory.Car, 10);
        });
    }

    @Test
    public void RouteDoesNotThrowErrorWithGoodInput() throws IOException, InterruptedException, RouteInvalidException {
        Assertions.assertDoesNotThrow(() -> {
            RouteUtility.getRoute("Veltackerstraat", "Professor goossenlaan", VehicleCategory.Car, 10);
        });
    }

    @Test
    public void RouteReturnsRoute() throws InterruptedException, RouteInvalidException, IOException {
        //execute
        var res = RouteUtility.getRoute("Veltackerstraat 3, Diessen", "Professor goossenlaan, Tilburg", VehicleCategory.Car, 10);

        assertNotNull(res);
        assertTrue(res.getLocations().size() > 0);
    }

}