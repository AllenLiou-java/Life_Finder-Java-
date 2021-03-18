package model.crawler;

import java.util.TimerTask;

import model.outJSON.ParkJsonOut;

public class ExecuteCrawler extends TimerTask {
	
	public void run() {
		 BikeCrawler bcrawler = new BikeCrawler();
		 bcrawler.executeCrawler();
		 
		 ParkCrawler pcrawler = new ParkCrawler();
		 pcrawler.executeCrawler();
		 
		 
	}
}
