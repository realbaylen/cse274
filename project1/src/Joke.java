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
      return this.getContent().substring(0, 10).compareTo(other.getContent().substring(0, 10));
    }
    return Integer.compare(this.getContent().length(),other.getContent().length());

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Joke)) return false;
    return this.compareTo((Joke) obj) == 0;
  }

  @Override
  public byte[] getBytes(int byteSize) {
    byteSize = Math.max(byteSize, 500);
    byte[] result = new byte[byteSize];
    byte[] categoryBytes = category.getBytes();
    byte[] contentBytes = this.getContent().getBytes();
    int categoryLength = Math.min(categoryBytes.length, 15);
    System.arraycopy(categoryBytes, 0, result, 0, categoryLength);
    int contentLength = Math.min(contentBytes.length, byteSize - 15);
    System.arraycopy(contentBytes, 0, result, 15, contentLength);
    return result;
  }

  @Override
  public Object clone() {
    return new Joke(this.category, this.getContent());
  }

  @Override
  public String toString() {
    return String.format("Category: %s%nJoke: %s%n", category, getContent().substring(0, Math.min(10, getContent().length())));
  }
}
