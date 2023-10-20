import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.SynchronousQueue;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
//        inputStream = new FileInputStream(new File("lca_rmq.in"));
//        outputStream = new FileOutputStream(new File("lca_rmq.out"));
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = 1;
        t = in.nextInt();
        for(int i = 0; i < t; i++) {
            solve(in, out);
        }
        out.close();
    }

    static class Node{
        int val;
        boolean isVisited;
        List<Node> neighbours = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }

    static class Pair implements Comparable<Pair>{
        long p1, p2;

        public Pair(long p1, long p2) {
            this.p1 = p1;
            this.p2 = p2;
        }


        @Override
        public int compareTo(Pair o) {
            if(this.p1 == o.p1) {
                return (int)(this.p2 - o.p2);
            }
            return (int)(this.p2-o.p2);
        }
    }

    static void solve(InputReader in, PrintWriter out) {
        
    }

    static void dfs(Node curr){
        curr.isVisited = true;
        for(Node node : curr.neighbours){
            if(!node.isVisited){
                dfs(node);
            }
        }
    }

    static long binarySearch(long[] a){
        long l = 0;
        long r = Long.MAX_VALUE;
        while(r > l+1){
            long m = (l+r)/2;
            if(check(a, m)){
                r = m;
            }else{
                l = m;
            }
        }

        return l;
    }

    static boolean check(long[] a, long m){
        return false;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}