package com.ltts;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PolygonService {


	public boolean isPointInPolygon(List<Location> poly, Location point)
	{
	    int i, j;
	    boolean status = false;
	    for (i = 0, j = poly.size() - 1; i < poly.size(); j = i++)
	    {
	        if ((((poly.get(i).getLt() <= point.getLt()) 
	        	  && (point.getLt() < poly.get(j).getLt())) 
	              || ((poly.get(j).getLt() <= point.getLt()) 
	              && (point.getLt() < poly.get(i).getLt()))) 	                
	        	  && (point.getLg() < (poly.get(j).getLg() - poly.get(i).getLg()) * (point.getLt() - poly.get(i).getLt()) 
	               / (poly.get(j).getLt() - poly.get(i).getLt()) + poly.get(i).getLg()))
	        {

	        	status = !status;
	        }
	    }

	    return status;
	}
}
