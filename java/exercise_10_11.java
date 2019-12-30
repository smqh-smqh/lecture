public class exercise_10_11 {
    static class Circle2D{
        double x,y;
        double radius=1;

        public double getRadius() {
            return radius;
        }
        Circle2D(){
            x=0;
            y=0;
            radius=1;
        }

        Circle2D(double x,double y,double r){
            this.x=x;
            this.y=y;
            radius=r;
        }

        public double getArea() {
            return Math.PI*radius*radius;
        }
        public double getPerimeter() {
            return Math.PI*radius*2;
        }
        public boolean contains(double x,double y){
            double d=Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2));
            return d>radius?false:true;
        }
        public boolean contains(Circle2D c){
            double d=Math.sqrt(Math.pow(c.x-this.x,2)+Math.pow(c.y-this.y,2));
            return d>(radius-c.radius)?false:true;
        }
        public boolean overlaps(Circle2D c){
            double d=Math.sqrt(Math.pow(c.x-this.x,2)+Math.pow(c.y-this.y,2));
            return (d>(radius+c.radius)||(d<(radius-c.radius)))?false:true;
        }
    }
    public static void main(String[] args){
        Circle2D c1=new Circle2D(2,2,5.5);
        System.out.println("c1 area: "+String.format("%.2f", c1.getArea()));
        System.out.println("c1 perimeter: "+String.format("%.2f", c1.getPerimeter()));
        System.out.println("Circle2D(2,2,5.5) contains(3,3)?: "+c1.contains(3,3));
        System.out.println("Circle2D(2,2,5.5) contains Circle2D(4,5,10.5)?: "+c1.contains(new Circle2D(4,5,10.5)));
        System.out.println("Circle2D(2,2,5.5) overlaps Circle2D(3,5,2.3)?: "+c1.overlaps(new Circle2D(3,5,2.3)));
    }
}
