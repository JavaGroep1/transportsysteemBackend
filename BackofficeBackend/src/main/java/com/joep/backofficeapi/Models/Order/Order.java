package com.joep.backofficeapi.Models.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.time.LocalDate;


@Entity
public class Order {

    @Id
    private ObjectId Id;

    private String IdString;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOrdered;

    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate deadline;

    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate dateDelivered;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateStarted;

    private  double weightInKg;
    private double distanceInKm;
    private double fuelUsed;
    private double cost;
    private  String startingPoint;
    private  String destination;
    private  Orderstatus orderStatus;
    private Vehicle vehicle;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    private  Customer customer;

    public Order(LocalDate dateOrdered, LocalDate dateStarted, LocalDate deadline, double weightInKg, String startingPoint, String destination, Orderstatus orderStatus, Vehicle vehicle, Customer customer) throws IOException, InterruptedException, OrderInvalidException {
        this.dateOrdered = dateOrdered;
        this.deadline = deadline;
        this.weightInKg = weightInKg;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
        this.customer = customer;

    }

    public Order() {
    }

    public ObjectId getId() {
        return Id;
    }

    public LocalDate getDateOrdered() {
        return dateOrdered;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Orderstatus getOrderStatus() {
        return orderStatus;
    }

    public double getCost() {
        return cost;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public void setFuelUsed(double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFuelUsed() {
        return fuelUsed;
    }

    public String getIdString() {
        return Id.toString();
    }


}