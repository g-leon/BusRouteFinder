package com.busroutefinder.api.controllers;

import com.busroutefinder.api.http.requests.BusRouteFinderResponse;
import com.busroutefinder.api.services.BusRouteFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BusRouteFinderController {
    private final BusRouteFinderService busRouteFinderService;

    @Autowired
    public BusRouteFinderController(BusRouteFinderService busRouteFinderService) {
        this.busRouteFinderService = busRouteFinderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/direct")
    public BusRouteFinderResponse directRoute(@RequestParam(value = "dep_sid") int departureStationId,
                                              @RequestParam(value = "arr_sid") int arrivalStationId) {
        boolean isDirectRoute = busRouteFinderService.hasDirectRoute(departureStationId, arrivalStationId);
        return new BusRouteFinderResponse(departureStationId, arrivalStationId, isDirectRoute);
    }
}
