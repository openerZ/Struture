import java.util.Arrays;

public class Person implements Comparable<Person>{

    private int salay ;
    private String name;

    public Person(int salay, String name) {
        this.salay = salay;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return o.salay-this.salay;
    }

    @Override
    public String toString() {
        return "Person{" +
                "salay=" + salay +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person lili = new Person(10000, "lili");
        Person tom = new Person(12000, "Tom");
        Person lack = new Person(15000, "Lack");
        Person[] a = new Person[3];
        a[2]=tom;
        a[1] =lili;
        a[0]=lack;
        for (Person person : a) {
            System.out.println(person);
        }
        System.out.println("——————————————————————————————");
        Arrays.sort(a);
        for (Person person : a) {
            System.out.println(person);
        }
    }
}
