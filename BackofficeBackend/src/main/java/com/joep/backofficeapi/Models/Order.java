package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

import java.util.Date;

@Entity
public class Order {

    @Id
    private int Id;

    private final Date dateOrdered;
    private final Date deadline;
    private final String startingPoint;
    private final String destination;
    private final Orderstatus orderStatus;

    @Reference
    private final Vehicle vehicle;

    @Reference
    private final Customer customer;

    public Order(Date dateOrdered, Date deadline, String startingPoint, String destination, Orderstatus orderStatus, Vehicle vehicle, Customer customer) {
        this.dateOrdered = dateOrdered;
        this.deadline = deadline;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
        this.customer = customer;
    }

    public int getId() {
        return Id;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public Date getDeadline() {
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
