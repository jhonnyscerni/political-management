package br.com.projeto.politicalmanagement.integration.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleDTO {

    private List<GoogleResultDTO> results;
}
