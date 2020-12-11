package com.joep.backofficeapi.Util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationUtilityTest {


    @Test
    public void GetLocationReturnsCorrect(){
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("Professor goossenlaan");
        });
    }

    @Test
    public void GetLocationBadInput(){
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("6toi78t");
        });
    }

    @Test
    public void GetLocationEmpty(){
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            LocationUtility.getLocation("");
        });
    }

    @Test
    public void ReturnLocation() throws InterruptedException, IOException {
        //Setup
        var res = LocationUtility.getLocation("Professor goossenlaan, Tilburg");

        //Assert
        assertNotNull(res);
        assertTrue(res.getItems().size() > 0);
    }
}

