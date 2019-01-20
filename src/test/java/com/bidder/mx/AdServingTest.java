package com.bidder.mx;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bidder.mx.controller.BidController;
import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.dto.BidResponse;
import com.bidder.mx.helper.BidResponseConverter;
import com.bidder.mx.model.Campaign;
import com.bidder.mx.service.CampaignService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BidderTestApp.class)
@AutoConfigureMockMvc
public class AdServingTest {

	@Mock
	private CampaignService campaignService;

	@Mock
	private Campaign campaign;

	@Autowired
	private BidResponseConverter bidResponseConverter;

	private MockMvc mockMvc;

	private BidController bidController;
	
	@Before
	public void setUp() {
		BidController bidController = new BidController(campaignService, bidResponseConverter);

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();
	}

	@Test
	public void respond_with_status_200_and_the_highest_bid_returned_the_bidders() throws Exception {

		BidRequest bidRequest = TestUtilities.GetBidRequest("requestFromClient.json");

		List<Campaign> expectedCampaignList = campaignService.getAllCampaigns();
		
		when(campaignService.getAllCampaigns()).thenReturn(expectedCampaignList);

		ObjectMapper objectMapper = new ObjectMapper();
		byte[] data_req = objectMapper.writeValueAsBytes(bidRequest);

		mockMvc.perform(post("/bid")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(data_req))
				.andExpect(status().isOk())
				.andExpect(jsonPath(("$.content[0]")).value("e7fe51ce4f6376876353ff0961c2cb0d"));

	}

	@Test
	public void respond_with_status_204_and_emty_body_if_all_bidders_didnt_send_any_bids() throws Exception {
		//Given
		BidRequest bidRequest = TestUtilities.GetBidRequest("requestFromClient.json");
		List<Campaign> expectedCampaignList = Collections.emptyList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] data = objectMapper.writeValueAsBytes(bidRequest);
		
		//when
		when(campaignService.getAllCampaigns()).thenReturn(expectedCampaignList);

		//then
		mockMvc.perform(post("/bid")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(data))
				.andExpect(status().isNoContent());

	}
}
