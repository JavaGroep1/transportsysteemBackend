
package com.joep.backofficeapi.Models.Route;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

    private Boolean hasTollRoad;
    private Integer index;
    private List<List<Object>> roadGradeStrategy = null;
    private Boolean hasHighway;
    private Boolean hasUnpaved;
    private Double distance;
    private Integer time;
    private Integer origIndex;
    private Boolean hasSeasonalClosure;
    private String origNarrative;
    private Boolean hasCountryCross;
    private String formattedTime;
    private String destNarrative;
    private Integer destIndex;
    private List<Maneuver> maneuvers;
    private Boolean hasFerry;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leg() {
    }

    /**
     * 
     * @param hasTollRoad
     * @param destNarrative
     * @param distance
     * @param hasHighway
     * @param index
     * @param origIndex
     * @param formattedTime
     * @param hasSeasonalClosure
     * @param hasCountryCross
     * @param roadGradeStrategy
     * @param destIndex
     * @param hasUnpaved
     * @param time
     * @param origNarrative
     * @param maneuvers
     * @param hasFerry
     */
    public Leg(Boolean hasTollRoad, Integer index, List<List<Object>> roadGradeStrategy, Boolean hasHighway, Boolean hasUnpaved, Double distance, Integer time, Integer origIndex, Boolean hasSeasonalClosure, String origNarrative, Boolean hasCountryCross, String formattedTime, String destNarrative, Integer destIndex, List<Maneuver> maneuvers, Boolean hasFerry) {
        super();
        this.hasTollRoad = hasTollRoad;
        this.index = index;
        this.roadGradeStrategy = roadGradeStrategy;
        this.hasHighway = hasHighway;
        this.hasUnpaved = hasUnpaved;
        this.distance = distance;
        this.time = time;
        this.origIndex = origIndex;
        this.hasSeasonalClosure = hasSeasonalClosure;
        this.origNarrative = origNarrative;
        this.hasCountryCross = hasCountryCross;
        this.formattedTime = formattedTime;
        this.destNarrative = destNarrative;
        this.destIndex = destIndex;
        this.maneuvers = maneuvers;
        this.hasFerry = hasFerry;
    }

    public Boolean getHasTollRoad() {
        return hasTollRoad;
    }

    public void setHasTollRoad(Boolean hasTollRoad) {
        this.hasTollRoad = hasTollRoad;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<List<Object>> getRoadGradeStrategy() {
        return roadGradeStrategy;
    }

    public void setRoadGradeStrategy(List<List<Object>> roadGradeStrategy) {
        this.roadGradeStrategy = roadGradeStrategy;
    }

    public Boolean getHasHighway() {
        return hasHighway;
    }

    public void setHasHighway(Boolean hasHighway) {
        this.hasHighway = hasHighway;
    }

    public Boolean getHasUnpaved() {
        return hasUnpaved;
    }

    public void setHasUnpaved(Boolean hasUnpaved) {
        this.hasUnpaved = hasUnpaved;
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

    public Integer getOrigIndex() {
        return origIndex;
    }

    public void setOrigIndex(Integer origIndex) {
        this.origIndex = origIndex;
    }

    public Boolean getHasSeasonalClosure() {
        return hasSeasonalClosure;
    }

    public void setHasSeasonalClosure(Boolean hasSeasonalClosure) {
        this.hasSeasonalClosure = hasSeasonalClosure;
    }

    public String getOrigNarrative() {
        return origNarrative;
    }

    public void setOrigNarrative(String origNarrative) {
        this.origNarrative = origNarrative;
    }

    public Boolean getHasCountryCross() {
        return hasCountryCross;
    }

    public void setHasCountryCross(Boolean hasCountryCross) {
        this.hasCountryCross = hasCountryCross;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getDestNarrative() {
        return destNarrative;
    }

    public void setDestNarrative(String destNarrative) {
        this.destNarrative = destNarrative;
    }

    public Integer getDestIndex() {
        return destIndex;
    }

    public void setDestIndex(Integer destIndex) {
        this.destIndex = destIndex;
    }

    public List<Maneuver> getManeuvers() {
        return maneuvers;
    }

    public void setManeuvers(List<Maneuver> maneuvers) {
        this.maneuvers = maneuvers;
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
