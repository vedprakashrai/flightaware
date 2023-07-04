package com.flightaware.app.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightaware.app.model.FilghtsServiceResponse;
import com.flightaware.app.model.FlightDetails;

public class ServiceResponseDeserialize {
	
	
	public static  List<FlightDetails> getFlightInfo(String xmlResponse){
		
		try {
            JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
            String json = xmlJSONObj.toString();
            ObjectMapper mapper = new ObjectMapper();
    		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    		FilghtsServiceResponse  response =mapper.readValue(json,FilghtsServiceResponse.class);
    		return response.getoTA_AirDetailsRS().getFlightDetails();
    		
        } catch (Exception je) {
            System.out.println(je.toString());
        }
		return new ArrayList<FlightDetails>();
	}
	
public static  List<FlightDetails> formatList(List<FlightDetails> flightDetails){
		
		for(FlightDetails flightDetail:flightDetails) {
			
			if(flightDetail.getFlightLegDetails()!=null) {
				if(flightDetail.getFlightLegDetails().getJourneyDuration()!=null)
					flightDetail.getFlightLegDetails().setJourneyDuration(flightDetail.getFlightLegDetails().getJourneyDuration().replace("PT", ""));
				if(flightDetail.getFlightLegDetails().getArrivalDateTime()!=null)
					flightDetail.getFlightLegDetails().setArrivalDateTime(flightDetail.getFlightLegDetails().getArrivalDateTime().replace("T", "/"));
				if(flightDetail.getFlightLegDetails().getDepartureDateTime()!=null)
					flightDetail.getFlightLegDetails().setDepartureDateTime(flightDetail.getFlightLegDetails().getDepartureDateTime().replace("T", "/"));
			
			}
		}
		
		return flightDetails;
	}

}
