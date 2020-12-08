package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VehicleContainerTest {
    private VehicleContainer container;
    private IVehicleStore vehicleStore;

    @Before
    public void setup(){
        vehicleStore = mock(IVehicleStore.class);
        container = new VehicleContainer(vehicleStore);
    }

    @Test
    public void canAddVehicle(){
        //setup
        var sampleVehicle = new Vehicle();

        //execute
        container.addVehicle(sampleVehicle);
    }

    @Test
    public void getVehicleByIdReturnsVehicle() throws VehicleNotFoundException {
        //setup
        var query = new ObjectId();
        var vehicleToReturn = new Vehicle();

        //Mocking
        when(vehicleStore.getVehicleById(query)).thenReturn(vehicleToReturn);

        //execute
        var res = container.getVehicleById(query);

        //assert
        assertEquals(res, vehicleToReturn);
    }

    @Test
    public void getVehicleByPlateReturnsVehicle() throws VehicleNotFoundException {
        //setup
        var query = "PLATE";
        var vehicleToReturn = new Vehicle();

        //Mocking
        when(vehicleStore.getVehicleByPlate(query)).thenReturn(vehicleToReturn);

        //execute
        var res = container.getVehicleByPlate(query);

        //assert
        assertEquals(res, vehicleToReturn);
    }

    @Test
    public void getVehiclesReturnsList(){
        //setup
        var listOfVehicles = new ArrayList<Vehicle>();
        listOfVehicles.add(new Vehicle());
        listOfVehicles.add(new Vehicle());

        //Mocking
        when(vehicleStore.getVehicles()).thenReturn(listOfVehicles);

        //execute
        var res = container.getVehicles();

        //assert
        assertEquals(res, listOfVehicles);
    }

    @Test
    public void getVehiclesByCatReturnsList(){
        //setup
        var cat = VehicleCategory.Car;
        var listOfVehicles = new ArrayList<Vehicle>();
        listOfVehicles.add(new Vehicle());
        listOfVehicles.add(new Vehicle());

        //Mocking
        when(vehicleStore.getVehiclesByCategory(cat)).thenReturn(listOfVehicles);

        //execute
        var res = container.getVehiclesByCategory(cat);

        //assert
        assertEquals(res, listOfVehicles);
    }

    @Test
    public void canDeleteVehicle(){
        //setup
        var vehicle = new ObjectId();

        //execute
        container.deleteVehicle(vehicle);
    }

    @Test
    public void canUpdateVehicle() throws VehicleNotFoundException {
        //setup
        var vehicleId = new ObjectId();
        var update = new EditVehicleRequest(vehicleId.toString(), 1131, "newplate", VehicleCategory.Bike,0);
        var vehicle = new Vehicle();
        //mocking
        when(vehicleStore.getVehicleById(vehicleId)).thenReturn(vehicle);
        //execute
        container.updateVehicle(update);
    }

    @Test
    public void cantUpdateVehicleWithNullId() throws VehicleNotFoundException {
        //setup
        var vehicle = new ObjectId();
        var update = new EditVehicleRequest(null, 0, null, null,0);

        //execute
        assertThrows(IllegalArgumentException.class, () -> {
            container.updateVehicle(update);
        });
    }
    @Test
    public void canUpdateVehicleWithNulls() throws VehicleNotFoundException {
        //setup
        var vehicleId = new ObjectId();
        var update = new EditVehicleRequest(vehicleId.toString(), 0, "", VehicleCategory.Car,0);
        var vehicle = new Vehicle();
        //mocking
       when(vehicleStore.getVehicleById(vehicleId)).thenReturn(vehicle);
        //execute
        container.updateVehicle(update);
    }

    @Test
    public void canUpdateVehicleCat() throws VehicleNotFoundException {
        //setup
        var vehicle = new Vehicle();

        //execute
        container.changeVehicleCategory(vehicle, VehicleCategory.Plane);
    }

    @Test
    public void getVehiclesByPositiveWeight() throws VehicleNotFoundException{

        //setup
        var weight = 500;
        var listOfVehicles = new ArrayList<Vehicle>();
        listOfVehicles.add(new Vehicle(null, null,200,0));
        listOfVehicles.add(new Vehicle(null, null,500,0));

        //Mocking
        when(vehicleStore.getVehiclesByWeight(weight)).thenReturn(listOfVehicles);
        when(vehicleStore.getVehicles()).thenReturn(listOfVehicles);

        //execute
        var res = container.getVehiclesByWeight(weight);

        //assert
        assertEquals(res, listOfVehicles);
    }

    @Test
    public void getVehiclesByTooMuchWeight() throws VehicleNotFoundException{

        //setup
        var weight = 1000;
        var listOfVehicles = new ArrayList<Vehicle>();
        listOfVehicles.add(new Vehicle(null, null,200,0));
        listOfVehicles.add(new Vehicle(null, null,500,0));

        //Mocking
        when(vehicleStore.getVehiclesByWeight(weight)).thenReturn(listOfVehicles);
        when(vehicleStore.getVehicles()).thenReturn(listOfVehicles);

        //execute
        var res = container.getVehiclesByWeight(weight);

        //assert
        assertEquals(res, new ArrayList<>());
    }

    @Test
    public void getVehiclesByNegativeWeight() throws VehicleNotFoundException{

        //setup
        var weight = -250;
        var listOfVehicles = new ArrayList<Vehicle>();
        listOfVehicles.add(new Vehicle(null, null,250,0));
        listOfVehicles.add(new Vehicle(null, null,250,0));

        //Mocking
        when(vehicleStore.getVehiclesByWeight(weight)).thenReturn(listOfVehicles);
        when(vehicleStore.getVehicles()).thenReturn(listOfVehicles);

        //execute
        var res = container.getVehiclesByWeight(weight);

        //assert
        assertEquals(res, new ArrayList<>());
    }
}