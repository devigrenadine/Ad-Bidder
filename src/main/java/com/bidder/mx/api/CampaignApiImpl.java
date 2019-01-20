package com.bidder.mx.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bidder.mx.model.Campaign;

@Component
public class CampaignApiImpl implements CampaignAPI {

	@Value("${campaign.service.url}")
	private String campaignServiceUrl;

	@Override
	public List<Campaign> getAllCampaigns() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(campaignServiceUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(uriBuilder.toUriString(),
									 HttpMethod.GET, entity,
									 new ParameterizedTypeReference<List<Campaign>>(){}).getBody();
	}
}
