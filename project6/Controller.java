import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Do very rudimentary timing of the getNearest method, as well as very small
 * sampling to measure
 * accuracy. You may wish to increase the amount of both.
 *
 * @author amjadm
 */

public class Controller {
	private static final String FILE_NAME = "starbucks.csv";

	/**
	 * This is a helper function that reads in the Starbucks.csv file. If you
	 * modified your Starbucks.csv,
	 * or if Starbucks.csv is not in the project's folder directly (don'tput in
	 * under src folder), then
	 * this will blow up on you.
	 */
	public static Locations[] readEntryList() {

		Locations[] ret = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
			String line;
			line = br.readLine(); // First line is the count of number of Starbucks locations
			String[] components = line.split(",");

			int count = Integer.parseInt(components[0]);
			ret = new Locations[count];

			line = br.readLine(); // Column labels, to be discarded
			for (int i = 0; i < count; i++) {
				line = br.readLine();
				components = line.split(",");
				ret[i] = new Locations(components[0], components[3],
						Double.parseDouble(components[2]), Double.parseDouble(components[1]));
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return ret;
	}

	public static Locations bruteGetNearest(double lng, double lat, Locations[] slocs) {
		double dist = Starbucks.distance(slocs[0].lng, slocs[0].lat, lng, lat);
		int index = 0;
		for (int i = 1; i < slocs.length; i++) {
			double cdist = Starbucks.distance(slocs[i].lng, slocs[i].lat, lng, lat);
			if (cdist < dist) {
				index = i;
				dist = cdist;
			}
		}
		return new Locations(slocs[index]);
	}

	public static void main(String[] args) {
		Starbucks sS = new StudentStarbucks();

		Locations[] slocs = readEntryList();
		Locations[] slocsCopy = new Locations[slocs.length];

		Random rng = new Random(5);
		// randomly scramble the entryList
		for (int i = 0; i < slocs.length; i++) {
			int j = i + (int) (rng.nextDouble() * (slocs.length - i));
			Locations t = slocs[i];
			slocs[i] = slocs[j];
			slocs[j] = t;
		}
		// Copy the list, so I have a clean copy for accuracy testing
		for (int i = 0; i < slocs.length; i++) {
			slocsCopy[i] = new Locations(slocs[i]);
		}

		// ---------------------------------------
		// Unless your build() method is very slow this time will not be very accurate.
		long startb = System.nanoTime();
		sS.build(slocs);
		long endb = System.nanoTime();
		System.out.println("Building the data structure took: " + (endb - startb) / 1000000.0
				+ " milliseconds (time not very accurate)");
		// ---------------------------------------

		// Quick accuracy test for some known values - Only use if your code is supposed
		// to be perfectly accurate!
		Locations s = sS.getNearest(-76.16304, 39.5106);
		if (!s.address.equals("Maryland House Travel Plaza")) {
			System.out.println("Failed Maryland House Travel Plaza test. Got: " + s.address);
			System.exit(0);
		}
		s = sS.getNearest(-104.8287, 39.70294);
		if (!s.address.equals("I-225 - Alameda-Aurora")) {
			System.out.println("Failed I-225 - Alameda-Aurora test. Got: " + s.address);
			System.exit(0);
		}

		// ---------------------------------------
		// Calcualting the Brute Force Solution on your machine
		// for comparison
		long start = 0;
		long end = 0;
		int numTrials = 1000;
		do {
			start = System.nanoTime();
			for (int i = 0; i < numTrials; i++) {
				double x = -125.0 + 73.0 * rng.nextDouble();
				double y = 24.0 + 25.0 * rng.nextDouble();
				Locations tmp = bruteGetNearest(x, y, slocsCopy);
			}
			end = System.nanoTime();
			numTrials *= 10;
		} while ((end - start) / 1000000.0 < 5000 && numTrials < 1000000);
		double bruteForce = (((end - start) / 1000000.0) / numTrials);
		// -------------------------------------	
		// TEST FOR SPEED
		start = 0;
		end = 0;
		numTrials = 1000;
		do {
			start = System.nanoTime();
			for (int i = 0; i < numTrials; i++) {
				double x = -125.0 + 73.0 * rng.nextDouble();
				double y = 24.0 + 25.0 * rng.nextDouble();
				Locations tmp = sS.getNearest(x, y);
			}
			end = System.nanoTime();
			numTrials *= 10;
		} while ((end - start) / 1000000.0 < 5000 && numTrials < 1000000);
		System.out.println("** Timing of your Solution **");
		double studentSpeed = (((end - start) / 1000000.0) / numTrials);
		System.out.println(
				"Time: " + studentSpeed + " ms per search, " + numTrials + " trials");
		double howManyTimesFaster = bruteForce / studentSpeed;
		System.out.println("Student solution is about " + howManyTimesFaster + " times faster than brute-force.");
	
		// ---------------------------------------
		/*
		 * TEST FOR ACCURACY from 1000 different locations
		 * Use brute-force for comparison
		 */
		double studentTotal = 0.0;
		double optTotal = 0.0;
		numTrials = 1000;
		for (int i = 0; i < numTrials; i++) {
			double x = -125.0 + 73.0 * rng.nextDouble();
			double y = 24.0 + 25.0 * rng.nextDouble();
			Locations student = sS.getNearest(x, y);
			Locations opt = bruteGetNearest(x, y, slocsCopy);
			studentTotal += Starbucks.distance(student.lng, student.lat, x, y);
			optTotal += Starbucks.distance(opt.lng, opt.lat, x, y);
		}
		/* 
		// Calculating Percise Error
		double error = studentTotal / optTotal;
		System.out.println("Error percentage is: " + 100.0 * (error - 1.0));
		*/
		// Calculating Rounded Error
		double error = studentTotal / optTotal;
		int errorPercent = (int) Math.round(100.0 * (error - 1.0));
		System.out.println("Error percentage is: " + errorPercent + "%");
	}
}
