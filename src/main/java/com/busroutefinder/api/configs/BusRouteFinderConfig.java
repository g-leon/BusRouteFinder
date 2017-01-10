package com.busroutefinder.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.boot.ApplicationArguments;


@Configuration
public class BusRouteFinderConfig {

    @Bean
    public Path getPath(ApplicationArguments args) throws IOException {
        String[] argsArray = args.getSourceArgs();
        return Paths.get(argsArray[0]);
    }
}
