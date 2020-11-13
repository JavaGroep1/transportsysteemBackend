
package com.joep.backofficeapi.Models.Autosuggest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Example {

    private List<Item> items = null;
    private List<Object> queryTerms = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Example() {
    }

    /**
     * 
     * @param queryTerms
     * @param items
     */
    public Example(List<Item> items, List<Object> queryTerms) {
        super();
        this.items = items;
        this.queryTerms = queryTerms;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Object> getQueryTerms() {
        return queryTerms;
    }

    public void setQueryTerms(List<Object> queryTerms) {
        this.queryTerms = queryTerms;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
