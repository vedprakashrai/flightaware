package com.flightaware.app.model;

public class FlightDetails {
	private FlightLegDetails flightLegDetails;
	private String totalFlightTime;
	private String totalTripTime;
	private String fLSFlightType;
	public FlightLegDetails getFlightLegDetails() {
		return flightLegDetails;
	}
	public void setFlightLegDetails(FlightLegDetails flightLegDetails) {
		this.flightLegDetails = flightLegDetails;
	}
	public String getTotalFlightTime() {
		return totalFlightTime;
	}
	public void setTotalFlightTime(String totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}
	public String getTotalTripTime() {
		return totalTripTime;
	}
	public void setTotalTripTime(String totalTripTime) {
		this.totalTripTime = totalTripTime;
	}
	public String getfLSFlightType() {
		return fLSFlightType;
	}
	public void setfLSFlightType(String fLSFlightType) {
		this.fLSFlightType = fLSFlightType;
	}
	
	
	
}
