package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import com.joep.backofficeapi.Util.Sanitizers.VehicleSanitizer;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class VehicleContainer implements IVehicleStore {

    private final IVehicleStore vehicleStore;

    public VehicleContainer(IVehicleStore vehicleStore) {
        this.vehicleStore = vehicleStore;
    }

    @Override
    public void addVehicle(Vehicle Vehicle) {
        vehicleStore.addVehicle(Vehicle);
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleStore.getVehicles();
    }

    @Override
    public List<Vehicle> getVehiclesByCategory(VehicleCategory cat) {
        return vehicleStore.getVehiclesByCategory(cat);
    }

    @Override
    public Vehicle getVehicleByPlate(String plate) throws VehicleNotFoundException {
        return vehicleStore.getVehicleByPlate(plate);
    }

    @Override
    public Vehicle getVehicleById(ObjectId id) throws VehicleNotFoundException {
        return vehicleStore.getVehicleById(id);
    }

    @Override
    public void deleteVehicle(ObjectId vehicleId) {
        vehicleStore.deleteVehicle(vehicleId);
    }

    @Override
    public void changeVehicleCategory(Vehicle Vehicle, VehicleCategory newVehicleCat) {
        vehicleStore.changeVehicleCategory(Vehicle, newVehicleCat);
    }

    @Override
    public void updateVehicle(EditVehicleRequest newVehicle) throws VehicleNotFoundException {
        var vehicle = vehicleStore.getVehicleById(new ObjectId(newVehicle.vehicleIdString));
        VehicleSanitizer.sanitize(newVehicle, vehicle);
        vehicleStore.updateVehicle(newVehicle);
    }

    @Override
    public List<Vehicle> getVehiclesByWeight(int weight) throws VehicleNotFoundException {

        List<Vehicle> vehicles = vehicleStore.getVehicles();

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        int closest = Integer.MAX_VALUE;
        int distance = closest - weight;
        for (var vehicle : vehicles) {
            int distanceI = vehicle.getCapacityInKG() - weight;
            if (distance >= distanceI && distanceI >= 0) {
                closest = vehicle.getCapacityInKG();
                distance = distanceI;
            }
        }

        for (var item: vehicleStore.getVehiclesByWeight(closest)){
            vehicleList.add(item);
        }
//        vehicleStore.getVehiclesByWeight(closest));


//        int distance = vehicles.get(0).getCapacityInKG() - weight;
//        int distance = weight;

        /* Start -> Pak alle vehicles -> Kijk voor elk vehicle of de capaciteit groter is dan t gewicht ->
         *  JA -> kijk of de afstand tussen de capaciteit en het gewicht niet groter is dan het gewicht zelf of niet negatief is ->
         *       JA -> Voeg het vehicle toe
         *       NEE -> Doe niets -> start
         *   Zijn er geen vehicles in de lijst?
         *   JA -> Pak dan t grootste vehicle
         *   NEE -> Start
         *
         *  NEE -> start
         *  */


        /*
        Je hebt vehicles,
            kijk voor elk vehicle of ie kleiner(capaciteit) is dan t gewicht, ja -> sla over volgende
            Als je een vehicle vind die groter is dan t gewicht, kijk of de afstand (capaciteit - gewicht) positief is?
            Voeg m toe



         */


//        int value = 0;


//        for (var veh: vehicles) {
//            if (veh.getCapacityInKG() > weight) {
//                int afstand = veh.getCapacityInKG() - weight;
//                if (afstand <= weight && afstand >= 0) {
//                    vehicleList.add(veh);
//                }
//            }
//        }


//        int distance = 20000;
//        int idx = 0;
//        for (int c = 0; c < vehicles.size(); c++) {
//            int cdistance = vehicles.get(c).getCapacityInKG() - weight;
//            if (cdistance <= distance && cdistance >= 0) {
//                idx = c;
//                distance = cdistance;
//                vehicleList.add(vehicles.get(c));
//            }
//        }
//        if(vehicleStore.getVehiclesByWeight(vehicleList.get(0).getCapacityInKG()).size() > 1){
//            vehicleList.clear();
//            for (var veh: vehicleStore.getVehiclesByWeight(vehicleList.get(0).getCapacityInKG())) {
//                vehicleList.add(veh);
//            }
//        }
        return vehicleList;
    }
}
