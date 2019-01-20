package com.bidder.mx.helper;

import org.springframework.stereotype.Component;

import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.dto.BidResponse;
import com.bidder.mx.model.Bid;
import com.bidder.mx.model.Campaign;

@Component
public class BidResponseConverter {
	public BidResponse convertCampaignToBidResponse(BidRequest bidRequest, Campaign campaign) {
		Bid bid = new Bid(campaign.getCampaignId(), campaign.getPrice(), campaign.getAdm());

		return new BidResponse(bidRequest.getId(), bid);
	}
}
