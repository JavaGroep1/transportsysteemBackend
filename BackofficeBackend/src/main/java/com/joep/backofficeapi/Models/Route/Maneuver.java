
package com.joep.backofficeapi.Models.Route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Maneuver {

    private List<Sign> signs = null;
    private Integer index;
    private List<Object> maneuverNotes = null;
    private Integer direction;
    private String narrative;
    private String iconUrl;
    private Integer distance;
    private Integer time;
    private List<Object> linkIds = null;
    private List<String> streets = null;
    private Integer attributes;
    private String transportMode;
    private String formattedTime;
    private String directionName;
    private startPoint startPoint;
    private String mapUrl;
    private Integer turnType;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Maneuver() {
    }

    /**
     * 
     * @param distance
     * @param streets
     * @param narrative
     * @param turnType
     * @param index
     * @param formattedTime
     * @param directionName
     * @param maneuverNotes
     * @param signs
     * @param linkIds
     * @param mapUrl
     * @param transportMode
     * @param attributes
     * @param iconUrl
     * @param time
     * @param direction
     */
    public Maneuver(List<Sign> signs, Integer index, List<Object> maneuverNotes, Integer direction, String narrative, String iconUrl, Integer distance, Integer time, List<Object> linkIds, List<String> streets, Integer attributes, String transportMode, String formattedTime, String directionName, String mapUrl, Integer turnType) {
        super();
        this.signs = signs;
        this.index = index;
        this.maneuverNotes = maneuverNotes;
        this.direction = direction;
        this.narrative = narrative;
        this.iconUrl = iconUrl;
        this.distance = distance;
        this.time = time;
        this.linkIds = linkIds;
        this.streets = streets;
        this.attributes = attributes;
        this.transportMode = transportMode;
        this.formattedTime = formattedTime;
        this.directionName = directionName;
        this.mapUrl = mapUrl;
        this.turnType = turnType;
    }

    public com.joep.backofficeapi.Models.Route.startPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(com.joep.backofficeapi.Models.Route.startPoint startPoint) {
        this.startPoint = startPoint;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Object> getManeuverNotes() {
        return maneuverNotes;
    }

    public void setManeuverNotes(List<Object> maneuverNotes) {
        this.maneuverNotes = maneuverNotes;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Object> getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(List<Object> linkIds) {
        this.linkIds = linkIds;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(List<String> streets) {
        this.streets = streets;
    }

    public Integer getAttributes() {
        return attributes;
    }

    public void setAttributes(Integer attributes) {
        this.attributes = attributes;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }


    public Integer getTurnType() {
        return turnType;
    }

    public void setTurnType(Integer turnType) {
        this.turnType = turnType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
