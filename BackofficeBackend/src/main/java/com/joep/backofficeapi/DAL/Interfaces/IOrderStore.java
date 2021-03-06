package com.joep.backofficeapi.DAL.Interfaces;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.*;
import org.bson.types.ObjectId;


import java.util.List;

public interface IOrderStore {

    void addOrder(Order order);
    List<Order> getOrders();
    List<Order> getOrdersByCustomer(Customer customer);
    Order getOrderById(ObjectId id) throws OrderNotFoundException;
    void changeOrderStatus(Order order, Orderstatus newOrderStatus);

}
