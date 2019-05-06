package it.mongo.java;
import java.util.HashMap;
import java.util.LinkedList;

import it.mongo.java.util.db.DAOCrud;

public class MongoJavaExe {



	/**
	 * Java + MongoDB
	 * 
	 */

	public static void main(String[] args) {

		LinkedList<HashMap<String, String>> listCol = new LinkedList<HashMap<String, String>>();		
		HashMap<String, String> hm = new HashMap<String, String>();
		
		hm.put("item", "value");
		hm.put("item1", "value");
		listCol.add(hm);
		
		DAOCrud dao = new DAOCrud();
		dao.insert(listCol);

		dao.read();

		/**** Done ****/
		System.out.println("Done");

	}
}

