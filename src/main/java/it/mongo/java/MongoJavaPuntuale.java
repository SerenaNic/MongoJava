package it.mongo.java;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import it.mongo.java.util.db.DAOCrud;
import it.mongo.java.util.file.FileHandler;

public class MongoJavaPuntuale {



	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		DAOCrud dao = new DAOCrud();

		//		dao.drop();
		FileHandler f = new FileHandler();
		f.setFileHandler("bibliografia1.bib", null);

		LinkedList<LinkedHashMap<String, String>> listCol = new LinkedList<LinkedHashMap<String, String>>();	
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		String s="";
		while((s=f.readFile())!=null) {
			if(s.contains("@")) {

				hm = new LinkedHashMap<String, String>();
				hm.put("typeCitation", s.split("\\{")[0].toLowerCase().replace(",", ""));

			}
			if(s.contains("=")) {
				s=s.replaceAll("[{}]", " ").replace(",", "").replaceAll("\"", "");
				hm.put(s.split("=")[0].trim(), s.split("=")[1].trim());

			}
				

		}

		String name = "";
		if(!hm.get("author").contains(" and ")) 
			name= hm.get("author").split(" ")[0];
		else {
			String[] auts = hm.get("author").split(" and ");
			
			if(auts.length==2)
				name= auts[0].split(" ")[0].substring(0, 2)+auts[1].split(" ")[0].substring(0, 2);
			else
				name= hm.get("author").split(" ")[0]+"*";
		}

		name= name+hm.get("year").substring(2, 4);
		
		hm.put("nameCitation",name);

		listCol.add(hm);

		f.closeFile();

		dao.insert(listCol);

		//dao.read();

		//dao.write("bibliografiaOut.bib");
		/**** Done ****/
		System.out.println("Done\n \\cite{"+name+"}");

	}


}
