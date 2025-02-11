import java.util.ArrayList;

public class Content {
  private String content;

  // Content(String) constructor
  public Content(String str) {
    if (str == null) {
      throw new NullPointerException(); } content = new String(str);
    
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
    if (key == null) return false;
    return content.contains(key);
  }

  // probably will need fixing
  public int[] indexOf(String key) {
    if (key == null || key.isEmpty()) {
      return new int[0];
    }

    ArrayList<Integer> indexes = new ArrayList<>();
    int index = content.indexOf(key);
    while (index != -1) {
      indexes.add(index);
      index = content.indexOf(key, index + 1);
    }

    int[] result = new int[indexes.size()];
    for (int i = 0; i < indexes.size(); i++) {
      result[i] = indexes.get(i);
    }

    return result;
  }

  public Object clone() {
    return new Content(this);
  }

  public String toString() {
    return String.format("Content %s%n", content);
  }
}
