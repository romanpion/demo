package com.romao.demo.network.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SortDescriptorRequest {

    @JsonProperty("FieldName")
    public String fieldName;

    @JsonProperty("Ascending")
    public boolean ascending;
}
