package com.bidder.mx;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import com.bidder.mx.dto.BidRequest;
import com.bidder.mx.dto.BidResponse;
import com.bidder.mx.model.Campaign;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtilities {

    public static BidResponse GetBidResponse(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(fileName).getInputStream());
            return mapper.readValue(inputStreamReader, BidResponse.class);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }

    public static BidRequest GetBidRequest(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(fileName).getInputStream());
            return mapper.readValue(inputStreamReader, BidRequest.class);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
    
    public static Campaign GetCampaignList(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(fileName).getInputStream());
            return mapper.readValue(inputStreamReader, Campaign.class);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
