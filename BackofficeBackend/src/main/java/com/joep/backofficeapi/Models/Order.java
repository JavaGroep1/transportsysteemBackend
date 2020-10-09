package com.joep.backofficeapi.Models;

import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Util.RouteUtility;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Order {

    @Id
    private ObjectId Id;

    private  LocalDate dateOrdered;
    private  LocalDate deadline;
    private  LocalDate dateDelivered;
    private  double weightInKg;
    private double distanceInKm;
    private double fuelUsed;
    private double timeTaken;
    private double cost;
    private  String startingPoint;
    private  String destination;
    private  Orderstatus orderStatus;

    private  Vehicle vehicle;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Reference
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

        var order = RouteUtility.getRoute(startingPoint, destination);
        if (order == null) throw new OrderInvalidException();
        this.distanceInKm = order.getDistance();
        this.fuelUsed = order.getFuelUsed();
        this.cost = RouteUtility.getRoutePrice(this.fuelUsed);
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
