package com.joep.backofficeapi.Models.Route;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route_ {

    private Boolean hasTollRoad;
    private List<Object> computedWaypoints = null;
    private Double fuelUsed;
    private Boolean hasUnpaved;
    private Boolean hasHighway;
    private Integer realTime;
    private Double distance;
    private Integer time;
    private List<Integer> locationSequence = null;
    private Boolean hasSeasonalClosure;
    private String sessionId;
    private List<Location> locations = null;
    private Boolean hasCountryCross;
    private List<Leg> legs;
    private String formattedTime;
    private Options options;
    private Boolean hasFerry;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Route_() {
    }

    /**
     * 
     * @param hasTollRoad
     * @param distance
     * @param hasHighway
     * @param computedWaypoints
     * @param formattedTime
     * @param sessionId
     * @param realTime
     * @param hasSeasonalClosure
     * @param hasCountryCross
     * @param fuelUsed
     * @param legs
     * @param options
     * @param locations
     * @param hasUnpaved
     * @param time
     * @param locationSequence
     * @param hasFerry
     */
    public Route_(Boolean hasTollRoad, List<Object> computedWaypoints, Double fuelUsed, Boolean hasUnpaved, Boolean hasHighway, Integer realTime, Double distance, Integer time, List<Integer> locationSequence, Boolean hasSeasonalClosure, String sessionId, List<Location> locations, Boolean hasCountryCross, List<Leg> legs, String formattedTime, Options options, Boolean hasFerry) {
        super();
        this.hasTollRoad = hasTollRoad;
        this.computedWaypoints = computedWaypoints;
        this.fuelUsed = fuelUsed;
        this.hasUnpaved = hasUnpaved;
        this.hasHighway = hasHighway;
        this.realTime = realTime;
        this.distance = distance;
        this.time = time;
        this.locationSequence = locationSequence;
        this.hasSeasonalClosure = hasSeasonalClosure;
        this.sessionId = sessionId;
        this.locations = locations;
        this.hasCountryCross = hasCountryCross;
        this.legs = legs;
        this.formattedTime = formattedTime;
        this.options = options;
        this.hasFerry = hasFerry;
    }

    public Boolean getHasTollRoad() {
        return hasTollRoad;
    }

    public void setHasTollRoad(Boolean hasTollRoad) {
        this.hasTollRoad = hasTollRoad;
    }

    public List<Object> getComputedWaypoints() {
        return computedWaypoints;
    }

    public void setComputedWaypoints(List<Object> computedWaypoints) {
        this.computedWaypoints = computedWaypoints;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public Boolean getHasUnpaved() {
        return hasUnpaved;
    }

    public void setHasUnpaved(Boolean hasUnpaved) {
        this.hasUnpaved = hasUnpaved;
    }

    public Boolean getHasHighway() {
        return hasHighway;
    }

    public void setHasHighway(Boolean hasHighway) {
        this.hasHighway = hasHighway;
    }

    public Integer getRealTime() {
        return realTime;
    }

    public void setRealTime(Integer realTime) {
        this.realTime = realTime;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Integer> getLocationSequence() {
        return locationSequence;
    }

    public void setLocationSequence(List<Integer> locationSequence) {
        this.locationSequence = locationSequence;
    }

    public Boolean getHasSeasonalClosure() {
        return hasSeasonalClosure;
    }

    public void setHasSeasonalClosure(Boolean hasSeasonalClosure) {
        this.hasSeasonalClosure = hasSeasonalClosure;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Boolean getHasCountryCross() {
        return hasCountryCross;
    }

    public void setHasCountryCross(Boolean hasCountryCross) {
        this.hasCountryCross = hasCountryCross;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Boolean getHasFerry() {
        return hasFerry;
    }

    public void setHasFerry(Boolean hasFerry) {
        this.hasFerry = hasFerry;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
