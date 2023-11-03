import java.util.Random;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        System.out.println(100);
        Random rnd = new Random();
        for(int i = 0; i < 100; i++){
            int n = rnd.nextInt(20)+1;
            int[] a = new int[n];
            int[] b = new int[n];
            for(int j = 0; j < n; j++){
                a[j] = rnd.nextInt(20);
            }
            for(int j = 0; j < n; j++){
                b[j] = rnd.nextInt(20);
            }

            System.out.println(n);
            for(int j : a){
                System.out.print(j + " ");
            }
            System.out.println();
            for(int j : b){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
