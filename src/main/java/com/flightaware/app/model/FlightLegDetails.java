package com.flightaware.app.model;

public class FlightLegDetails {
	private String departureDateTime;
	private String arrivalDateTime;
	private String flightNumber;
	private String journeyDuration;
	private Airport departureAirport;
	private Airport arrivalAirport;
	private Airline marketingAirline;
	public String getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getJourneyDuration() {
		return journeyDuration;
	}
	public void setJourneyDuration(String journeyDuration) {
		this.journeyDuration = journeyDuration;
	}
	public Airport getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}
	public Airport getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public Airline getMarketingAirline() {
		return marketingAirline;
	}
	public void setMarketingAirline(Airline marketingAirline) {
		this.marketingAirline = marketingAirline;
	}
	
	

}
