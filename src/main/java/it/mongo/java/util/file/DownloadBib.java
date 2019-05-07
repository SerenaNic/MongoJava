package it.mongo.java.util.file;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DownloadBib {



	public static String download(String url) {

		StringBuilder res = new StringBuilder();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nicolazzo\\Documents\\Software\\chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		browser.get(url);
		WebElement href = browser.findElement(By.xpath("//a[@href='/beta/login']"));
		System.out.println((href.isDisplayed()));
		browser.close();       
		return res.toString();

	}
//		 try{
//	            
//			 Connection connection = Jsoup.connect(url);
//			 connection.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
//			 connection.referrer("https://www.google.com");
//			 connection.timeout(20000);
//			 connection.followRedirects(true);
//			   connection.header("Content-Length", "0");
//			   connection.header("Host", "scholar.googleusercontent.come");
//			   connection.header("Pragma", "no-cache");
//			 connection.header("Proxy-Connection", "Keep-Alive");
//			
//			 Document docCustomConn = connection.get();
//	           
//	            res.append(docCustomConn.select("body").toString());
//	        }catch(IOException ioe){
//	            ioe.printStackTrace();
//	        }
//		return res.toString();
//	}


}
