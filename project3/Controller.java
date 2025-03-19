import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Meisam Amjad
 * @Date : 2025
 *       Sources : All code is original
 *       Purpose : The purpose of this file is to do some VERY rudimentary
 *       timing of your increaseCount
 *       method. For the QUALITY measures I am also going to test getCount and
 *       getNthPopulat,
 *       so you might want to design some way to test out the running time of
 *       your getCount as well!
 */
public class Controller {

	static Random rng; // To generate random values with

	/*
	 * The main() method is only used to call processFile with progressively
	 * larger files. Larger files provide a more accurate measure of
	 * addToTrends' performance, but at a certain point, getNthPopular may
	 * become too slow to wait for completion.
	 */
	public static void main(String[] args) {

		/*
		 * These files are books from project Gutenberg. I have provided the
		 * inputs, as well as my outputs in the starter files
		 */

		/*
		 * NOTE: You may want to comment some of these out!
		 * Unless your program is very speedy on all operations some
		 * of these will never finish.
		 */
		rng = new Random(42);

		timeTests("28885.txt");
		// timeTests("46.txt");
		// timeTests("23684.txt");
		// timeTests("4300.txt");
		// timeTests("1342.txt");
		// timeTests("3090.txt");
		// timeTests("6130.txt");

	}

	/**
	 * <p>
	 * This method reads all the words inside a file, get's rid of
	 * all non-alphanumeric/whitespace characters, and returns an ArrayList
	 * of all single words.
	 * </p>
	 * 
	 * @param fileName a file to read from
	 * @return ArrayList<String> holding all single words
	 */
	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> wordList = new ArrayList<String>();
		String line;
		try {
			// Decided to use [FileReader, BufferReader]
			// instead of [File, Scanner] because they
			// are more efficient for reading big files
			FileReader fileReader = new FileReader(fileName);
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				while ((line = bufferedReader.readLine()) != null) {
					// Remove all non-alphanumeric/whitespace characters
					line = line.replaceAll("[^A-Za-z0-9 ]", "");
					for (String word : line.split(" "))
						if (!word.isEmpty())
							wordList.add(word);
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Unable to open file '" + fileName + "'");
			System.exit(1);
		} catch (IOException ex) {
			System.err.println("Error reading file '" + fileName + "'");
			System.exit(1);
		}
		return wordList;
	}

	/**
	 * <p>
	 * Find the total time to insert the words into a list.
	 * </p>
	 * 
	 * @param wordList
	 * @return average time per insert
	 */
	public static void timeTests(String file) {
		ArrayList<String> wordList = readFile(file);

		// Average time for insert
		long startTime = System.nanoTime();
		Trends tr = new StudentTrends();
		for (String w : wordList) {
			tr.increaseCount(w, 1);
		}
		long endTime = System.nanoTime();
		double test1 = (double) ((endTime - startTime)
				/ 1000000.0) / (double) wordList.size();
		System.out.printf("Test1:\t%s\n:", "Average time for insert");
		System.out.printf("%.4f %s\n", test1, "milliseconds / insert");

		// Average time for getCount
		startTime = System.nanoTime();
		for (String w : wordList) {
			tr.getCount(w);
		}
		endTime = System.nanoTime();
		double test2 = (double) ((endTime - startTime)
				/ 1000000.0) / (double) wordList.size();
		System.out.printf("Test2:\t%s\n:", "Average time for getCount");
		System.out.printf("%.4f %s\n", test2, " milliseconds / getCount");

		// Average time for getNthPopular();
		int n = tr.numEntries();
		startTime = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tr.getNthPopular(i);
		}
		endTime = System.nanoTime();
		double test3 = (double) ((endTime - startTime)
				/ 1000000.0) / (double) n;
		System.out.printf("Test3:\t%s\n:", "Average time for getNthPopular()");
		System.out.printf("%.4f %s\n", test3, " milliseconds / getNthPopular");

		// Average time per operation when mixing operations
		tr = new StudentTrends();
		startTime = System.nanoTime();
		n = 0;
		for (int i = 0; i < wordList.size(); i++) {
			tr.increaseCount(wordList.get(i), 1);
			if (rng.nextDouble() < 0.2)
				n = tr.numEntries();
			if (rng.nextDouble() < 0.2) {
				tr.getNthPopular(rng.nextInt(Math.max(n, 1)));
			}
		}
		endTime = System.nanoTime();
		double test4 = (endTime - startTime) / 1000000000.0;
		System.out.printf("Test4:\t%s\n:", "Average time per operation"
				+ "when mixing operations");
		System.out.printf("%.4f %s\n", test4, " seconds (total)");
	}

}
