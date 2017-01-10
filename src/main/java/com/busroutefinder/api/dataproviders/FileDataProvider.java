package com.busroutefinder.api.dataproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Service
public class FileDataProvider implements DataProvider {
    private final Path filePath;

    @Autowired
    public FileDataProvider(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads input from file and transforms it in a list of
     * list, skipping the first one.
     *
     * @return a list of lists representing bus routes.
     * @throws IOException if the input file cannot be read.
     */
    @Override
    public List<List<Integer>> fetch() throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            final ArrayList<List<Integer>> busStations = lines
                    .skip(1)
                    .map(this::buildBusRoute)
                    .collect(toCollection(ArrayList::new));
            return busStations;
        }
    }

    /**
     * Transforms each bus route represented as string
     * in a list of integers, skipping the first value
     * that represents the id of the route.
     *
     * @param line a bus route represented as string.
     * @return a list of integers representing ids
     *         of stations.
     */
    private List<Integer> buildBusRoute(String line) {
        final String[] busStations = line.split(" ");
        final List<Integer> busStationsList = Stream.of(busStations)
                .skip(1)
                .map(Integer::valueOf)
                .collect(toList());
        return busStationsList;
    }
}
