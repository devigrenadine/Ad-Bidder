package com.bidder.mx.dto;

import com.bidder.mx.model.Bid;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BidResponse {

	private String id;
	private Bid bid;

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public BidResponse(String id, Bid bid) {
		this.id = id;
		this.bid = bid;
	}

	
}
