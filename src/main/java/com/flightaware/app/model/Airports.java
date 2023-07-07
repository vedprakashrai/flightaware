package com.flightaware.app.model;

import java.util.List;

public class Airports {
	
	List<Airport> carrier;
	Airport airport;

	

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public List<Airport> getCarrier() {
		return carrier;
	}

	public void setCarrier(List<Airport> carrier) {
		this.carrier = carrier;
	}

}
