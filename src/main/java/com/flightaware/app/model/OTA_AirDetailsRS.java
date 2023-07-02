package com.flightaware.app.model;

import java.util.List;

public class OTA_AirDetailsRS {
	
	private List<FlightDetails> flightDetails;
	private String fLSNote;
	
	public List<FlightDetails> getFlightDetails() {
		return flightDetails;
	}
	public void setFlightDetails(List<FlightDetails> flightDetails) {
		this.flightDetails = flightDetails;
	}
	public String getfLSNote() {
		return fLSNote;
	}
	public void setfLSNote(String fLSNote) {
		this.fLSNote = fLSNote;
	}
	
	

}
