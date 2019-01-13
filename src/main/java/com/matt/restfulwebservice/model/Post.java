package com.matt.restfulwebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    @JsonProperty
    private int userId;

    @JsonProperty
    private String title;

    @JsonProperty
    private String body;

    @JsonIgnore
    @JsonProperty
    private int id;
}
