package com.learnjava.numbers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.learnjava.LearnjavaApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LearnjavaApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class NumberControllerIT {

    @Value("${local.server.port}")
    private int port;

	private URL base;
	private RestTemplate template;
	
	@Before
	public void setUp() {
		template = new TestRestTemplate();
	}

	@Test
	public void createNumbers() throws Exception {
		
		this.base = new URL("http://localhost:" + port + "/numbers/create");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> entity = new HttpEntity<String>("value=100", headers);
	    
		ResponseEntity<String> response = template.postForEntity(base.toString(), entity, String.class);
		
		JsonParser parser = new BasicJsonParser();
		Map<String,Object> parsedResponse = parser.parseMap(response.getBody());
		
		@SuppressWarnings("unchecked")
		Map<String,Object> result = (Map<String, Object>) parsedResponse.get("data");
		
		assertThat(result.get("value").toString(), equalTo("100"));
	}
	
	@Test
	public void listNumbers() throws Exception {
		
		this.base = new URL("http://localhost:" + port + "/numbers");
		
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		
		JsonParser parser = new BasicJsonParser();
		Map<String,Object> parsedResponse = parser.parseMap(response.getBody());
		
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) parsedResponse.get("data");
		
		if(result.size() < 1) {
			assertTrue("No result", false);
		}
	 }
	
	@SuppressWarnings("unchecked")
	@Test
	public void biggestNumber() throws Exception {
		
		this.base = new URL("http://localhost:" + port + "/numbers");
		
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		
		JsonParser parser = new BasicJsonParser();
		Map<String,Object> parsedResponse = parser.parseMap(response.getBody());
		
		List<Object> result = (List<Object>) parsedResponse.get("data");
		
		ArrayList<Long> numbers = new ArrayList<Long>();
		for (int i = 0; i < result.size(); i++) {
			Map<String,Object> record = (Map<String, Object>) result.get(i);
			numbers.add((long)record.get("value"));
		}
		
		Long biggestNumber = Collections.max(numbers);
		
		this.base = new URL("http://localhost:" + port + "/numbers/biggest");
		
		response = template.getForEntity(base.toString(), String.class);
		
		parser = new BasicJsonParser();
		parsedResponse = parser.parseMap(response.getBody());
		
		Map<String,Object> biggestResult = (Map<String, Object>) parsedResponse.get("data");
		
		assertThat(biggestResult.get("value").toString(), equalTo(biggestNumber.toString()));
		
	 }
	
	@SuppressWarnings("unchecked")
	@Test
	public void smallestNumber() throws Exception {
		
		this.base = new URL("http://localhost:" + port + "/numbers");
		
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		
		JsonParser parser = new BasicJsonParser();
		Map<String,Object> parsedResponse = parser.parseMap(response.getBody());
		
		List<Object> result = (List<Object>) parsedResponse.get("data");
		
		ArrayList<Long> numbers = new ArrayList<Long>();
		for (int i = 0; i < result.size(); i++) {
			Map<String,Object> record = (Map<String, Object>) result.get(i);
			numbers.add((long)record.get("value"));
		}
		
		Long smallestNumber = Collections.min(numbers);
		
		this.base = new URL("http://localhost:" + port + "/numbers/smallest");
		
		response = template.getForEntity(base.toString(), String.class);
		
		parser = new BasicJsonParser();
		parsedResponse = parser.parseMap(response.getBody());
		
		Map<String,Object> smallestResult = (Map<String, Object>) parsedResponse.get("data");
		
		assertThat(smallestResult.get("value").toString(), equalTo(smallestNumber.toString()));
		
	 }
}


