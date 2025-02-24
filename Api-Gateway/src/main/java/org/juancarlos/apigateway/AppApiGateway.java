package org.juancarlos.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppApiGateway {

    public static void main(String[] args) {
        SpringApplication.run(AppApiGateway.class, args);
    }

}
