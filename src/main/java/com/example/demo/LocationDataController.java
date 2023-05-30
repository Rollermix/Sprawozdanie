package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class LocationDataController {

    private final LocationDataService locationDataService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) throws IOException {
        String ipAddress = locationDataService.getIpAddress();
        String serverPort = String.valueOf(request.getServerPort());
        LogManager logManager = LogManager.getLogManager();
        Logger log = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.info("Ip address: " + ipAddress);
        log.info("Server port: " + serverPort);


        LocationData locationData = locationDataService.getLocationInfo(ipAddress);
        String timeZone = locationDataService.getTimezone(locationData);

        String formattedDateTime = locationDataService.getDateInTimezone(timeZone);


        return formattedDateTime + "\n ip: " +ipAddress;
    }

}
