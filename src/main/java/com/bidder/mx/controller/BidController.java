package com.bidder.mx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.dto.BidResponse;
import com.bidder.mx.helper.BidResponseConverter;
import com.bidder.mx.model.Campaign;
import com.bidder.mx.service.CampaignService;

import lombok.Data;

@RestController
public class BidController {

	private CampaignService campaignService;
	private BidResponseConverter bidResponseConverter;

	public BidController(CampaignService campaignService, BidResponseConverter bidResponseConverter) {
		this.campaignService = campaignService;
		this.bidResponseConverter = bidResponseConverter;
	}

	@PostMapping(value = "/bid")
	public ResponseEntity<BidResponse> submitBid(@RequestBody BidRequest bidRequest) {

		// Get all
		List<Campaign> campaignList = campaignService.getAllCampaigns();

		// Filter
		List<Campaign> matchingCampaignList = campaignService.filterCampaigns(bidRequest, campaignList);

		if (matchingCampaignList.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		// Pick highest bid campaign
		Optional<Campaign> highestPriceCampaign = campaignService.getCampaignWithsHighestPrice(matchingCampaignList);

        return highestPriceCampaign.map(campaign -> new ResponseEntity<>(
                                        bidResponseConverter.convertCampaignToBidResponse(bidRequest, campaign),
                                        HttpStatus.OK))
                                   .orElseGet(() -> new ResponseEntity<>(
                                        HttpStatus.NO_CONTENT));
	}
}
