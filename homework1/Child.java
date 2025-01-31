public class Child extends Parent {
  private int age;
  Child(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public void displayInfo() {
    System.out.printf("Name: %s%n", name);
    System.out.printf("Age: %s%n", age);
  }
}
