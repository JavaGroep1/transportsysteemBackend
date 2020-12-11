package com.joep.backofficeapi.DAL.Stores.OrderStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Order.Orderstatus;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import dev.morphia.query.internal.MorphiaCursor;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public class MongoOrderStore implements IOrderStore {

    private final Datastore datastore;
    public MongoOrderStore() {
        datastore = Morphia.createDatastore(MongoClients.create(ConnectionConfiguration.getMongoConnectionString()), "backoffice");
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
    public List<Order> getOrdersByCustomer(Customer customer) throws OrderNotFoundException {
         MorphiaCursor<Order> cursor = datastore.find(Order.class).filter(Filters.eq("customer", customer.getId())).iterator();
         if (cursor.hasNext()){
             return cursor.toList();
         }

         throw new OrderNotFoundException();
    }

    @Override
    public Order getOrderById(ObjectId id) throws OrderNotFoundException {
        Order result = datastore.find(Order.class).filter(Filters.eq("Id", id)).first();
        if (result != null) return result;

        throw new OrderNotFoundException();
    }

    @Override
    public void changeOrderStatus(Order order, Orderstatus newOrderStatus) {

        datastore.find(Order.class)
                 .filter(Filters.eq("Id", order.getId()))
                 .update(UpdateOperators.set("orderStatus", newOrderStatus))
                 .execute();
    }

    @Override
    public List<Order> getActiveOrders() throws OrderNotFoundException {
        MorphiaCursor<Order> cursor = datastore.find(Order.class)
                .filter(Filters.eq("orderStatus", Orderstatus.Confirmed))
                .iterator();
        if (cursor.hasNext()){
            return cursor.toList();
        }

        throw new OrderNotFoundException();
    }

    @Override
    public List<Order> getActiveOrdersByCustomer(Customer customer) throws OrderNotFoundException {
        MorphiaCursor<Order> cursor = datastore.find(Order.class)
                .filter(Filters.eq("orderStatus", Orderstatus.Confirmed))
                .filter(Filters.eq("customer", customer.getId()))
                .iterator();

        if (cursor.hasNext()){
            return cursor.toList();
        }

        throw new OrderNotFoundException();
    }

    @Override
    public List<Order> getPendingOrders() throws OrderNotFoundException {
        MorphiaCursor<Order> cursor = datastore.find(Order.class)
                .filter(Filters.eq("orderStatus", Orderstatus.Pending))
                .iterator();

        if (cursor.hasNext()){
            return cursor.toList();
        }

        throw new OrderNotFoundException();
    }

    @Override
    public List<Order> getPendingOrdersByCustomer(Customer customer) throws OrderNotFoundException {
        MorphiaCursor<Order> cursor = datastore.find(Order.class)
                .filter(Filters.eq("orderStatus", Orderstatus.Pending))
                .filter(Filters.eq("customer", customer.getId()))
                .iterator();

        if (cursor.hasNext()){
            return cursor.toList();
        }

        throw new OrderNotFoundException();
    }

    @Override
    public void changeOrderDate(Order order, LocalDate date) {
        datastore.find(Order.class)
                .filter(Filters.eq("Id", order.getId()))
                .update(UpdateOperators.set("dateStarted", date))
                .execute();

    }

    @Override
    public void deleteOrder(ObjectId id){
        datastore.find(Order.class)
                .filter(Filters.eq("Id", id))
                .delete();
    }
}
