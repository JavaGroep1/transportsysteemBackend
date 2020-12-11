package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Order.Orderstatus;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderContainerTest {
    private OrderContainer container;
    private IOrderStore orderStore;

    @Before
    public void setup() {
        orderStore = mock(IOrderStore.class);
        container = new OrderContainer(orderStore);
    }

    @Test
    public void canAddOrder() throws IOException, InterruptedException, OrderInvalidException {
        //setup
        var sampleOrder = new Order();

        //execute
        container.addOrder(sampleOrder);
    }

    @Test
    public void getOrderByIdReturnsTicket() throws OrderNotFoundException {
        //setup
        var query = new ObjectId();
        var orderToReturn = new Order();

        //Mocking
        when(orderStore.getOrderById(query)).thenReturn(orderToReturn);

        //execute
        var res = container.getOrderById(query);

        //assert
        assertEquals(res, orderToReturn);
    }

    @Test
    public void getOrdersReturnsTickets() {
        //setup
        var listOfOrders = new ArrayList<Order>();
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());

        //Mocking
        when(orderStore.getOrders()).thenReturn(listOfOrders);

        //execute
        var res = container.getOrders();

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void getOrderByCustomerReturnsOrder() throws OrderNotFoundException {
        //setup
        var listOfOrders = new ArrayList<Order>();
        var user = new Customer(new ObjectId(), "name", "businessId", "adres", "phone", null);
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());

        //Mocking
        when(orderStore.getOrdersByCustomer(user)).thenReturn(listOfOrders);

        //execute
        var res = container.getOrdersByCustomer(user);

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void changeOrderStatus(){
        //setup
        var order = new Order();

        assertDoesNotThrow(() -> {
            //execute
            container.changeOrderStatus(order, Orderstatus.Cancelled);
        });
    }

    @Test
    public void changeOrderStatusToConfirmed(){
        //setup
        var order = new Order();

        assertDoesNotThrow(() -> {
            //execute
            container.changeOrderStatus(order, Orderstatus.Confirmed);
        });
    }
    @Test
    public void changeOrderDate(){
        //setup
        var order = new Order();
        assertDoesNotThrow(() -> {
            //execute
            container.changeOrderDate(order, LocalDate.now());
        });
    }

    @Test
    public void getActiveOrdersReturnsActiveTickets() throws OrderNotFoundException {
        //setup
        var listOfOrders = new ArrayList<Order>();
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());

        //Mocking
        when(orderStore.getActiveOrders()).thenReturn(listOfOrders);

        //execute
        var res = container.getActiveOrders();

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void getActiveOrdersByCustomerReturnsActiveTickets() throws OrderNotFoundException {
        //setup
        var listOfOrders = new ArrayList<Order>();
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());
        var user = new Customer(new ObjectId(), "name", "businessId", "adres", "phone", null);

        //Mocking
        when(orderStore.getActiveOrdersByCustomer(user)).thenReturn(listOfOrders);

        //execute
        var res = container.getActiveOrdersByCustomer(user);

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void getPendingOrdersPendingActiveTickets() throws OrderNotFoundException {
        //setup
        var listOfOrders = new ArrayList<Order>();
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());

        //Mocking
        when(orderStore.getPendingOrders()).thenReturn(listOfOrders);

        //execute
        var res = container.getPendingOrders();

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void getPendingOrdersByCustomerReturnsPendingTickets() throws OrderNotFoundException {
        //setup
        var listOfOrders = new ArrayList<Order>();
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());
        var user = new Customer(new ObjectId(), "name", "businessId", "adres", "phone", null);

        //Mocking
        when(orderStore.getPendingOrdersByCustomer(user)).thenReturn(listOfOrders);

        //execute
        var res = container.getPendingOrdersByCustomer(user);

        //assert
        assertEquals(res, listOfOrders);
    }

    @Test
    public void deleteOrder(){

        //setup
        var query = new ObjectId();

        //execute
        container.deleteOrder(query);
    }

}