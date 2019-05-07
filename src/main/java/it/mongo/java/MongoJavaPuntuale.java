package it.mongo.java;

import it.mongo.java.util.file.DownloadBib;

public class MongoJavaPuntuale {

	public static String url ="https://scholar.googleusercontent.com/scholar.bib?q=info:fRUc-XDNutYJ:scholar.google.com/&output=citation&scisig=AAGBfm0AAAAAXNGGuKFQLMqSqJn1-yuaFUy6VkBgBy-K&scisf=4&ct=citation&cd=-1&hl=it";
	

	public static void main(String[] args) {

		String bibItem = DownloadBib.download(url);

		System.out.println(bibItem);
	}

}
