package com.joep.backofficeapi.DAL.Stores.CustomerStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.apache.commons.lang3.NotImplementedException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoCustomerStore implements ICustomerStore {

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
        throw new NotImplementedException();
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
