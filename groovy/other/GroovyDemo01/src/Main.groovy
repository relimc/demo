public class Person1 {
  private String name;

  public Person1(String name) {
    this.name = name
  }

  public String getName() {
    return name
  }

  public void setName(String name) {
    this.name = name
  }
}

class Person2 {
  String name
}

def person = new Person2(name: "John")
println person.name









