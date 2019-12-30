public class exercise_11_2 {
    public static void main(String[] args) {
        m(new Faculty("熊猫"));
        m(new Stuff("小米"));
        m(new Employee("小丽"));
        m(new Student("小红"));
        m(new Person("小芳"));
    }
    public static void m(Object x) {
        System.out.println(x.toString());
    }

    static class Stuff extends Employee {
        Stuff(String name){super(name);}
        String position;
        public String toString() {
            return "Stuff: "+name;
        }
    }

    static class Faculty extends Employee {
        int leval;
        String worktime;
        Faculty(String name){super(name);}
        public String toString() {
            return "Faculty: "+name;
        }
    }

    static class Employee extends Person {
        float salary;
        String office;
        exercise_10_14.MyDate date;
        Employee(String name){super(name);}
        public String toString() {
            return "Employee: "+name;
        }
    }

    static class Student extends Person {
    int grade;
    Student(String name){
        super(name);
    }
    public String toString() {
        return "Student: "+name;
    }
}
    static class Person{
    String name;
    String addr;
    String phonenum;
    String email;
    Person(String name){this.name=name;}
    public String toString() {
        return "Person: "+name;
    }
    }
}
