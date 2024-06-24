package br.com.projeto.politicalmanagement.integration.feign.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleMaps", url = "https://maps.googleapis.com")
public interface GoogleMapsClient {

    @GetMapping("/maps/api/geocode/json")
    GeocodeResponse geocode(@RequestParam("address") String address, @RequestParam("key") String key);

}