package com.flightaware.app.controller;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class ApiCalls {
	
	//API Call to get list of flights
	public String getFlights(String p1,String src,String dest,String date) throws IOException, InterruptedException, ParseException
	{
		date = date.replace("-", "");
		String url = "https://timetable-lookup.p.rapidapi.com/TimeTable/"+src+"/"+dest+"/"+date+"/?Connection=NONSTOP";
		System.out.println(url);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("x-rapidapi-key", "357df87d7fmshb3c3334338cb884p13a011jsn6ef2c3ab2598")
				.header("x-rapidapi-host", "timetable-lookup.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		//HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		//return response.body();
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><OTA_AirDetailsRS PrimaryLangID=\"eng\" Version=\"1.0\" TransactionIdentifier=\"\" FLSNote=\"This XML adds attributes not in the OTA XML spec.  All such attributes start with FLS\" FLSDevice=\"ota-xml-expanded\" xmlns=\"http://www.opentravel.org/OTA/2003/05\"><Success/><FLSResponseFields FLSOriginCode=\"BOS\" FLSOriginName=\"Boston\" FLSDestinationCode=\"LAX\" FLSDestinationName=\"Los Angeles\" FLSStartDate=\"2023-12-17\" FLSEndDate=\"2023-12-17\" FLSResultCount=\"304\" FLSRoutesFound=\"542\" FLSBranchCount=\"3336\" FLSTargetCount=\"2329\" FLSRecordCount=\"2237809\"/><FlightDetails TotalFlightTime=\"PT7H29M\" TotalMiles=\"2659\" TotalTripTime=\"PT8H14M\" FLSDepartureDateTime=\"2023-12-17T05:01:00\" FLSDepartureTimeOffset=\"-0500\" FLSDepartureCode=\"BOS\" FLSDepartureName=\"Boston\" FLSArrivalDateTime=\"2023-12-17T10:15:00\" FLSArrivalTimeOffset=\"-0800\" FLSArrivalCode=\"LAX\" FLSArrivalName=\"Los Angeles\" FLSFlightType=\"Connect\" FLSFlightLegs=\"2\" FLSFlightDays=\"......7\" FLSDayIndicator=\"\"><FlightLegDetails DepartureDateTime=\"2023-12-17T05:01:00\" FLSDepartureTimeOffset=\"-0500\" ArrivalDateTime=\"2023-12-17T06:15:00\" FLSArrivalTimeOffset=\"-0500\" FlightNumber=\"617\" JourneyDuration=\"PT1H14M\" SequenceNumber=\"1\" LegDistance=\"187\" FLSMeals=\"\" FLSInflightServices=\" \" FLSUUID=\"BOSJFK20231217B6617\"><DepartureAirport CodeContext=\"IATA\" LocationCode=\"BOS\" FLSLocationName=\"Edward L. Logan International Airport\" Terminal=\"C\" FLSDayIndicator=\"\"/><ArrivalAirport CodeContext=\"IATA\" LocationCode=\"JFK\" FLSLocationName=\"John F. Kennedy International Airport\" Terminal=\"5\" FLSDayIndicator=\"\"/><MarketingAirline Code=\"B6\" CodeContext=\"IATA\" CompanyShortName=\"JetBlue Airways\"/><Equipment AirEquipType=\"E90\"/></FlightLegDetails></FlightDetails><FlightDetails TotalFlightTime=\"PT6H21M\" TotalMiles=\"2608\" TotalTripTime=\"PT6H21M\" FLSDepartureDateTime=\"2023-12-17T19:55:00\" FLSDepartureTimeOffset=\"-0500\" FLSDepartureCode=\"BOS\" FLSDepartureName=\"Boston\" FLSArrivalDateTime=\"2023-12-17T23:16:00\" FLSArrivalTimeOffset=\"-0800\" FLSArrivalCode=\"LAX\" FLSArrivalName=\"Los Angeles\" FLSFlightType=\"NonStop\" FLSFlightLegs=\"1\" FLSFlightDays=\"......7\" FLSDayIndicator=\"\"><FlightLegDetails DepartureDateTime=\"2023-12-17T19:55:00\" FLSDepartureTimeOffset=\"-0500\" ArrivalDateTime=\"2023-12-17T23:16:00\" FLSArrivalTimeOffset=\"-0800\" FlightNumber=\"462\" JourneyDuration=\"PT6H21M\" SequenceNumber=\"1\" LegDistance=\"2608\" FLSMeals=\"F\" FLSInflightServices=\"  3/  5/ 18/ 20/ 22/ 27/ 99\" FLSUUID=\"BOSLAX20231217DL462\"><DepartureAirport CodeContext=\"IATA\" LocationCode=\"BOS\" FLSLocationName=\"Edward L. Logan International Airport\" Terminal=\"A\" FLSDayIndicator=\"\"/><ArrivalAirport CodeContext=\"IATA\" LocationCode=\"LAX\" FLSLocationName=\"Los Angeles International Airport\" Terminal=\"3\" FLSDayIndicator=\"\"/><MarketingAirline Code=\"DL\" CodeContext=\"IATA\" CompanyShortName=\"Delta Air Lines\"/><Equipment AirEquipType=\"75W\"/></FlightLegDetails></FlightDetails></OTA_AirDetailsRS>";
		
	}
	public String dprices(String ctry,String curr,String p1,String p2,String p3,String d,String d2) throws IOException, InterruptedException, ParseException
	{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/"+ctry+"/"+curr+"/"+p1+"/"+p2+"/"+p3+"/"+d+"?inboundpartialdate="+d2))
				.header("x-rapidapi-key", "4e96ca0c82msh7acc5424070fda1p1c7d82jsn9ec25a33485e")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
		
	}
	public String rts(String ctry,String curr,String p1,String p2,String p3,String d) throws IOException, InterruptedException, ParseException
	{
				HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/"+ctry+"/"+curr+"/"+p1+"/"+p2+"/"+p3+"/"+d))
				.header("x-rapidapi-key", "4e96ca0c82msh7acc5424070fda1p1c7d82jsn9ec25a33485e")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
		
	}
	public String curs() throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/reference/v1.0/currencies"))
				.header("x-rapidapi-key", "4e96ca0c82msh7acc5424070fda1p1c7d82jsn9ec25a33485e")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
		
	}
	public String cntrys() throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/reference/v1.0/countries/en-US"))
				.header("x-rapidapi-key", "4e96ca0c82msh7acc5424070fda1p1c7d82jsn9ec25a33485e")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
		
	}
	public String placess(String curr,String code,String cntry,String locale) throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/"+code+"/"+curr+"/"+locale+"/?query="+cntry))
				.header("x-rapidapi-key", "4e96ca0c82msh7acc5424070fda1p1c7d82jsn9ec25a33485e")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
		
	}
	
	//search flights
	/*HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://skyscanner50.p.rapidapi.com/api/v1/searchFlights?origin=LOND&destination=NYCA&date=%3CREQUIRED%3E&adults=1&currency=USD&countryCode=US&market=en-US"))
			.header("X-RapidAPI-Key", "357df87d7fmshb3c3334338cb884p13a011jsn6ef2c3ab2598")
			.header("X-RapidAPI-Host", "skyscanner50.p.rapidapi.com")
			.method("GET", HttpRequest.BodyPublishers.noBody())
			.build();
	HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	System.out.println(response.body());*/
}