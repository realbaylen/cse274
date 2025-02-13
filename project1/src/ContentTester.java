import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

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
  @Test
  public void testClone() {
    Content original = new Content("Clone");
    Content cloned = (Content) original.clone();
    assertEquals(original.getContent(), cloned.getContent());
    assertNotSame(original, cloned);
  }

  @Test
    public void testCopyConstructor() {
    Content original = new Content("Copy");
    Content copy = new Content(original);
    assertEquals(original.getContent(), copy.getContent());
  }
}
