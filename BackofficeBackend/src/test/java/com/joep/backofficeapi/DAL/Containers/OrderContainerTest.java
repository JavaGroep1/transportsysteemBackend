package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Order.Orderstatus;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderContainerTest {
    private OrderContainer container;
    private IOrderStore orderStore;

    @Before
    public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
    public void getOrderByIdReturnsTicket() throws UnsupportedEncodingException, NoSuchAlgorithmException, OrderNotFoundException {
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
    public void getOrdersReturnsTickets() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
    public void getOrderByCustomerReturnsOrder() throws UnsupportedEncodingException, NoSuchAlgorithmException, OrderNotFoundException {
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

}