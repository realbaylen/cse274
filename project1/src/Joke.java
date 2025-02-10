public class Joke extends Content implements Convertable, Comparable<Joke> {
  private String category;
  public Joke(String category, String content) {
    super(content);
    if (category==null) {
      throw new NullPointerException();
    }
    this.category = new String(category);
  }

  @Override
  public int compareTo(joke other) {
    if (this.getContent().length() == other.getContent().length()) {
      return this.getContent().substring(0, 10).compareTo(other.getContent().substring(0, 10))
    }
    return Integer.compare(this.getContent().length(),other.getContent().length());

  }
}
