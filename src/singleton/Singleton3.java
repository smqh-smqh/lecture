package singleton;

import java.io.IOException;
import java.util.Properties;

public class Singleton3 {
    public static final Singleton3 INGLETON;
    static {
        Properties pro=new Properties();
        try {
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        INGLETON=new Singleton3(pro.getProperty("info"));
    }

    @Override
    public String toString() {
        return "Singleton3{"+info+"}";
    }

    private final String info;

    public String getInfo() {
        return info;
    }

    private Singleton3(String info){
        this.info=info;
    }
}
