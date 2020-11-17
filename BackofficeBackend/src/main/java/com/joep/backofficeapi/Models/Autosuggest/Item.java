
package com.joep.backofficeapi.Models.Autosuggest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String title;
    private String id;
    private String resultType;
    private String houseNumberType;
    private Address address;
    private Position position;
    private List<Access> access = null;
    private Integer distance;
    private MapView mapView;
    private Highlights highlights;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param mapView
     * @param address
     * @param access
     * @param highlights
     * @param distance
     * @param houseNumberType
     * @param id
     * @param position
     * @param title
     * @param resultType
     */
    public Item(String title, String id, String resultType, String houseNumberType, Address address, Position position, List<Access> access, Integer distance, MapView mapView, Highlights highlights) {
        super();
        this.title = title;
        this.id = id;
        this.resultType = resultType;
        this.houseNumberType = houseNumberType;
        this.address = address;
        this.position = position;
        this.access = access;
        this.distance = distance;
        this.mapView = mapView;
        this.highlights = highlights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getHouseNumberType() {
        return houseNumberType;
    }

    public void setHouseNumberType(String houseNumberType) {
        this.houseNumberType = houseNumberType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Access> getAccess() {
        return access;
    }

    public void setAccess(List<Access> access) {
        this.access = access;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
