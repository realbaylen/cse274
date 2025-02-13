import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JokeTester {
  @Test
  public void testEquals() {
    Joke joke1 = new Joke("Funny", "a joke");
    Joke joke2 = new Joke("Funny", "a joke");
    Joke joke3 = new Joke("Funny", "a new joke");
    assertTrue(joke1.equals(joke2));
    assertFalse(joke1.equals(joke3));
  }
}