package br.com.tdc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateAsset {

    private final String color;

    private final int size;

    private final String owner;

    private final int appraisedValue;
	
}


