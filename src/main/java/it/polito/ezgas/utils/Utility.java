package it.polito.ezgas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {
	
	public static boolean checkCoordinates(double lat, double lon) {
		if (lat < (double) -90 || lon < (double) -180 || lat > (double) 90 || lon > (double) 180)
			return false;
		return true;
	}
	
	public static double calculateDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
		if(!checkCoordinates(lat1, lon1) || !checkCoordinates(lat2, lon2))
			return -1;
		
		double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lon2-lon1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);

	    return dist;
		/*return Math.sqrt(Math.pow((Math.abs(lat1-lat2)), 2)+Math.pow((Math.abs(lon1-lon2)), 2));*/
	}

	public static double trustCalculation(int userRep, String timestamp) {
		double obsolescence;
		double trust;
		
		if(userRep < -5 || userRep > 5)
			return 0;

		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		Date data = null;
		try {
			data = sdf.parse(timestamp);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		Long timestamp_long = data.getTime();		
		Date today = new Date();
		Long today_long = today.getTime();

		if (today_long - timestamp_long > 604800000) { // 7 days
			obsolescence = 0.0;
		} else {
			obsolescence = 1 - (today_long - timestamp_long) / 604800000.0;
		}

		trust = 50 * (userRep + 5) / 10 + 50 * obsolescence;
		return trust;
	}
}
