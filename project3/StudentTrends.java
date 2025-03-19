import java.util.ArrayList;
import java.util.LinkedList;
// Do not import any extra data structures

/**
 * @author Baylen Romanello
 */
public class StudentTrends implements Trends {

  // TODO: Add code
  private ArrayList<String> words;
  private ArrayList<Integer> cnts;

  public StudentTrends() {
    words = new ArrayList<String>();
    cnts = new ArrayList<Integer>();
  }

  public void increaseCount(String s, int amount) {
      if (amount < 0) {
        throw new IllegalArgumentException();
      }
      if (words.contains(s)) {
        cnts.set(words.indexOf(s), cnts.get(words.indexOf(s)) + amount);
      } else {
        words.add(s);
        cnts.add(amount);
      }
    }

  public int getCount(String s) {
    if (!words.contains(s)) {
      return 0;
    }
    return cnts.get(words.indexOf(s));
  }

  public String getNthPopular(int n) {
    if (n < 0 || n >= words.size()) {
      return null;
    }

  }

  public int numEntries() {
    if (cnts.size() == 0) {
      return 0;
    }
    int total = 0;
    for (int i = 0; i < cnts.size(); i++) {
      total += cnts.get(i);
    }
    return total;
  }

}
