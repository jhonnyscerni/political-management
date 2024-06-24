package br.com.projeto.politicalmanagement.api.response;

import lombok.Data;

@Data
public class AddressResponse {

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String nameCity;

    private String state;

    private String lat;

    private String lng;
}
