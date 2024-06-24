package br.com.projeto.politicalmanagement.integration.feign.google;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GeocodeResponse{
    private List<Result> results;

    @Setter
    @Getter
    public static class Result {
        private Geometry geometry;

    }

    @Setter
    @Getter
    public static class Geometry {
        private Location location;

    }

    @Setter
    @Getter
    public static class Location {
        private String lat;
        private String lng;

    }
}