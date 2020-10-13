package com.joep.backofficeapi.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Util.RouteUtility;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

@Entity
public class Order {

    @Id
    private ObjectId Id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOrdered;

    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate deadline;
    private  LocalDate dateDelivered;
    private  double weightInKg;
    private double distanceInKm;
    private double fuelUsed;
    private String timeTaken;
    private double cost;
    private  String startingPoint;
    private  String destination;
    private  Orderstatus orderStatus;

    private  Vehicle vehicle;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    private  Customer customer;

    public Order(LocalDate dateOrdered, LocalDate deadline, double weightInKg, String startingPoint, String destination, Orderstatus orderStatus, Vehicle vehicle, Customer customer) throws IOException, InterruptedException, OrderInvalidException {
        this.dateOrdered = dateOrdered;
        this.deadline = deadline;
        this.weightInKg = weightInKg;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
        this.customer = customer;


       // this.timeTaken = Duration.between(this.dateOrdered, this.dateDelivered).toString();
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


    public String getTimeTaken() {
        return timeTaken;
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
}
