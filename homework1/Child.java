public class Child extends Parent {
  private int age;
  // cc onstructor
  public Child(String name, int age) {
    super(name);
    this.age = age;
  }
  // Display attributes of Child object
  public void displayInfo() {
    System.out.printf("Name: %s%n", name);
    System.out.printf("Age: %d%n", age);
  }
}
