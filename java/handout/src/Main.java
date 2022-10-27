public class Main {
    public static void main(String[] args) {
        Person p = new Person("孙悟空");
        System.out.println(p);
    }
}

class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }
    public void info() {
        System.out.println("此人名为：" + name);
    }

    @Override
    public String toString() {
        return "[Person name=" + this.name + "]";
    }
}

