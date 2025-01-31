public class Child extends Parent {
  private int age;
  void Child(String name, int age) {
    name = "";
    age = 0;
  }
  public void displayInfo() {
    System.out.printf("Name: %s", name);
    System.out.printf("Age: %s", age);
  }
}
