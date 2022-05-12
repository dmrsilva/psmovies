package com.psmovies;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Application {

	private static void getMovies(String uri) throws Exception {

		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(uri))
			.build();
		
		HttpResponse<String> response = 
			client.send(request, BodyHandlers.ofString());
		
		System.out.println(response.body());
		System.out.printf("Status code: %d", response.statusCode());

	}
	
	public static void main(String[] args) throws Exception {
		
		getMovies("https://imdb-api.com/en/API/Top250Movies/<apiKey>");
		
	}

}
