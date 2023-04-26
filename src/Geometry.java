import java.io.PrintWriter;

public class Geometry {

    // Полярный угол точки
    public static void solve1(Main.InputReader in, PrintWriter out) {
        double x = in.nextInt();
        double y = in.nextInt();
        if(x>0 && y>0){
            out.println(Math.atan(y/x));
        }
        if((x<0 && y< 0) || (x<0 && y>0)){
            out.println(Math.atan(y/x) + Math.PI);
        }
        if(x>0 && y<0){
            out.println(Math.atan(y/x) + 2*Math.PI);
        }
        if(x == 0 && y > 0){
            out.println(Math.PI/2);
        }
        if(x == 0 && y < 0){
            out.println(3*Math.PI/2);
        }
        if(x > 0 && y == 0){
            out.println(Math.PI*0);
        }
        if(x < 0 && y == 0){
            out.println(Math.PI);
        }
    }


    // Угол между векторами
    public static double rad1(double x, double y){
        if(x>0 && y>0){
            return Math.atan(y/x);
        }
        if((x<0 && y< 0) || (x<0 && y>0)){
            return Math.atan(y/x) + Math.PI;
        }
        if(x>0 && y<0){
            return Math.atan(y/x) + 2*Math.PI;
        }
        if(x == 0 && y > 0){
            return Math.PI/2;
        }
        if(x == 0 && y < 0){
            return 3*Math.PI/2;
        }
        if(x > 0 && y == 0){
            return Math.PI*0;
        }
        if(x < 0 && y == 0){
            return Math.PI;
        }
        return 0;
    }

    public static void solve2(Main.InputReader in, PrintWriter out) {
        double x1 = in.nextInt();
        double y1 = in.nextInt();
        double x2 = in.nextInt();
        double y2 = in.nextInt();
        double r = Math.abs(rad1(x1, y1) - rad1(x2, y2));
        String str = "";
        if(r <= Math.PI) {
            out.println(r);
        } else{
            out.println( Math.PI * 2 - r);
        }
    }


    // Площадь треугольника
    public static double rad2(double x, double y){
        if(x>0 && y>0){
            return Math.atan(y/x);
        }
        if((x<0 && y< 0) || (x<0 && y>0)){
            return Math.atan(y/x) + Math.PI;
        }
        if(x>0 && y<0){
            return Math.atan(y/x) + 2*Math.PI;
        }
        if(x == 0 && y > 0){
            return Math.PI/2;
        }
        if(x == 0 && y < 0){
            return 3*Math.PI/2;
        }
        if(x > 0 && y == 0){
            return Math.PI*0;
        }
        if(x < 0 && y == 0){
            return Math.PI;
        }
        return 0;
    }

    public static double corner(double x1, double x2, double y1, double y2){
        double r = Math.abs(rad2(x1, y1) - rad2(x2, y2));
        if(r <= Math.PI) {
            return r;
        } else{
            return (Math.PI * 2 - r);
        }
    }

    public static void solve3(Main.InputReader in, PrintWriter out) {
        double Xa = in.nextLong();
        double Ya = in.nextLong();
        double Xb = in.nextLong();
        double Yb = in.nextLong();
        double Xc = in.nextLong();
        double Yc = in.nextLong();

        double AB = Math.sqrt((Xb-Xa)*(Xb-Xa) + (Yb-Ya)*(Yb-Ya));
        double AC = Math.sqrt((Xc-Xa)*(Xc-Xa) + (Yc-Ya)*(Yc-Ya));

        double S = 0.5*AB*AC*Math.sin(corner(Xb-Xa, Xc-Xa, Yb-Ya,Yc-Ya));
        out.println(S);
    }


    // Площадь многоугольника
    static void solve4(Main.InputReader in, PrintWriter out) {
        int n = in.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];

        double res = 0;

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            res += x[i]*y[i + 1];
        }
        for (int i = 0; i < n - 1; i++) {
            res -= y[i]*x[i + 1];
        }
        res += x[n - 1]*y[0];
        res -= y[n - 1]*x[0];

        out.print(Math.abs(res/2));
    }

    // Расстояние от точки до прямой
    static void solve5(Main.InputReader in, PrintWriter out) {
        double x = in.nextInt();
        double y = in.nextInt();
        double a = in.nextInt();
        double b = in.nextInt();
        double c = in.nextInt();

        double res = (a*x + b*y + c)/Math.sqrt(a*a + b*b);
        out.print(Math.abs(res));
    }
}
