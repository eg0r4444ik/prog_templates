import java.io.IOException;
import java.io.PrintWriter;

public class LCA {
    public static void solve(Main.InputReader in, PrintWriter out) throws IOException {
        int[][] up = new int[500001][31];
        int[] dist = new int[500001];
        dist[1] = 0;
        for(int i = 0; i <= 30; i++){
            up[1][i] = 0;
        }
        int k = in.nextInt();
        for(int i = 0; i < k; i++){
            String command = in.next();
            if(command.equals("ADD")){
                int a = in.nextInt();
                int b = in.nextInt();
                dist[b] = dist[a]+1;
                up[b][0] = a;
                for(int j = 1; j <= 30; j++){
                    up[b][j] = up[up[b][j-1]][j-1];
                }
            }else{
                int a = in.nextInt();
                int b = in.nextInt();
                if (dist[a] < dist[b]){
                    int tmp = a;
                    a = b;
                    b = tmp;
                }

                for (int j = 30; j >= 0; j--){
                    if (dist[up[a][j]] >= dist[b] && up[a][j] != 0){
                        a = up[a][j];
                    }
                }
                if(a == b){
                    out.println(b);
                }else {
                    for (int j = 30; j >= 0; j--) {
                        if (up[a][j] == up[b][j]) {
                            continue;
                        } else {
                            a = up[a][j];
                            b = up[b][j];
                        }
                    }
                    out.println(up[a][0]);
                }
            }
        }
    }

}
