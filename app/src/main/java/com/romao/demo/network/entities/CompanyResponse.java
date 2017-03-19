package com.romao.demo.network.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyResponse {

    @JsonProperty("IsPaymentVerified")
    public Boolean isPaymentVerified;

    @JsonProperty("ImageUrl")
    public String imageUrl;

    @JsonProperty("Raiting")
    public int rating;

    @JsonProperty("Founded")
    public String founded;

    @JsonProperty("Location")
    public LocationResponse location;

    @JsonProperty("Industry")
    public String industry;

    @JsonProperty("Name")
    public String name;

    @JsonProperty("IsValidated")
    public boolean isValidated;

    @JsonProperty("Id")
    public int id;

    @JsonProperty("Description")
    public String description;

    @JsonProperty("Cin")
    public String cin;


}
