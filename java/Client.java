public class Client {
    public void someMethod(){
        TaskClass task = new TaskClass();

        Thread thread = new Thread(task);

        thread.start();
    }
}
