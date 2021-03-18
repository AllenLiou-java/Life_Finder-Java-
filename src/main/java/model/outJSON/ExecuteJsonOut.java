package model.outJSON;

import java.util.TimerTask;

public class ExecuteJsonOut  extends TimerTask {

	public void run() {
		
		BikeJson bike = new BikeJsonOut();
		bike.BikeDbToJsonOut();
		
		ParkJson park = new ParkJsonOut();
		park.ParkDbToJsonOut();

		
	}
}
