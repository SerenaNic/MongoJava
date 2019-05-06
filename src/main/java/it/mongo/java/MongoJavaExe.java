package it.mongo.java;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.mongo.java.util.db.DAOCrud;
import it.mongo.java.util.file.FileHandler;

public class MongoJavaExe {



	/**
	 * Java + MongoDB
	 * 
	 */

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		DAOCrud dao = new DAOCrud();
		
		dao.drop();
		FileHandler f = new FileHandler();
		f.setFileHandler("bibliografia.bib", null);

		LinkedList<HashMap<String, String>> listCol = new LinkedList<HashMap<String, String>>();	
		HashMap<String, String> hm = new HashMap<String, String>();
		String s="";
		while((s=f.readFile())!=null) {
			if(s.contains("@")) {
				
				hm = new HashMap<String, String>();
				hm.put("typeCitation", s.split("\\{")[0].replace(",", ""));
				hm.put("nameCitation", s.split("\\{")[1].replace(",", ""));

			}
			if(s.contains("=")) {
				s=s.replaceAll("[{}]", " ").replace(",", "").replaceAll("\"", "");
				hm.put(s.split("=")[0].replaceAll("\\s+", ""), s.split("=")[1].trim());
			}
			
			if(s.replaceAll("\\s+", "").equals("}")) 			
				listCol.add(hm);
					
		}

		
	
		dao.insert(listCol);

		dao.read();

		/**** Done ****/
		System.out.println("Done");

	}
}

