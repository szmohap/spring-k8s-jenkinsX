package com.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/k8/helm")
@Slf4j
public class SpringTestController {

    private final HealthEndpoint healthEndpoint;

    public SpringTestController(HealthEndpoint healthEndpoint) {
        log.info("SpringTestController() {}", System.currentTimeMillis());
        this.healthEndpoint = healthEndpoint;
    }

    /*
    @Value("file:/etc/secrets/DB_USERNAME")
    String secretVolumeDbUserName;//form volume mount



    @Value("${DB_USERNAME}")
    private String secretDbUsername;// from secret

    @Value("${APP_WELCOME_MESSAGE}")
    private String configMapValue;// from configmap
*/
    @GetMapping("/test")
    public String getValue(@RequestParam(name = "value",defaultValue = "Hello",required = false) String value) throws IOException {
        //String configMapValue = System.getProperty("APP_WELCOME_MESSAGE");
      //  String secretDbUsername = System.getProperty("DB_USERNAME");
        //String secretVolumeApiKey= Files.readString(Paths.get("/etc/secrets/API_KEY")).trim();

        log.info("{} Received request, param value={}"
                ,Thread.currentThread()
                ,value
        );
        return "k8-helm-"+value;
    }


    @GetMapping(value = "/healthcheck")
    public ResponseEntity<HealthComponent> healthcheck(){

        HealthComponent health = healthEndpoint.health();
        HttpStatus status = Status.UP.equals(health.getStatus()) ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(health,status);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.ok("Exception Handler:"+e.getMessage());
    }
}
