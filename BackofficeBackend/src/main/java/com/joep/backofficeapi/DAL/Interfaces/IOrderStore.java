package com.joep.backofficeapi.DAL.Interfaces;
import com.joep.backofficeapi.Models.*;


import java.util.List;

public interface IOrderStore {

    void addOrder(Order order);
    List<Order> getOrders();
    List<Order> getOrdersByCustomer(Customer customer);
    Order getOrderById(int id);
    void changeOrderStatus(Order order, Orderstatus newOrderStatus);

}
