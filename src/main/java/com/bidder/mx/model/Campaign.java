package com.bidder.mx.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Campaign {

	private String campaignId;
	private BigDecimal price;
	private String adm;
	private List<String> targetedCountries = new ArrayList<>();
}
