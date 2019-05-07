package it.mongo.java.util.db;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import it.mongo.java.util.file.FileHandler;

public class DAOCrud {

	public DAOCrud() {
		// TODO Auto-generated constructor stub
	}

	public void drop() {
		DBConnection db = new DBConnection();

		/**** Get collection / table from 'bibitem' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.Connection().getCollection("bibitem");
		table.drop();
	}
	
	public void insert(LinkedList<LinkedHashMap<String, String>> l) {
		DBConnection db = new DBConnection();

		/**** Get collection / table from 'bibitem' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.Connection().getCollection("bibitem");

		/**** Insert ****/

		for (HashMap<String, String> doc : l) {
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			
			for (String k : doc.keySet()) {
				document.put(k, doc.get(k));
			}
			table.insert(document);
		}
	}
	
	public void read() {
		DBConnection db = new DBConnection();

		/**** Get collection / table from 'bibitem' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.Connection().getCollection("bibitem");

		/**** Find and display ****/

		DBCursor cursor = table.find().sort(new BasicDBObject("nameCitation",1));
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	
	public void update() {
		DBConnection db = new DBConnection();

		/**** Get collection / table from 'bibitem' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.Connection().getCollection("bibitem");
		
		/**** Update ****/
		// search document where name="mkyong" and update it with new values
		BasicDBObject query = new BasicDBObject();
		query.put("name", "mkyong");

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", "mkyong-updated");

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		table.update(query, updateObj);

		/**** Find and display ****/
		BasicDBObject searchQuery2 
		= new BasicDBObject().append("name", "mkyong-updated");

		DBCursor cursor2 = table.find(searchQuery2);

		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
	}

	@SuppressWarnings("static-access")
	public void write(String string) {
		DBConnection db = new DBConnection();

		/**** Get collection / table from 'bibitem' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.Connection().getCollection("bibitem");

		/**** Find and display ****/
//		BasicDBObject searchQuery = new BasicDBObject();
//		searchQuery..put("name", "mkyong");
//
//		DBCursor cursor = table.find(searchQuery);
		DBCursor cursor = table.find().sort(new BasicDBObject("nameCitation",1));
		FileHandler f = new FileHandler();
		f.setFileHandler(null, "bibliografiaOut.bib");
		while (cursor.hasNext()) {
			DBObject line = cursor.next();
			String[] attr = line.toString().split("\"} ,")[1].replaceAll("\"", "").split(",");
			int c=0;
			for(String a: attr) {
				c++;
				String nameAttr = a.split(" : ")[0];
				String valueAttr = a.split(" : ")[1].trim();
			
				if(nameAttr.contains("typeCitation"))
					FileHandler.writeFile(valueAttr+"{");
				else if (nameAttr.contains("nameCitation"))
					FileHandler.writeFileLine(valueAttr+",");
				else {
					if(c==attr.length)
						FileHandler.writeFileLine("\t"+nameAttr+"={"+valueAttr.replace("}", "")+"}");
					else if(nameAttr.contains("title"))
						FileHandler.writeFileLine("\t"+nameAttr+"={{"+valueAttr+"}},");
					else
						FileHandler.writeFileLine("\t"+nameAttr+"={"+valueAttr+"},");
				}
			}
			FileHandler.writeFileLine("}\n");
		}
		f.closeFile();
	}
}