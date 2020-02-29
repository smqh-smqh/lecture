import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;

class User{
    String name;
    int age;

    public User(String name, int i) {
        this.age=i;
        this.name=name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args){
        User z3=new User("z3",22);
        User li4=new User("li4",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t "+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t "+atomicReference.get().toString());
    }
}
