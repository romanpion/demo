package com.romao.demo.network.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {
    @JsonProperty("Id")
    public int id;

    @JsonProperty("Name")
    public String name;
}
