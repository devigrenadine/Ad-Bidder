package com.bidder.mx.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bidder.mx.model.Campaign;

import lombok.extern.slf4j.Slf4j;

import com.bidder.mx.api.CampaignAPI;
import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.exception.GetCampaignsException;

@Service
@Slf4j
public class CampaignServiceImpl implements CampaignService {

	private CampaignAPI campaignAPI;
	private static final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

	public CampaignServiceImpl(CampaignAPI campaignAPI) {
		this.campaignAPI = campaignAPI;
	}

	@Override
	public List<Campaign> getAllCampaigns() {
		try {
			return campaignAPI.getAllCampaigns();
		} catch (GetCampaignsException e) {
			
			log.error(String.format("An error occurred while getAllCampaigns through campaignAPI"), e);
			//catch-throw runtime exceptions as a best practice
			throw e;
		}
	}

	@Override
	public List<Campaign> filterCampaigns(BidRequest bidRequest, List<Campaign> campaignList) {
		return campaignList.stream().parallel().filter(
				campaign -> campaign.getTargetedCountries().contains(bidRequest.getDevice().getGeo().getCountry()))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Campaign> getCampaignWithsHighestPrice(List<Campaign> matchingCampaignList) {
		return matchingCampaignList.stream().max(Comparator.comparing(Campaign::getPrice));
	}

}
