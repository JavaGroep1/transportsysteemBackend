package com.joep.backofficeapi.DAL.Stores.CustomerStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
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
        return datastore.find(Customer.class).filter(Filters.eq("Id", id)).first();
    }

    @Override
    public List<Customer> getActiveCustomers() {
        return datastore.find(Customer.class).filter(Filters.eq("Role", Roles.Customer)).iterator().toList();

    }

    @Override
    public void deleteCustomer(String businessIdentifier) {
        datastore.find(Customer.class)
                .filter(Filters.eq("businessIdentifier", businessIdentifier))
                .delete();
    }

    @Override
    public void updateCustomer(EditCustomerRequest editCustomerRequest) {
        var id = editCustomerRequest.getCustomerIdString();
        datastore.find(Customer.class)
                .filter(Filters.eq("Id", id))
                .update(UpdateOperators.set("name", editCustomerRequest.getName()),
                        UpdateOperators.set("businessIdentifier", editCustomerRequest.getBusinessIdentifier()),
                        UpdateOperators.set("address", editCustomerRequest.getAddress()),
                        UpdateOperators.set("phoneNumber", editCustomerRequest.getPhoneNumber()))
                .execute();
    }



}
