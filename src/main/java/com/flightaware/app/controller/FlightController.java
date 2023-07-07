package com.flightaware.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightaware.app.model.AirportResponse;
import com.flightaware.app.model.Airports;
import com.flightaware.app.model.FlightDetails;
import com.flightaware.app.model.User;
import com.flightaware.app.model.UserIp;
import com.flightaware.app.repository.FlightRepo;
import com.flightaware.app.repository.userRepo;
import com.flightaware.app.service.ServiceResponseDeserialize;

@Controller
public class FlightController {

	@Autowired
	FlightRepo frepo;

	@Autowired
	userRepo urepo;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	cityCode cc = new cityCode();

	@GetMapping("/")
	public ModelAndView homeGet(ModelAndView modelAndView, UserIp userip) {
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/Register")
	public ModelAndView RegGet(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@PostMapping("/Register")
	public String registerUser(ModelAndView modelAndView, User user) {
		String path = null;
		String email = user.getEmailId();
		System.out.println(email);
		User existingUser = urepo.findByEmailIdIgnoreCase(email);
		if (existingUser != null) {
			modelAndView.addObject("msg", "This email already exists!");
			modelAndView.setViewName("register");
		} else {
			System.out.println(user.getPass());
			user.setPass(encoder.encode(user.getPass()));
			urepo.save(user);
			// sendEmail(user.getEmailId());
			modelAndView.addObject("emailId", user.getEmailId());
			path = "redirect:/Login";
		}

		return path;
	}

	@GetMapping("/Login")
	public ModelAndView LogGet(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/Login")
	public ModelAndView loginUser(ModelAndView modelAndView, User user,HttpSession session) {
		String email = user.getEmailId();
		User existingUser = urepo.findByEmailIdIgnoreCase(email);
		System.out.println(existingUser);
		if (existingUser != null) {
			if (encoder.matches(user.getPass(), existingUser.getPass())) {
				// successfully logged in
				session.setAttribute("loggedEmail", existingUser.getEmailId()); 
				modelAndView.addObject("msg", "You Have Successfully Logged in");
				modelAndView.setViewName("loginHome");
			} else {
				// wrong password
				modelAndView.addObject("msg", "Incorrect password. Try again.");
				modelAndView.setViewName("login");
			}
		} else {
			modelAndView.addObject("msg", "The email provided does not exist!");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	long loggedInUser;

	ApiCalls ap = new ApiCalls();

	@GetMapping("/logHome")
	public ModelAndView loghomeGet(ModelAndView modelAndView, UserIp userip) {
		modelAndView.setViewName("loginHome");
		return modelAndView;
	}

	@GetMapping("/flights")
	public ModelAndView flightsGet(ModelAndView modelAndView, UserIp userip) {
		modelAndView.addObject("userip", userip);
		modelAndView.setViewName("flights");
		return modelAndView;
	}

	@PostMapping(value = "/flights")
	public ModelAndView dispform(ModelAndView modelAndView, UserIp userip)
			throws InterruptedException, IOException, ParseException {
		String locale, src, dest, dte;
		// frepo.save(userip);
		locale = userip.getLocale();
		src = userip.getOrigin();
		dest = userip.getDest();
		dte = userip.getOutDate();
		// Date date1=(Date) new SimpleDateFormat("yyyy-mm-dd").parse(dte);
		// DateFormat sdf = new SimpleDateFormat();
		// java.util.Date out = sdf.parse(dte);
		// System.out.println(dte+"\t"+date1);
		String srcc, destc;
		srcc = cc.codes(src);
		destc = cc.codes(dest);
		System.out.println(src + " " + dest);
		System.out.println(srcc + " " + destc);
		String str = ap.getFlights(locale, src, dest, dte);
		
		List<FlightDetails> flightDetails =ServiceResponseDeserialize.getFlightInfo(str);
		flightDetails = ServiceResponseDeserialize.formatList(flightDetails);
		
		modelAndView.addObject("msg", "Flight details");
		modelAndView.addObject("userip", userip);
		modelAndView.addObject("flights",flightDetails);
		if(flightDetails ==null || flightDetails.isEmpty()) {
			modelAndView.addObject("noFlight","Sorry, No direct flights available.");
		}
		modelAndView.setViewName("flights");
		return modelAndView;
	}
	
	@PostMapping(value = "/airport")
	public ModelAndView getAirportInfo(ModelAndView modelAndView, UserIp userip)
			throws InterruptedException, IOException, ParseException {
		String airport =  userip.getOrigin();
		String aInfo = ap.getAirportInfo(airport);
		String aAirlineInfo = ap.getAirportAirlines(airport);
		Airports aps =  ((AirportResponse)ServiceResponseDeserialize.getObjectFromXml(aInfo, AirportResponse.class)).getAirports();

		aps.setCarrier(((AirportResponse)ServiceResponseDeserialize.getObjectFromXml(aAirlineInfo, AirportResponse.class)).getAirports().getCarrier());

		modelAndView.addObject("msg", "Airport details");
		modelAndView.addObject("userip", userip);
		modelAndView.addObject("airport",aps.getAirport());
		modelAndView.addObject("carrier",aps.getCarrier());
		modelAndView.setViewName("route");
		return modelAndView;
	}



	@GetMapping("/routes")
	public ModelAndView drGet(ModelAndView modelAndView, UserIp userip) {
		modelAndView.addObject("userip", userip);
		modelAndView.setViewName("route");
		return modelAndView;
	}

	@PostMapping(value = "/routes")
	public ModelAndView route(ModelAndView modelAndView, UserIp userip)
			throws InterruptedException, IOException, ParseException {
		String airport =  userip.getOrigin();
		String aInfo = ap.getAirportInfo(airport);
		String aAirlineInfo = ap.getAirportAirlines(airport);
		Airports aps =  ((AirportResponse)ServiceResponseDeserialize.getObjectFromXml(aInfo, AirportResponse.class)).getAirports();
		
		aps.setCarrier(((AirportResponse)ServiceResponseDeserialize.getObjectFromXml(aAirlineInfo, AirportResponse.class)).getAirports().getCarrier());
		
		modelAndView.addObject("msg", "Airport details");
		modelAndView.addObject("userip", userip);
		modelAndView.addObject("airport",aps.getAirport());
		modelAndView.addObject("carrier",aps.getCarrier());
		modelAndView.setViewName("route");
		return modelAndView;
	}

	@GetMapping("/uguides")
	public ModelAndView uguidet(ModelAndView modelAndView, HttpSession session) {
		
		String email = (String)session.getAttribute("loggedEmail");
		modelAndView.addObject("userip",urepo.findByEmailIdIgnoreCase(email));
		modelAndView.setViewName("uguide");
		return modelAndView;
	}
	
	
	//User existingUser = urepo.findByEmailIdIgnoreCase(email);

	@GetMapping("/currs")
	public ModelAndView currss(ModelAndView modelAndView) throws IOException, InterruptedException {
		String str = ap.curs();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> restMap = mapper.readValue(str, Map.class);
		System.out.println(restMap.toString());
		ArrayList<String> qlist = (ArrayList<String>) restMap.get("Currencies");
		Iterator doit = qlist.iterator();
		Iterable<Object> curList = (Iterable<Object>) StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(doit, 0), false).collect(Collectors.toList());

		modelAndView.addObject("curList", curList);
		modelAndView.setViewName("uguide");
		return modelAndView;
	}

	@GetMapping("/cntrys")
	public ModelAndView cntry(ModelAndView modelAndView) throws IOException, InterruptedException {
		String str = ap.cntrys();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> restMap = mapper.readValue(str, Map.class);
		System.out.println(restMap.toString());
		ArrayList<String> qlist = (ArrayList<String>) restMap.get("Countries");
		Iterator doit = qlist.iterator();
		Iterable<Object> cntry = (Iterable<Object>) StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(doit, 0), false).collect(Collectors.toList());

		modelAndView.addObject("cntry", cntry);
		modelAndView.setViewName("uguide");
		return modelAndView;
	}

	@GetMapping("/places")
	public ModelAndView places(ModelAndView modelAndView, UserIp userip) throws IOException, InterruptedException {

		modelAndView.addObject("userip", userip);
		modelAndView.setViewName("places");
		return modelAndView;
	}

	@PostMapping("/places")
	public ModelAndView placespost(ModelAndView modelAndView, UserIp userip) throws IOException, InterruptedException {
		String cntry, curr, code, locale;
		// frepo.save(userip);
		locale = userip.getLocale();
		cntry = userip.getCnt();
		code = userip.getCountry();
		curr = userip.getCurr();
		String str = ap.placess(curr, code, cntry, locale);

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> restMap = mapper.readValue(str, Map.class);
		System.out.println(restMap.toString());
		ArrayList<String> plist = (ArrayList<String>) restMap.get("Places");
		Iterator doit = plist.iterator();
		Iterable<Object> placeArray = (Iterable<Object>) StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(doit, 0), false).collect(Collectors.toList());
		modelAndView.addObject("placeArray", placeArray);
		modelAndView.addObject("userip", userip);
		modelAndView.setViewName("places");
		return modelAndView;
	}

}
