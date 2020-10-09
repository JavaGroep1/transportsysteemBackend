
package com.joep.backofficeapi.Models.Route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Options {

    private List<Object> mustAvoidLinkIds = null;
    private Integer drivingStyle;
    private Boolean countryBoundaryDisplay;
    private Integer generalize;
    private String narrativeType;
    private String locale;
    private Boolean avoidTimedConditions;
    private Boolean destinationManeuverDisplay;
    private Boolean enhancedNarrative;
    private Integer filterZoneFactor;
    private Integer timeType;
    private Integer maxWalkingDistance;
    private String routeType;
    private Integer transferPenalty;
    private Boolean stateBoundaryDisplay;
    private Integer walkingSpeed;
    private Integer maxLinkId;
    private List<Object> arteryWeights = null;
    private List<Object> tryAvoidLinkIds = null;
    private String unit;
    private Integer routeNumber;
    private String shapeFormat;
    private Integer maneuverPenalty;
    private Boolean useTraffic;
    private Boolean returnLinkDirections;
    private List<Object> avoidTripIds = null;
    private String manmaps;
    private Integer highwayEfficiency;
    private Boolean sideOfStreetDisplay;
    private Integer cyclingRoadFactor;
    private Integer urbanAvoidFactor;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Options() {
    }

    /**
     * 
     * @param arteryWeights
     * @param cyclingRoadFactor
     * @param timeType
     * @param useTraffic
     * @param returnLinkDirections
     * @param countryBoundaryDisplay
     * @param locale
     * @param enhancedNarrative
     * @param tryAvoidLinkIds
     * @param drivingStyle
     * @param generalize
     * @param mustAvoidLinkIds
     * @param routeType
     * @param sideOfStreetDisplay
     * @param avoidTimedConditions
     * @param routeNumber
     * @param maxWalkingDistance
     * @param shapeFormat
     * @param destinationManeuverDisplay
     * @param transferPenalty
     * @param narrativeType
     * @param walkingSpeed
     * @param urbanAvoidFactor
     * @param stateBoundaryDisplay
     * @param unit
     * @param highwayEfficiency
     * @param maxLinkId
     * @param maneuverPenalty
     * @param avoidTripIds
     * @param filterZoneFactor
     * @param manmaps
     */
    public Options(List<Object> mustAvoidLinkIds, Integer drivingStyle, Boolean countryBoundaryDisplay, Integer generalize, String narrativeType, String locale, Boolean avoidTimedConditions, Boolean destinationManeuverDisplay, Boolean enhancedNarrative, Integer filterZoneFactor, Integer timeType, Integer maxWalkingDistance, String routeType, Integer transferPenalty, Boolean stateBoundaryDisplay, Integer walkingSpeed, Integer maxLinkId, List<Object> arteryWeights, List<Object> tryAvoidLinkIds, String unit, Integer routeNumber, String shapeFormat, Integer maneuverPenalty, Boolean useTraffic, Boolean returnLinkDirections, List<Object> avoidTripIds, String manmaps, Integer highwayEfficiency, Boolean sideOfStreetDisplay, Integer cyclingRoadFactor, Integer urbanAvoidFactor) {
        super();
        this.mustAvoidLinkIds = mustAvoidLinkIds;
        this.drivingStyle = drivingStyle;
        this.countryBoundaryDisplay = countryBoundaryDisplay;
        this.generalize = generalize;
        this.narrativeType = narrativeType;
        this.locale = locale;
        this.avoidTimedConditions = avoidTimedConditions;
        this.destinationManeuverDisplay = destinationManeuverDisplay;
        this.enhancedNarrative = enhancedNarrative;
        this.filterZoneFactor = filterZoneFactor;
        this.timeType = timeType;
        this.maxWalkingDistance = maxWalkingDistance;
        this.routeType = routeType;
        this.transferPenalty = transferPenalty;
        this.stateBoundaryDisplay = stateBoundaryDisplay;
        this.walkingSpeed = walkingSpeed;
        this.maxLinkId = maxLinkId;
        this.arteryWeights = arteryWeights;
        this.tryAvoidLinkIds = tryAvoidLinkIds;
        this.unit = unit;
        this.routeNumber = routeNumber;
        this.shapeFormat = shapeFormat;
        this.maneuverPenalty = maneuverPenalty;
        this.useTraffic = useTraffic;
        this.returnLinkDirections = returnLinkDirections;
        this.avoidTripIds = avoidTripIds;
        this.manmaps = manmaps;
        this.highwayEfficiency = highwayEfficiency;
        this.sideOfStreetDisplay = sideOfStreetDisplay;
        this.cyclingRoadFactor = cyclingRoadFactor;
        this.urbanAvoidFactor = urbanAvoidFactor;
    }

    public List<Object> getMustAvoidLinkIds() {
        return mustAvoidLinkIds;
    }

    public void setMustAvoidLinkIds(List<Object> mustAvoidLinkIds) {
        this.mustAvoidLinkIds = mustAvoidLinkIds;
    }

    public Integer getDrivingStyle() {
        return drivingStyle;
    }

    public void setDrivingStyle(Integer drivingStyle) {
        this.drivingStyle = drivingStyle;
    }

    public Boolean getCountryBoundaryDisplay() {
        return countryBoundaryDisplay;
    }

    public void setCountryBoundaryDisplay(Boolean countryBoundaryDisplay) {
        this.countryBoundaryDisplay = countryBoundaryDisplay;
    }

    public Integer getGeneralize() {
        return generalize;
    }

    public void setGeneralize(Integer generalize) {
        this.generalize = generalize;
    }

    public String getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(String narrativeType) {
        this.narrativeType = narrativeType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getAvoidTimedConditions() {
        return avoidTimedConditions;
    }

    public void setAvoidTimedConditions(Boolean avoidTimedConditions) {
        this.avoidTimedConditions = avoidTimedConditions;
    }

    public Boolean getDestinationManeuverDisplay() {
        return destinationManeuverDisplay;
    }

    public void setDestinationManeuverDisplay(Boolean destinationManeuverDisplay) {
        this.destinationManeuverDisplay = destinationManeuverDisplay;
    }

    public Boolean getEnhancedNarrative() {
        return enhancedNarrative;
    }

    public void setEnhancedNarrative(Boolean enhancedNarrative) {
        this.enhancedNarrative = enhancedNarrative;
    }

    public Integer getFilterZoneFactor() {
        return filterZoneFactor;
    }

    public void setFilterZoneFactor(Integer filterZoneFactor) {
        this.filterZoneFactor = filterZoneFactor;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getMaxWalkingDistance() {
        return maxWalkingDistance;
    }

    public void setMaxWalkingDistance(Integer maxWalkingDistance) {
        this.maxWalkingDistance = maxWalkingDistance;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public Integer getTransferPenalty() {
        return transferPenalty;
    }

    public void setTransferPenalty(Integer transferPenalty) {
        this.transferPenalty = transferPenalty;
    }

    public Boolean getStateBoundaryDisplay() {
        return stateBoundaryDisplay;
    }

    public void setStateBoundaryDisplay(Boolean stateBoundaryDisplay) {
        this.stateBoundaryDisplay = stateBoundaryDisplay;
    }

    public Integer getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(Integer walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    public Integer getMaxLinkId() {
        return maxLinkId;
    }

    public void setMaxLinkId(Integer maxLinkId) {
        this.maxLinkId = maxLinkId;
    }

    public List<Object> getArteryWeights() {
        return arteryWeights;
    }

    public void setArteryWeights(List<Object> arteryWeights) {
        this.arteryWeights = arteryWeights;
    }

    public List<Object> getTryAvoidLinkIds() {
        return tryAvoidLinkIds;
    }

    public void setTryAvoidLinkIds(List<Object> tryAvoidLinkIds) {
        this.tryAvoidLinkIds = tryAvoidLinkIds;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getShapeFormat() {
        return shapeFormat;
    }

    public void setShapeFormat(String shapeFormat) {
        this.shapeFormat = shapeFormat;
    }

    public Integer getManeuverPenalty() {
        return maneuverPenalty;
    }

    public void setManeuverPenalty(Integer maneuverPenalty) {
        this.maneuverPenalty = maneuverPenalty;
    }

    public Boolean getUseTraffic() {
        return useTraffic;
    }

    public void setUseTraffic(Boolean useTraffic) {
        this.useTraffic = useTraffic;
    }

    public Boolean getReturnLinkDirections() {
        return returnLinkDirections;
    }

    public void setReturnLinkDirections(Boolean returnLinkDirections) {
        this.returnLinkDirections = returnLinkDirections;
    }

    public List<Object> getAvoidTripIds() {
        return avoidTripIds;
    }

    public void setAvoidTripIds(List<Object> avoidTripIds) {
        this.avoidTripIds = avoidTripIds;
    }

    public String getManmaps() {
        return manmaps;
    }

    public void setManmaps(String manmaps) {
        this.manmaps = manmaps;
    }

    public Integer getHighwayEfficiency() {
        return highwayEfficiency;
    }

    public void setHighwayEfficiency(Integer highwayEfficiency) {
        this.highwayEfficiency = highwayEfficiency;
    }

    public Boolean getSideOfStreetDisplay() {
        return sideOfStreetDisplay;
    }

    public void setSideOfStreetDisplay(Boolean sideOfStreetDisplay) {
        this.sideOfStreetDisplay = sideOfStreetDisplay;
    }

    public Integer getCyclingRoadFactor() {
        return cyclingRoadFactor;
    }

    public void setCyclingRoadFactor(Integer cyclingRoadFactor) {
        this.cyclingRoadFactor = cyclingRoadFactor;
    }

    public Integer getUrbanAvoidFactor() {
        return urbanAvoidFactor;
    }

    public void setUrbanAvoidFactor(Integer urbanAvoidFactor) {
        this.urbanAvoidFactor = urbanAvoidFactor;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
