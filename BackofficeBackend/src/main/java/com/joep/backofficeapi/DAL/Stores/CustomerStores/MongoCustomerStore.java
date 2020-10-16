package com.joep.backofficeapi.DAL.Stores.CustomerStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Util.JwtUtil;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoCustomerStore implements ICustomerStore {

    @Autowired
    private UserStoreContainer userStoreContainer;

    @Autowired
    private JwtUtil jwtUtil;

    private final Datastore datastore;
    public MongoCustomerStore() {
        datastore = Morphia.createDatastore(MongoClients.create(ConnectionConfiguration.getMongoConnectionString()), "backoffice");
        datastore.getMapper().mapPackage("org.joep.BackofficeBackend");
        datastore.ensureIndexes();
    }
    @Override
    public void addCustomer(Customer Customer) {
        datastore.save(Customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return datastore.find(Customer.class).iterator().toList();
    }

    @Override
    public Customer getCustomerByJwt(String jwt) throws Exception {
        var user = userStoreContainer.getUserByName(jwtUtil.extractUsername(jwt));
        return user.getCustomer();
    }

    @Override
    public Customer getCustomerById(ObjectId id){
        return datastore.find(Customer.class).filter(Filters.eq("Id", id.toString())).first();
    }

    @Override
    public List<Customer> getActiveCustomers() {
        return datastore.find(Customer.class).filter(Filters.eq("isProspect", false)).iterator().toList();

    }

    @Override
    public void changeCustomerRole(Customer customer, Roles role) {
        datastore.find(Customer.class)
                .filter(Filters.eq("Id", customer.getId()))
                .update(UpdateOperators.set("orderStatus", role))
                .execute();
    }

    @Override
    public void deleteCustomer(String businessIdentifier) {
        datastore.find(Customer.class)
                .filter(Filters.eq("businessIdentifier", businessIdentifier))
                .delete();
    }


}
