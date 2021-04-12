package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.WeatherApiClient;

class WeatherApiClientTests {
	private WeatherApiClient weatherClient;
	
	@BeforeEach
	void instantiateNewsClient() {
		weatherClient = new WeatherApiClient();
	}
	
	@Test
	void testGetWeatherZipCode() throws Exception {
		// Ensure that user can get weather with zip code
		String weatherString = weatherClient.getWeather("63130");
		assertFalse(weatherString.length() == 0);
	}
	
	@Test
	void testGetWeatherCityName() throws Exception {
		// Ensure that user can get weather with city name
		String weatherString = weatherClient.getWeather("St. Louis");
		assertFalse(weatherString.length() == 0);
	}
	
	@Test
	void testGetWeatherNoInput() throws Exception {
		// Ensure that weather is retrieved using user's ip address if no location is input
		String weatherString = weatherClient.getWeather("");
		assertFalse(weatherString.length() == 0);
	}
}