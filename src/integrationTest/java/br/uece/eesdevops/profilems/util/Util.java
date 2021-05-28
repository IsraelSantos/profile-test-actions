package br.uece.eesdevops.profilems.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {

	public static String formatDateIntegrationTests(LocalDate date) {
		if (date != null) {
			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	String res = date.format(format1);
			return res;
		}
		return null;
	}
	
	public static String buildJson(Object request) {
		Gson gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
		        .create();
		
		String json = gson.toJson(request);
		return json;
	}
	
}
