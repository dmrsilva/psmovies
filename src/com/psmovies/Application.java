package com.psmovies;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {
	
	public static void main(String[] args) throws Exception {
		
		String apiKey = "<apiKey>";
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

		HttpClient client = HttpClient.newHttpClient();		
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		Integer statusCode = response.statusCode();
		String json = response.body();
		
		System.out.println("Status code: " + statusCode);
		System.out.println("Answer: " + json);
		
	}

}
