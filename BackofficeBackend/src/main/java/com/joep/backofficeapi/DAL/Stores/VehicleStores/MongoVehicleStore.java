package com.joep.backofficeapi.DAL.Stores.VehicleStores;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
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
    public List<Vehicle> getVehiclesByCategory(VehicleCategory cat) {
        return datastore.find(Vehicle.class).filter(Filters.eq("vehicleCategory", cat)).iterator().toList();
    }

    @Override
    public Vehicle getVehicleByPlate(String plate) throws VehicleNotFoundException {
        Vehicle result = datastore.find(Vehicle.class).filter(Filters.eq("licensePlate", plate)).first();
        if (result != null) return result;

        throw new VehicleNotFoundException();
    }

    @Override
    public Vehicle getVehicleById(ObjectId id) throws VehicleNotFoundException {
        Vehicle result = datastore.find(Vehicle.class).filter(Filters.eq("id", id)).first();
        if (result != null) return result;

        throw new VehicleNotFoundException();
    }

    @Override
    public void deleteVehicle(ObjectId vehicleId) {
        datastore.find(Vehicle.class).filter(Filters.eq("id", vehicleId)).delete();
    }

    @Override
    public void changeVehicleCategory(Vehicle Vehicle, VehicleCategory newVehicleCat) {
        datastore.find(Vehicle.class)
                .filter(Filters.eq("id", Vehicle.getId()))
                .update(UpdateOperators.set("vehicleCategory", newVehicleCat))
                .execute();
    }

    @Override
    public void updateVehicle(EditVehicleRequest newVehicle) throws VehicleNotFoundException {
        var vehicleId = new ObjectId(newVehicle.getVehicleIdString());
        datastore.find(Vehicle.class)
                .filter(Filters.eq("id", vehicleId))
                .update(UpdateOperators.set("vehicleCategory", newVehicle.getNewVehicleCategory()),
                        UpdateOperators.set("licensePlate", newVehicle.getNewLicensePlate()),
                        UpdateOperators.set("capacityInKG", newVehicle.getNewCapacityInKg()),
                        UpdateOperators.set("kmPerLiter", newVehicle.getNewKmPerLiter()))
                .execute();

    }
}
