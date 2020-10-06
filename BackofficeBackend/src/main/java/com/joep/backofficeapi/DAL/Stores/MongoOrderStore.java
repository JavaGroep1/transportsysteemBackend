package com.joep.backofficeapi.DAL.Stores;

import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.experimental.filters.Filters;

import java.util.List;

public class MongoOrderStore implements IOrderStore {

    private final Datastore datastore;
    public MongoOrderStore() {
        datastore = Morphia.createDatastore(MongoClients.create(), "backoffice");
        datastore.getMapper().mapPackage("org.joep.BackofficeBackend");
        datastore.ensureIndexes();
    }

    @Override
    public void addOrder(Order order) {
        datastore.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return datastore.find(Order.class).iterator().toList();
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        return datastore.find(Order.class).filter(Filters.lte("customer", customer.getId())).iterator().toList();
    }

    @Override
    public Order getOrderById(int id) {
        return datastore.find(Order.class).filter(Filters.lte("Id", id)).first();
    }

    @Override
    public void changeOrderStatus(Order order, Orderstatus newOrderStatus) {

        Query<Order> query = datastore.find(Order.class).filter(Filters.lte("Id", order.getId()));
        UpdateOperations<Order> ops = datastore.createUpdateOperations(Order.class)
                .set("orderStatus", newOrderStatus);

        datastore.update(query, ops);
    }
}
