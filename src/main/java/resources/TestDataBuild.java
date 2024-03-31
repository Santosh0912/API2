package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayLoad(String name,String language, String address) {
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(address);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("htttp://google.com");
		p.setLanguage(language);
		
		//Types
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		
		//Location
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	
	public String deletePlacePayload(String placeID) {
		return "{\\r\\n    \\\"place_id\\\": \\\""+placeID+"\\\"\\r\\n}";
	}
	
	
	
}
