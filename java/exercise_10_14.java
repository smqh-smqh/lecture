import java.util.GregorianCalendar;

public class exercise_10_14 {
    static class MyDate{
        int year,month,day;
        GregorianCalendar g=new GregorianCalendar();
        MyDate(){
            year=g.get(GregorianCalendar.YEAR);
            month=g.get(GregorianCalendar.MONTH);
            day=g.get(GregorianCalendar.DAY_OF_MONTH);
        }
        MyDate(long stime){
            g.setTimeInMillis(stime);
            year=g.get(GregorianCalendar.YEAR);
            month=g.get(GregorianCalendar.MONTH);
            day=g.get(GregorianCalendar.DAY_OF_MONTH);
        }
        MyDate(int year,int month,int day){
            this.year=year;
            this.month=month;
            this.day=day;
        }

        public int getYear() {
            return year;
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public void setDate(long elapsedTime) {
            new MyDate(elapsedTime);
        }
    }
    public static void main(String[] args){
        MyDate date1=new MyDate();
        System.out.println("当前时间为: "+date1.getYear()+"年"+date1.getMonth()+"月"+date1.getDay()+"日");
        MyDate date2=new MyDate(34355555133101L);
        System.out.println("设置时间为: "+date2.getYear()+"年"+date2.getMonth()+"月"+date2.getDay()+"日");
    }
}
