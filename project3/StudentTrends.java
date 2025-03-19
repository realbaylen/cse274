import java.util.ArrayList;

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
    // check for negative amount
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
    // check if the word is not in the list
    if (!words.contains(s)) {
      return 0;
    }
    return cnts.get(words.indexOf(s));
  }

  public String getNthPopular(int n) {
    // check for invalid value of n
    if (n < 0 || n >= words.size()) {
      return null;
    }
    // create a copy of the words list
    ArrayList<String> sortedWords = new ArrayList<>(words);

    for (int i = 0; i < sortedWords.size() - 1; i++) {
      int maxIndex = i;
      for (int j = i + 1; j < sortedWords.size(); j++) {
        String word1 = sortedWords.get(j);
        String word2 = sortedWords.get(maxIndex);
        int count1 = getCount(word1);
        int count2 = getCount(word2);

        // compare by count (higher count comes first)
        if (count1 > count2 || (count1 == count2 && word1.compareTo(word2) < 0)) {
          maxIndex = j;
        }
      }
      // swap the words
      if (maxIndex != i) {
        String temp = sortedWords.get(i);
        sortedWords.set(i, sortedWords.get(maxIndex));
        sortedWords.set(maxIndex, temp);
      }
    }
    return sortedWords.get(n);
  }

  public int numEntries() {
    return words.size();
  }
}
