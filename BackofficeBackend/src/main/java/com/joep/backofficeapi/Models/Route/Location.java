
package com.joep.backofficeapi.Models.Route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String adminArea4;
    private String adminArea5Type;
    private String adminArea4Type;
    private String adminArea5;
    private String street;
    private String adminArea1;
    private String adminArea3;
    private String type;
    private Integer linkId;
    private String postalCode;
    private String sideOfStreet;
    private Boolean dragPoint;
    private String adminArea1Type;
    private String geocodeQuality;
    private String geocodeQualityCode;
    private String adminArea3Type;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param dragPoint
     * @param adminArea4
     * @param adminArea5
     * @param postalCode
     * @param adminArea1
     * @param adminArea3
     * @param type
     * @param sideOfStreet
     * @param geocodeQualityCode
     * @param adminArea4Type
     * @param linkId
     * @param street
     * @param adminArea5Type
     * @param geocodeQuality
     * @param adminArea1Type
     * @param adminArea3Type
     */
    public Location(String adminArea4, String adminArea5Type, String adminArea4Type, String adminArea5, String street, String adminArea1, String adminArea3, String type, Integer linkId, String postalCode, String sideOfStreet, Boolean dragPoint, String adminArea1Type, String geocodeQuality, String geocodeQualityCode, String adminArea3Type) {
        super();
        this.adminArea4 = adminArea4;
        this.adminArea5Type = adminArea5Type;
        this.adminArea4Type = adminArea4Type;
        this.adminArea5 = adminArea5;
        this.street = street;
        this.adminArea1 = adminArea1;
        this.adminArea3 = adminArea3;
        this.type = type;
        this.linkId = linkId;
        this.postalCode = postalCode;
        this.sideOfStreet = sideOfStreet;
        this.dragPoint = dragPoint;
        this.adminArea1Type = adminArea1Type;
        this.geocodeQuality = geocodeQuality;
        this.geocodeQualityCode = geocodeQualityCode;
        this.adminArea3Type = adminArea3Type;
    }


    public String getAdminArea4() {
        return adminArea4;
    }

    public void setAdminArea4(String adminArea4) {
        this.adminArea4 = adminArea4;
    }

    public String getAdminArea5Type() {
        return adminArea5Type;
    }

    public void setAdminArea5Type(String adminArea5Type) {
        this.adminArea5Type = adminArea5Type;
    }

    public String getAdminArea4Type() {
        return adminArea4Type;
    }

    public void setAdminArea4Type(String adminArea4Type) {
        this.adminArea4Type = adminArea4Type;
    }

    public String getAdminArea5() {
        return adminArea5;
    }

    public void setAdminArea5(String adminArea5) {
        this.adminArea5 = adminArea5;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdminArea1() {
        return adminArea1;
    }

    public void setAdminArea1(String adminArea1) {
        this.adminArea1 = adminArea1;
    }

    public String getAdminArea3() {
        return adminArea3;
    }

    public void setAdminArea3(String adminArea3) {
        this.adminArea3 = adminArea3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSideOfStreet() {
        return sideOfStreet;
    }

    public void setSideOfStreet(String sideOfStreet) {
        this.sideOfStreet = sideOfStreet;
    }

    public Boolean getDragPoint() {
        return dragPoint;
    }

    public void setDragPoint(Boolean dragPoint) {
        this.dragPoint = dragPoint;
    }

    public String getAdminArea1Type() {
        return adminArea1Type;
    }

    public void setAdminArea1Type(String adminArea1Type) {
        this.adminArea1Type = adminArea1Type;
    }

    public String getGeocodeQuality() {
        return geocodeQuality;
    }

    public void setGeocodeQuality(String geocodeQuality) {
        this.geocodeQuality = geocodeQuality;
    }

    public String getGeocodeQualityCode() {
        return geocodeQualityCode;
    }

    public void setGeocodeQualityCode(String geocodeQualityCode) {
        this.geocodeQualityCode = geocodeQualityCode;
    }

    public String getAdminArea3Type() {
        return adminArea3Type;
    }

    public void setAdminArea3Type(String adminArea3Type) {
        this.adminArea3Type = adminArea3Type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
