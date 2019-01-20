package com.bidder.mx.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Bid {

	private String campaignId;

	private String adm;
	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Bid() {
	}

	public Bid(String campaignId, BigDecimal price, String adm) {
		this.campaignId = campaignId;
		this.price = price;
		this.adm = adm;
	}

}