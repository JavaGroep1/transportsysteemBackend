package com.joep.backofficeapi.Models.Order;

import com.joep.backofficeapi.Models.Route.startPoint;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class Coordinates {
    @Id
    private ObjectId id;

    public double Lat;
    public double Long;

    public Coordinates() {
    }

    public Coordinates(startPoint startpoint) {
        this.setLat(startpoint.getLat());
        this.setLong(startpoint.getLng());
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }
}
