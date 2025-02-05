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
}
