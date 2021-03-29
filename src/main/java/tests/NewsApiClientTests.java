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
	void testGetHeadlines() throws Exception{
		// Test that user can receive news stories
		newsClient.getTopHeadlines();
	}

}