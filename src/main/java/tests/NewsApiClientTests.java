package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.NewsApiClient;

class NewsApiClientTests {
	private NewsApiClient newsClient;
	
	@BeforeEach
	void instantiateNewsClient() {
		newsClient = new NewsApiClient();
	}
	
	@Test
	void testParseHeadlines() throws Exception {
		// Test that user receives specified number of stories
		newsClient.numHeadlines = "30";
		newsClient.parseResponse();
		assertEquals(newsClient.headlines.length, 30);
	}
	
	@Test
	void testGetTopHeadlines() throws Exception {
		// Test that response string isn't empty 
		String headlineString = newsClient.requestHeadlines();
		assertTrue(headlineString.contains("\"status\":\"ok\""));
		assertFalse(headlineString.length() == 0);
	}

}
