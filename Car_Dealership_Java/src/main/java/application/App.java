package application;

import java.util.concurrent.TimeUnit;

import head_office.HeadOffice;
import time.ChangeableTime;
import timer.DurationInSeconds;
import utils.simulator.Simulator;

public class App {

	public static void main(String[] args) throws InterruptedException {

		// TODO - Hadoop path
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
				
		HeadOffice ho = HeadOffice.getInstance(
				new ChangeableTime(8,59,58), 
				new DurationInSeconds(TimeUnit.SECONDS, 60));

		Simulator sim = new Simulator(ho);
		ho.registerSimulator(sim);
		sim.start();		
	}

}
