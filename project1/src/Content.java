import java.util.ArrayList;

public class Content {
  private String content;

  // Content(String) constructor
  public Content(String str) {
    if (str == null) {
      throw new NullPointerException();
    }
    content = new String(str);
    
  }

  // Content copy constructor
  public Content(Content original) {
    this(original.getContent());

  } 

  // getContent getter method
  public String getContent() {
    return content;
  }

  // checks if key is in content string
  public boolean contains(String key) {
    if (key == null) {
      return false;
    } else {
      return getName().contains(key);
    }
  }

  // probably will need fixing
  public int[] indexOf(String key) {
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    for (int i = 0; i < getContent().length; i++) {
      if (getContent().toLowerCase().charAt(i) == key) {
        indexes.add(i);
      }
    }
    return indexes.toArray();
  }

  public Object clone() {
    return new content(this);
  }

  public String toString() {
    return String.format("Content %s%n", content);
  }
}
