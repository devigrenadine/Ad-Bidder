package com.bidder.mx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.model.Campaign;

public interface CampaignService {

	List<Campaign> getAllCampaigns();

	List<Campaign> filterCampaigns(BidRequest bidRequest, List<Campaign> campaignList);

	Optional<Campaign> getCampaignWithsHighestPrice(List<Campaign> matchingCampaignList);
}
