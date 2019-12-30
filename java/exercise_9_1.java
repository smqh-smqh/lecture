public class exercise_9_1 {
    static class Rectangle{
        double width=1;
        double height=1;
        Rectangle(){}
        Rectangle(double w,double h){
            width=w;
            height=h;
        }
        public double getArea(){
            return width*height;
        }
        public double getPerimeter(){
            return 2*(width+height);
        }

        public double getHeight() {
            return height;
        }

        public double getWidth() {
            return width;
        }
    }
    public static void main(String[] args){
        Rectangle r1=new Rectangle(4,40);
        Rectangle r2=new Rectangle(3.5,35.9);
        System.out.println("r1 width: "+r1.getWidth());
        System.out.println("r1 height: "+r1.getHeight());
        System.out.println("r1 area: "+r1.getArea());
        System.out.println("r1 perimeter: "+r1.getPerimeter());
        System.out.println("r2 width: "+r2.getWidth());
        System.out.println("r2 height: "+r2.getHeight());
        System.out.println("r2 area: "+String.format("%.2f", r2.getArea()));
        System.out.println("r2 perimeter: "+r2.getPerimeter());
    }
}
