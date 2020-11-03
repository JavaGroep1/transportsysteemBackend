package com.joep.backofficeapi.Models.Requests.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class AddOrderRequest {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;
    private int weightInKg;
    private  String startingPoint;
    private  String destination;
    private ObjectId vehicleId;

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public ObjectId getVehicleId() {
        return vehicleId;
    }
}
