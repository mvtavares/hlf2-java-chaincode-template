package br.com.tdc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {
	
    private String assetID;

    private String color;

    private Integer size;

    private String owner;

    private Integer appraisedValue;
	
}


