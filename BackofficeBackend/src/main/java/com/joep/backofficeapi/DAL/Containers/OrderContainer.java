package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
import org.bson.types.ObjectId;

import java.util.List;

public class OrderContainer implements IOrderStore {

    private final IOrderStore orderStore;
    public OrderContainer(IOrderStore orderStore) {
        this.orderStore = orderStore;
    }

    @Override
    public void addOrder(Order order) {
        orderStore.addOrder(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderStore.getOrders();
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) throws OrderNotFoundException {
        return orderStore.getOrdersByCustomer(customer);
    }

    @Override
    public Order getOrderById(ObjectId id) throws OrderNotFoundException {
        return orderStore.getOrderById(id);
    }

    @Override
    public void changeOrderStatus(Order order, Orderstatus newOrderStatus) {
        orderStore.changeOrderStatus(order, newOrderStatus);
    }

    @Override
    public List<Order> getActiveOrders() throws OrderNotFoundException {
        return orderStore.getActiveOrders();
    }

    @Override
    public List<Order> getActiveOrdersByCustomer(Customer customer) throws OrderNotFoundException {
        return orderStore.getActiveOrdersByCustomer(customer);
    }

    @Override
    public List<Order> getPendingOrders() throws OrderNotFoundException {
        return orderStore.getPendingOrders();
    }

    @Override
    public List<Order> getPendingOrdersByCustomer(Customer customer) throws OrderNotFoundException {
        return orderStore.getPendingOrdersByCustomer(customer);
    }
}
