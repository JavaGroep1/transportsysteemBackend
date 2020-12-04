package com.joep.backofficeapi.Util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LocationUtilityTest {


    @Test
    public void GetLocationReturnsCorrect(){
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("Professor goossenlaan");
        });
    }

    @Test
    public void GetLocationBadInput(){
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("6toi78t");
        });
    }

    @Test
    public void GetLocationEmpty(){
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("");
        });
    }

    @Test
    public void ReturnLocation() throws InterruptedException, IOException {
        var res = LocationUtility.getLocation("Professor goossenlaan, Tilburg");

        assertNotNull(res);
        assertTrue(res.getItems().size() > 0);
    }
}
