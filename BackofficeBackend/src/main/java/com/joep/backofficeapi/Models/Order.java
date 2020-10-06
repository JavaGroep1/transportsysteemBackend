package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Order {

    @Id
    private ObjectId Id;

    private  LocalDate dateOrdered;
    private  LocalDate deadline;
    private  String startingPoint;
    private  String destination;
    private  Orderstatus orderStatus;

    private  Vehicle vehicle;

    @Reference
    private  Customer customer;

    public Order(LocalDate dateOrdered, LocalDate deadline, String startingPoint, String destination, Orderstatus orderStatus, Vehicle vehicle, Customer customer) {
        this.dateOrdered = dateOrdered;
        this.deadline = deadline;
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
}
