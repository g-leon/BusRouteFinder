package com.busroutefinder.api.services;

import com.busroutefinder.api.dataproviders.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class BusRouteFinderService {

    private final DataProvider dataProvider;
    private static Map<Integer, Set<Integer>> busStations;

    @Autowired
    public BusRouteFinderService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    /**
     * Returns true if there is a direct route between the two stations
     * given as parameters.
     *
     * @param departureStationId the id of the departure station.
     * @param arrivalStationId the id of the arrival station/
     * @return true|false if there is a direct route between given
     * stations.
     */
    public boolean hasDirectRoute(int departureStationId, int arrivalStationId) {
        if (busStations == null) {
            try {
                preprocessData();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return busStations.containsKey(departureStationId) ?
                busStations.get(departureStationId).contains(arrivalStationId) : false;
    }

    /**
     * Transforms the bus routes in a associations between
     * a bus stations and all the other station that follows
     * it.
     * This way clients can find out in constant time if
     * there is a direct route between two stations.
     *
     * @throws IOException if data cannot be read.
     */
    private void preprocessData() throws IOException {
        busStations = new HashMap<>();
        for (List<Integer> route : dataProvider.fetch()) {
            for (int startStation = 0; startStation < route.size() - 1; startStation++) {
                for (int stopStation = startStation + 1; stopStation < route.size(); stopStation++) {
                    if (busStations.get(route.get(startStation)) != null) {
                        busStations.get(route.get(startStation)).add(route.get(stopStation));
                    } else {
                        Set<Integer> stations = new HashSet<>();
                        stations.add(route.get(stopStation));
                        busStations.put(route.get(startStation), stations);
                    }
                }
            }
        }
    }
}
