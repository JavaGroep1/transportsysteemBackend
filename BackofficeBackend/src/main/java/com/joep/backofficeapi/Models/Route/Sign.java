
package com.joep.backofficeapi.Models.Route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Sign {

    private String text;
    private String extraText;
    private Integer direction;
    private Integer type;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sign() {
    }

    /**
     * 
     * @param text
     * @param extraText
     * @param type
     * @param url
     * @param direction
     */
    public Sign(String text, String extraText, Integer direction, Integer type, String url) {
        super();
        this.text = text;
        this.extraText = extraText;
        this.direction = direction;
        this.type = type;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
