import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Assert.*;

public class ContentTester {
  @Test
  public void testContentConstructor() {
      String expectedContent = "Hello";
      Content content = new Content(expectedContent);
      assertEquals(expectedContent, content.getContent());
    }

  @Test
  public void testContains() {    
    Content content = new Content("Hello World");
    assertTrue(content.contains("World"));
    assertFalse(content.contains("world"));
  }

  
  @Test
  public void testIndexOf() {
      Content content = new Content("Hello Hello");
      int[] indices = content.indexOf("lo");
      assertArrayEquals(new int[]{3, 9}, indices);
  }
}
