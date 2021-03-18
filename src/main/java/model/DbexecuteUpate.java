package model;

import java.util.Timer;

import model.crawler.ExecuteCrawler;
import model.outJSON.ExecuteJsonOut;

public class DbexecuteUpate {
	
	public static void main(String[] args) {
		
		Timer timer = new Timer();

		long delayT = 3 * 1000;
		long periodT = 1*30*1000;
		
		timer.schedule(new ExecuteCrawler(), delayT, periodT);
		//timer.schedule(new ExecuteJsonOut(), delayT, periodT);	
	}
}


