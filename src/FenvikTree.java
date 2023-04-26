import java.io.*;
import java.util.StringTokenizer;

public class FenvikTree {


    public static long fenvSum(long[] fenv, int idx){
        int start = idx&(idx+1);
        long res = fenv[idx];
        if(start != 0){
            res += fenvSum(fenv, start-1);
        }
        return res;
    }

    static void solve(Main.InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] pref = new long[n];
        int[] a = new int[n];
        a[0] = in.nextInt();
        pref[0] = a[0];
        for(int i = 1; i < n; i++){
            a[i] = in.nextInt();
            pref[i] = pref[i-1] + a[i];
        }

        long[] fenv = new long[n];
        fenv[0] = pref[0];
        for(int i = 1; i < n; i++){
            fenv[i] = (i & (i + 1)) == 0 ? pref[i] : pref[i] - pref[(i & (i + 1)) - 1];
        }

        for(int q = 0; q < m; q++){
            int t = in.nextInt();
            int x = in.nextInt()-1;
            int y = in.nextInt();

            if(t == 0){
                y--;
                if(x == 0){
                    out.println(fenvSum(fenv, y));
                }else{
                    out.println(fenvSum(fenv, y) - fenvSum(fenv, x-1));
                }
            }else{
                int s = y - a[x];
                a[x] = y;
                int idx = x;
                while(idx < n){
                    fenv[idx] += s;
                    idx = idx|(idx+1);
                }
            }

        }
    }
}
