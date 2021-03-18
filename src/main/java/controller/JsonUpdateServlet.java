package controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.crawler.ExecuteCrawler;
import model.outJSON.BikeJson;
import model.outJSON.BikeJsonOut;
import model.outJSON.ParkJson;
import model.outJSON.ParkJsonOut;

@WebServlet("/servlet/JsonUpdateServlet.controller")
public class JsonUpdateServlet extends HttpServlet {
	private BikeJsonOut bikeJson;
	private ParkJsonOut parkJson;
	@Override
	public void init() throws ServletException {
		bikeJson = new BikeJsonOut();
		parkJson = new ParkJsonOut();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				BikeJson bike = new BikeJsonOut();
				bike.BikeDbToJsonOut();
				
				ParkJson park = new ParkJsonOut();
				park.ParkDbToJsonOut();
			}
		};
		
		long delayT = 3 * 1000;
		long periodT = 1*30*1000;
		
		timer.schedule(task, delayT, periodT);
		
		
		String path = request.getContextPath();
		response.sendRedirect(path + "/homepg.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
}
