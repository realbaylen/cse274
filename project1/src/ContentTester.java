import org.junit.Test;
import org.junit.Assert.*;

public class ContentTester {
  @Test
  public ContentTester() {
    String expectedContent = "Hello";
    Content content = new Content(expectedContent);

    assertEquals(expectedContent, content.getContent());
  }

  @Test
  public void testContains() {
    Content content = new Content("Hi");
    assertTrue(content.contains("World"), "Content should contain 'World'.");
    assertFalse(content.contains("world"), "Content should not contain 'world' (case-sensitive).");
  }

}
