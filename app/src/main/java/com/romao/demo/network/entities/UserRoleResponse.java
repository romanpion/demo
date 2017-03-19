package com.romao.demo.network.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleResponse implements Serializable {

    @JsonProperty("Name")
    public String name;

    @JsonProperty("ShortName")
    public String shortName;
}
