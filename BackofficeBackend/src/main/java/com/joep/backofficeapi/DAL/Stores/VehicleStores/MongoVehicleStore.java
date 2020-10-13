package com.joep.backofficeapi.DAL.Stores.VehicleStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Vehicle;
import com.joep.backofficeapi.Models.VehicleCategory;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import dev.morphia.query.internal.MorphiaCursor;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoVehicleStore implements IVehicleStore {

    private final Datastore datastore;
    public MongoVehicleStore() {
        datastore = Morphia.createDatastore(MongoClients.create(ConnectionConfiguration.getMongoConnectionString()), "backoffice");
        datastore.getMapper().mapPackage("org.joep.BackofficeBackend");
        datastore.ensureIndexes();
    }


    @Override
    public void addVehicle(Vehicle Vehicle) {
        datastore.save(Vehicle);
    }

    @Override
    public List<Vehicle> getVehicles() {
        MorphiaCursor<Vehicle> query = datastore.find(Vehicle.class).iterator();
        return query.toList();
    }

    @Override
    public Vehicle getVehicleById(ObjectId id) throws VehicleNotFoundException {
        Vehicle result = datastore.find(Vehicle.class).filter(Filters.eq("Id", id)).first();
        if (result != null) return result;

        throw new VehicleNotFoundException();
    }

    @Override
    public void changeVehicleCategory(Vehicle Vehicle, VehicleCategory newVehicleCat) {
        datastore.find(Order.class)
                .filter(Filters.eq("Id", Vehicle.getId()))
                .update(UpdateOperators.set("vehicleCategory", newVehicleCat))
                .execute();
    }
}
