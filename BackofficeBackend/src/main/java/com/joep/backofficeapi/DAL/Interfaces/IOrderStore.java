package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Order.Orderstatus;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IOrderStore {

    void addOrder(Order order) throws OrderInvalidException, IOException, InterruptedException;
    List<Order> getOrders();
    List<Order> getOrdersByCustomer(Customer customer) throws OrderNotFoundException;
    Order getOrderById(ObjectId id) throws OrderNotFoundException;
    void changeOrderStatus(Order order, Orderstatus newOrderStatus);
    List<Order> getActiveOrders() throws OrderNotFoundException;
    List<Order> getActiveOrdersByCustomer(Customer customer) throws OrderNotFoundException;
    List<Order> getPendingOrders() throws OrderNotFoundException;
    List<Order> getPendingOrdersByCustomer(Customer customer) throws OrderNotFoundException;
    void changeOrderDate(Order order, LocalDate date);
    void deleteOrder(String id);

}
