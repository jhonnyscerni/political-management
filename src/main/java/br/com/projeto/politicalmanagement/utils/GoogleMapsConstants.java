package br.com.projeto.politicalmanagement.utils;

import java.util.ResourceBundle;

public final class GoogleMapsConstants {

    private static final ResourceBundle rb = ResourceBundle.getBundle("config");

    public static final String API_KEY = rb.getString("google.api.key");

}