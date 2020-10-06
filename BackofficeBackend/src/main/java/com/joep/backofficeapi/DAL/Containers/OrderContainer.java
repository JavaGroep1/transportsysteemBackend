package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
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
    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderStore.getOrdersByCustomer(customer);
    }

    @Override
    public Order getOrderById(int id) {
        return orderStore.getOrderById(id);
    }

    @Override
    public void changeOrderStatus(Order order, Orderstatus newOrderStatus) {
        orderStore.changeOrderStatus(order, newOrderStatus);
    }
}
