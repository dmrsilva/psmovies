package com.psmovies;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
	
	public static void main(String[] args) throws Exception {
		
		String apiKey = "<apiKey>";
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

		HttpClient client = HttpClient.newHttpClient();		
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		String json = response.body();
		
		Pattern pattern = Pattern.compile(".*\\[(.*)\\].*");
		Matcher matcher = pattern.matcher(json);
		
		matcher.matches();
		
		String[] objects = matcher.group(1).split("\\},\\{");
		
		for (int i = 0; i < objects.length; i++) {
			objects[i] = objects[i].replace("{", "").replace("}", "");
		}
		
		List<String> titles = Arrays.asList(parseTitles(objects));		
		titles.forEach(System.out::println);
		
		System.out.println();
		
		List<String> imgUrls = Arrays.asList(parseUrlImages(objects));
		imgUrls.forEach(System.out::println);

	}
	
	public static String[] attributes(String[] moviesArray, int attributePositionInArray, int attributeInitialPosition) {
		String[] attributesArray = new String[moviesArray.length];
		for (int i = 0; i < moviesArray.length; i++) {
			String[] movie = moviesArray[i].split(",");
			attributesArray[i] = movie[attributePositionInArray].substring(attributeInitialPosition, movie[attributePositionInArray].length() - 1);
		}
		return attributesArray;
	}
	
	public static String[] parseTitles(String[] moviesArray) {
		return attributes(moviesArray, 2, 9);
	}

	public static String[] parseUrlImages(String[] moviesArray) {
		return attributes(moviesArray, 5, 9);
	}

}
