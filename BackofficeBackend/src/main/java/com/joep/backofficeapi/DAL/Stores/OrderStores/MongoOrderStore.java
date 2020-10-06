package com.joep.backofficeapi.DAL.Stores.OrderStores;

import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.UpdateResult;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.internal.MorphiaCursor;
import org.bson.types.ObjectId;

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
        MorphiaCursor query = datastore.find(Order.class).iterator();
        return query.toList();
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        return datastore.find(Order.class).filter(Filters.eq("customer", customer.getId())).iterator().toList();
    }

    @Override
    public Order getOrderById(ObjectId id) throws OrderNotFoundException {
        Order result = datastore.find(Order.class).filter(Filters.eq("Id", id)).first();
        if (result != null) return result;

        throw new OrderNotFoundException();
    }

    @Override
    public void changeOrderStatus(Order order, Orderstatus newOrderStatus) {

        Query<Order> query = datastore.find(Order.class).filter(Filters.eq("Id", order.getId()));
        UpdateOperations<Order> ops = datastore.createUpdateOperations(Order.class)
                .set("orderStatus", newOrderStatus);

        UpdateResult res = datastore.update(query, ops);

        System.out.println(res);
    }
}
