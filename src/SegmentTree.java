import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SegmentTree {

    // Дерево отрезков

    static class segmentTree{
        long[] tree;
        int size;

        public void init(int n) {
            size = 1;
            while(size < n){size*=2;}
            tree = new long[size*2-1];
        }

        private void set(int i, int v, int x, int lx, int rx){
            if(rx - lx == 1){
                tree[x] = v;
                return;
            }
            int m = (lx+rx)/2;
            if(i < m){
                set(i, v, 2*x+1, lx, m);
            }else{
                set(i, v, 2*x+2, m, rx);
            }
            tree[x] = tree[2*x+1] + tree[2*x+2];
        }
        public void set(int i, int v){
            set(i, v, 0, 0, size);
        }

        public long sum(int l, int r, int x, int lx, int rx){
            if(l >= rx || lx >= r){
                return 0;
            }
            if(lx >= l && rx <= r){
                return tree[x];
            }
            int m = (lx+rx)/2;
            long s1 = sum(l, r, 2*x + 1, lx, m);
            long s2 = sum(l, r, 2*x + 2, m, rx);
            return s1+s2;
        }

        public long sum(int l, int r){
            return sum(l, r, 0, 0, size);
        }
    }


    static class segmentTree1{

        private long[] tree;
        private int size;

        public void init(int n) {
            size = 1;
            while(size < n){size*=2;}
            tree = new long[2*size-1];
        }

        private void add(int l, int r, long v, int x, int lx, int rx){
            if(rx <= l || lx >= r){
                return;
            }
            if(lx >= l && rx <= r){
                tree[x] += v;
                return;
            }
            int m = (lx+rx)/2;
            add(l, r, v, 2*x+1, lx, m);
            add(l, r, v, 2*x+2, m, rx);
        }

        public void add(int l, int r, long v){
            add(l, r, v, 0, 0, size);
        }

        private long get(int i, int x, int lx, int rx){
            if(rx-lx == 1){
                return tree[x];
            }
            int m = (rx+lx)/2;
            if(i < m){
                return get(i, 2*x+1, lx, m)+tree[x];
            }else{
                return get(i, 2*x+2, m, rx)+tree[x];
            }
        }

        public long get(int i){
            return get(i, 0, 0, size);
        }

    }


}
