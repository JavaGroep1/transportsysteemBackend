package com.joep.backofficeapi.Models.Route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Route {
    private Route_ route;

    public Route(){

    }
    /**
     * @param route
     */
    public Route(Route_ route) {
        super();
        this.route = route;
    }

    public Route_ getRoute() {
        return route;
    }

    public void setRoute(Route_ route) {
        this.route = route;
    }
}
