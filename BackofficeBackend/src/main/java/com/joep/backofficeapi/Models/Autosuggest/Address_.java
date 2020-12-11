
package com.joep.backofficeapi.Models.Autosuggest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address_ {

    private List<Label> label = null;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Address_() {
    }

    /**
     * 
     * @param label
     */
    public Address_(List<Label> label) {
        super();
        this.label = label;
    }

    public List<Label> getLabel() {
        return label;
    }

    public void setLabel(List<Label> label) {
        this.label = label;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
