package com.ltts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeofenceController {

	@Autowired
	DistanceService distanceService;
	
	@Autowired
	PolygonService polygonService;
	
	@GetMapping("/")
	public String indexpage() {
				
		return "index";
	}
	
	@GetMapping("/polygon")
	public String poligonpage() {
				
		return "polygon";
	}
	
	@PostMapping("/geofence")
	public @ResponseBody String geoFeanceAlert(@RequestParam("lat") double boatCurrentLat,@RequestParam("lng") double boatCurrentLng ) {
		
		double fenceLat = 18.954090;
		double fenceLng = 87.121814;
		double radius   = 200000;
		
		System.out.println("boatCurrentLat "+boatCurrentLat);
		System.out.println("boatCurrentLng "+boatCurrentLng);
		
		
		double distance1 = distanceService.distance(fenceLat, boatCurrentLat, fenceLng, boatCurrentLng, 0, 0);
		
		double distance2 = org.apache.lucene.util.SloppyMath.haversinMeters(fenceLat, fenceLng, boatCurrentLat, boatCurrentLng);
		
		System.out.println("distancec1----- "+distance1);
		System.out.println("distancec2----- "+distance2);
		
		
		if (radius > distance2) {
			// inside fence
			return "Inside the Geofence";
		} 
		else if (radius < distance2) {
			// outside the fence
			return "Outside the Geofence";
		}else {
			// On the line
			return "On the line ";
		}
	}
	
	@PostMapping("/polygonGeofence")
	public @ResponseBody String polygonGeoFeanceAlert(@RequestParam("lat") double boatCurrentLat,@RequestParam("lng") double boatCurrentLng ) {
		
		List<Location> poly = new ArrayList<Location>();
		poly.add(new Location(14.017223749117512, 83.94433647394182));
		poly.add(new Location(14.694132145652691, 85.44506862759592));
		poly.add(new Location(13.888211536518165, 86.73925794661046));
		poly.add(new Location(12.599598832790107, 86.29870645701887));
		poly.add(new Location(12.46768752049401, 84.39917039126159));
		
		Location loc = new Location(boatCurrentLat, boatCurrentLng);
		
		
		boolean status = polygonService.isPointInPolygon(poly, loc);

		if (status) {
			return "Inside the Polygon";
		}else {
			return "OutSide the Polygon";
		}
	}
	
}
