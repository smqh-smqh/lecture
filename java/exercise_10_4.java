public class exercise_10_4 {
    static class MyPoint{
        float x,y;
        MyPoint()
        {
            x=0;
            y=0;
        }
        MyPoint(float x,float y)
        {
            this.x=x;
            this.y=y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
        public double distance(MyPoint p){
            return Math.sqrt(Math.pow(x-p.x,2)+Math.pow(y-p.y,2));
        }
        public double distance(int a,int b){
            return Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2));
        }
    }
    public static void main(String[] args){
        MyPoint p1=new MyPoint();
        MyPoint p2=new MyPoint(10, (float) 30.5);
        System.out.println("the distance of the point and (0,0) is: "+String.format("%.2f",p2.distance(p1)));
        System.out.println("the distance of the point and (1,2) is: "+String.format("%.2f",p2.distance(1,2)));
    }
}
