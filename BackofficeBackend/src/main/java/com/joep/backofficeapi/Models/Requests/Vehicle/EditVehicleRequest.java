package com.joep.backofficeapi.Models.Requests.Vehicle;

import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;

public class EditVehicleRequest {
    public String vehicleIdString;
    public int NewCapacityInKg;
    public String NewLicensePlate;
    public VehicleCategory NewVehicleCategory;

    public double NewKmPerLiter;

    public EditVehicleRequest() {
    }

    public EditVehicleRequest(String vehicleIdString, int newCapacityInKg, String newLicensePlate, VehicleCategory newVehicleCategory,double newKmPerLiter) {
        this.vehicleIdString = vehicleIdString;
        NewCapacityInKg = newCapacityInKg;
        NewLicensePlate = newLicensePlate;
        NewKmPerLiter = newKmPerLiter;

        NewVehicleCategory = newVehicleCategory;
    }

    public void setVehicleIdString(String vehicleIdString) {
        this.vehicleIdString = vehicleIdString;
    }

    public void setNewCapacityInKg(int newCapacityInKg) {
        NewCapacityInKg = newCapacityInKg;
    }

    public void setNewLicensePlate(String newLicensePlate) {
        NewLicensePlate = newLicensePlate;
    }

    public void setNewVehicleCategory(VehicleCategory newVehicleCategory) {
        NewVehicleCategory = newVehicleCategory;
    }


    public void setNewKmPerLiter(double newKmPerLiter) {
        NewKmPerLiter = newKmPerLiter;
    }
    public double getNewKmPerLiter() {
        return NewKmPerLiter;
    }

    public String getVehicleIdString() {
        return vehicleIdString;
    }

    public int getNewCapacityInKg() {
        return NewCapacityInKg;
    }

    public String getNewLicensePlate() {
        return NewLicensePlate;
    }

    public VehicleCategory getNewVehicleCategory() {
        return NewVehicleCategory;
    }




}
