package com.busroutefinder.api.http.requests;

import org.springframework.stereotype.Component;


public class BusRouteFinderResponse {
    private int dep_sid;
    private int arr_sid;
    private boolean direct_bus_route;

    public BusRouteFinderResponse(int dep_sid, int arr_sid, boolean isDirect_bus_route) {
        super();
        this.dep_sid = dep_sid;
        this.arr_sid = arr_sid;
        this.direct_bus_route = isDirect_bus_route;
    }

    public int getDep_sid() {
        return dep_sid;
    }

    public int getArr_sid() {
        return arr_sid;
    }

    public boolean isDirect_bus_route() {
        return direct_bus_route;
    }

    public void setDep_sid(int dep_sid) {
        this.dep_sid = dep_sid;
    }

    public void setArr_sid(int arrivalStationId) {
        this.arr_sid = arrivalStationId;
    }

    public void setDirect_bus_route(boolean isDirect_bus_route) {
        this.direct_bus_route = isDirect_bus_route;
    }
}

