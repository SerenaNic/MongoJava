package it.mongo.java;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

		LinkedList<LinkedHashMap<String, String>> listCol = new LinkedList<LinkedHashMap<String, String>>();	
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		String s="";
		while((s=f.readFile())!=null) {
			if(s.contains("@")) {
				
				hm = new LinkedHashMap<String, String>();
				hm.put("typeCitation", s.split("\\{")[0].toLowerCase().replace(",", ""));
				hm.put("nameCitation", s.split("\\{")[1].replace(",", ""));

			}
			if(s.contains("=")) {
				s=s.replaceAll("[{}]", " ").replace(",", "").replaceAll("\"", "");
				hm.put(s.split("=")[0].trim(), s.split("=")[1].trim());
			}
			
			if(s.replaceAll("\\s+", "").equals("}")) 			
				listCol.add(hm);
					
		}

		f.closeFile();
	
		dao.insert(listCol);

		//dao.read();
		
		dao.write("bibliografiaOut.bib");
		/**** Done ****/
		System.out.println("Done");

	}
}

