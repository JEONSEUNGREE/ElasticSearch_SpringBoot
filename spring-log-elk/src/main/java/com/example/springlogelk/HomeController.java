package com.example.springlogelk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String HomePage() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Welcome home Page" + now);
        return "Welcome SpringBoot";
    }

    @GetMapping("/logs")
    public String LogsPage() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Welcome home Page" + now);
        return "Welcome SpringBoot";
    }


    @GetMapping("/warn")
    public String WarnPage() {
        LocalDateTime now = LocalDateTime.now();
        log.warn("Welcome home Page" + now);
        return "Welcome SpringBoot";
    }

    @GetMapping("/debug")
    public String DebugPage() {
        LocalDateTime now = LocalDateTime.now();
        log.debug("Welcome home Page" + now);
        return "Welcome SpringBoot";
    }


}
