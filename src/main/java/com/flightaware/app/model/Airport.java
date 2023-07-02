package com.flightaware.app.model;

public class Airport {
	private String locationCode;
	private String FLSLocationName;
	private String terminal;
	private String FLSDayIndicator;
	
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getFLSLocationName() {
		return FLSLocationName;
	}
	public void setFLSLocationName(String fLSLocationName) {
		FLSLocationName = fLSLocationName;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getFLSDayIndicator() {
		return FLSDayIndicator;
	}
	public void setFLSDayIndicator(String fLSDayIndicator) {
		FLSDayIndicator = fLSDayIndicator;
	}
}
