package com.flightaware.app.model;

public class Airport {
	private String locationCode;
	private String fLSLocationName;
	private String terminal;
	private String fLSDayIndicator;
	
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getFLSLocationName() {
		return fLSLocationName;
	}
	public void setFLSLocationName(String fLSLocationName) {
		this.fLSLocationName = fLSLocationName;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getFLSDayIndicator() {
		return fLSDayIndicator;
	}
	public void setFLSDayIndicator(String fLSDayIndicator) {
		this.fLSDayIndicator = fLSDayIndicator;
	}
}
