import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Joke class.
 */
public class JokeTester {
  @Test
  public void testEquals() {
    Joke joke1 = new Joke("Funny", "a joke");
    Joke joke2 = new Joke("Funny", "a joke");
    Joke joke3 = new Joke("Funny", "a new joke");
    assertTrue(joke1.equals(joke2));
    assertFalse(joke1.equals(joke3));
  }

  @Test
  public void testCompareTo() {
    Joke joke1 = new Joke("Funny", "a joke");
    Joke joke2 = new Joke("Funny", "a joke");
    assertEquals(0, joke1.compareTo(joke2));
    Joke joke3 = new Joke("Funny", "a joke");
    Joke joke4 = new Joke("Funny", "a longer joke");
    assertTrue(joke3.compareTo(joke4) < 0);
  }

  @Test
  public void testGetBytes() {
    Joke joke = new Joke("Funny", "a joke");
    byte[] bytes = joke.getBytes(10);
    assertEquals(500, bytes.length);
    assertEquals("Funny".getBytes()[0], bytes[0]);
    assertEquals("a joke".getBytes()[0], bytes[5]);
  }

  @Test
  public void testClone() {
    Joke joke = new Joke("Funny", "a joke");
    Joke clone = (Joke) joke.clone();
    assertEquals(joke.getContent(), clone.getContent());
    assertEquals(joke.getCategory(), clone.getCategory());
  }
}