package it.mongo.java.util.db;

import java.util.HashMap;
import java.util.LinkedList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

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
	public void insert(LinkedList<HashMap<String, String>> l) {
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
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "mkyong");

		DBCursor cursor = table.find(searchQuery);

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
}