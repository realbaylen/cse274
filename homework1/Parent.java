//package cse274.homework1;

public class Parent {
  protected String name;
  void Parent(String name) {
    name = this.name;
  }
  public void displayInfo() {
    System.out.printf("Name: %s%n", name);
  }
}


