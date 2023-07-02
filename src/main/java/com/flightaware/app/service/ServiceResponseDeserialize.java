package com.flightaware.app.service;

import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightaware.app.model.FlightDetails;
import com.flightaware.app.model.OTA_AirDetailsRS;

public class ServiceResponseDeserialize {
	
	
	public static  List<FlightDetails> getFlightInfo(String xmlResponse){
		
		try {
            JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
            String json = xmlJSONObj.toString();
            ObjectMapper mapper = new ObjectMapper();
    		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    		OTA_AirDetailsRS  response =mapper.readValue(json,OTA_AirDetailsRS.class);
    		return response.getFlightDetails();
    		
        } catch (Exception je) {
            System.out.println(je.toString());
        }
		return null;
	}

}
