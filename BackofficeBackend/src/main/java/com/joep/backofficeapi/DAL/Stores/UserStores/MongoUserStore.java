package com.joep.backofficeapi.DAL.Stores.UserStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Exceptions.UserNotFoundException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.List;

@Component
public class MongoUserStore implements IUserStore {

    private final Datastore datastore;

    public MongoUserStore() throws UnknownHostException {

        datastore = Morphia.createDatastore(MongoClients.create(ConnectionConfiguration.getMongoConnectionString()), "backoffice");
        datastore.getMapper().mapPackage("org.joep.BackofficeBackend");
        datastore.ensureIndexes();
    }
    @Override
    public List<ApplicationUser> getAllUsers() {
        var list = datastore.find(ApplicationUser.class).iterator().toList();
        return list;
    }

    @Override
    public ApplicationUser getUserByName(String name) throws Exception {
        var user=  datastore.find(ApplicationUser.class).filter(Filters.eq("username", name)).first();
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public Boolean createUser(ApplicationUser user) {

        datastore.save(user);

        return true;
    }
    @Override
    public Boolean emailExists(String email) {

        var res = datastore.find(ApplicationUser.class).filter(Filters.eq("email", email)).iterator();

        return res.hasNext();
    }
    @Override
    public Boolean usernameExists(String name) {
        var res = datastore.find(ApplicationUser.class).filter(Filters.eq("username", name)).iterator();

        return res.hasNext();
    }

    @Override
    public List<ApplicationUser> getByRole(Roles role) {
        return datastore.find(ApplicationUser.class).filter(Filters.eq("role", role)).iterator().toList();

    }

    @Override
    public void changeRole(ObjectId customerId, Roles role) {
        datastore.find(ApplicationUser.class)
                .filter(Filters.eq("customer", customerId))
                .update(UpdateOperators.set("role", role))
                .execute();
    }

    @Override
    public void deleteAccount(String businessIdentifier) {
        Customer customer = datastore.find(Customer.class)
                .filter(Filters.eq("businessIdentifier", businessIdentifier)).first();
        datastore.find(ApplicationUser.class)
                .filter(Filters.eq("customer", customer.getId()))
                .delete();
    }

}

