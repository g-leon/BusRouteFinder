package com.busroutefinder.api.services;

import com.busroutefinder.api.dataproviders.DataProvider;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBusRouteFinderService {

    @Test
    public void TestHasDirectRoute() throws IOException {
        List<Integer> busRoute1 = new ArrayList<>();
        busRoute1.add(1);
        busRoute1.add(3);
        busRoute1.add(4);
        List<Integer> busRoute2 = new ArrayList<>();
        busRoute2.add(1);
        busRoute2.add(6);
        busRoute2.add(5);

        DataProvider dataProvider = mock(DataProvider.class);
        when(dataProvider.fetch()).thenReturn(asList(busRoute1, busRoute2));
        BusRouteFinderService busRoutes = new BusRouteFinderService(dataProvider);

        // Existing route
        boolean hasDirectConnection = busRoutes.hasDirectRoute(1, 6);
        assert(hasDirectConnection == true);

        // Non-existing route
        hasDirectConnection = busRoutes.hasDirectRoute(3, 5);
        assert(hasDirectConnection == false);
    }
}
