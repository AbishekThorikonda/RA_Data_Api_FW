package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.location;

public class AddPlaceTestData {
	
	public AddPlace addPlacePayload()
	{
		AddPlace ap = new AddPlace();
		  ap.setAccuracy(50);
		  ap.setName("Abishek");
		  ap.setPhone_number("123123123");
		  ap.setAddress("Canada");
		  ap.setWebsite("www.abi.com");
	      ap.setLanguage("french");
	      List<String>myList=new ArrayList<String>();
	      myList.add("yo-1");
	      myList.add("yo-2");
	      ap.setTypes(myList);
	      location l = new location();
	      l.setLat(20);
	      l.setLng(200);
	      ap.setLocation(l);
	      
	      return ap;
	}


}
