package com.bidder.mx.dto;

import com.bidder.mx.model.App;
import com.bidder.mx.model.Device;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BidRequest {

	private String id;
	private App app;
	private Device device;

}
