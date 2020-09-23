package com.ltts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class GeofenceApplication implements CommandLineRunner{

	@Autowired
	DistanceService distanceService;
	
	@Autowired
	PolygonService polygonService;
	
	public static void main(String[] args) {
		SpringApplication.run(GeofenceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		double lat1 = 18.954090;
		double lon1 = 87.121814;		
				
		double lat2 = 18.954090;	
		double lon2 = 87.121814;
		
		
		double distance2 = distanceService.distance(lat1, lat2, lon1, lon2, 0, 0);
		
		double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, lon1, lat2, lon2);
		
		System.out.println("distance "+ dist);
		System.out.println("distance "+ distance2);
		
		
		List<Location> poly = new ArrayList<Location>();
		poly.add(new Location(14.017223749117512, 83.94433647394182));
		poly.add(new Location(14.694132145652691, 85.44506862759592));
		poly.add(new Location(13.888211536518165, 86.73925794661046));
		poly.add(new Location(12.599598832790107, 86.29870645701887));
		poly.add(new Location(12.46768752049401, 84.39917039126159));
		
		Location loc = new Location(12.645698276524334, 85.32971184700729);
		//12.645698276524334 ------85.32971184700729
		
		boolean status = polygonService.isPointInPolygon(poly, loc);
		
		System.out.println("polygon   "+status);
		
	
		
	}

}
