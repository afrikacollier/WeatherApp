package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


/*What this class is going to do is it is going
 * to be a utility class that we use to make the 
 * API call..........
 * 
 * It is very common when making Spring Boot applications
 * to create "Service" classes that essentially
 * are just utility classes.
 */

//He also talks about web parameters

//tells Spring Boot what this class is for (it's not a Controller and it's not a Repository)
//later on we will ask for an Injection (weather service object and Spring Boot will make it for us) and the utility class will not have to be instantiated 
//then with Service
@Service
public class WeatherService {
    @Value("${api_key}") //will grab it from the configuration or the environment we put the api key
    private String apiKey;
    
    public Response getForecast(String zipCode) {
    	//String url ="https://api.openweathermap.org/data/2.5/weather";
    	//url += "?zip=" + zipCode + "&units=imperial&appid=" + apiKey;
    
    	UriComponents uriComponents = UriComponentsBuilder.newInstance()
    			.scheme("https")
    			.host("api.openweathermap.org")
    			.path("/data/2.5/weather")
    			.queryParam("zip", zipCode)
    			.queryParam("units", "imperial")
    			.queryParam("appid", apiKey)
    			.build();
    	String url = uriComponents.toUriString();
    
    	//Any time you want to make a REST API call or just generally
    	//any request over HTTP via Spring Boot you create a RestTemplate.
    	RestTemplate restTemplate = new RestTemplate();
    	//We want to do an HTTP GET to get the page
    	Response response;
    	try {
    		response = restTemplate.getForObject(url, Response.class);
    	}
    	catch (HttpClientErrorException e) {
    		response = new Response();
    		response.setName("error");
    	}
    	
    	return response;
    }
}
