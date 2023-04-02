import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SegmentTree {

    // Дерево отрезков

    public static class segmentTree{

        List<Long> tree = new ArrayList<>();
        int size;

        public void init(int n){
            size = 1;
            while(size < n){
                size *= 2;
            }
            for(int i = 0; i < 2*size - 1; i++){
                tree.add((long) 0);
            }
        }

        public void set(int i, int v, int x, int lx, int rx){
            if(rx - lx == 1) {
                tree.set(x, (long) v);
                return;
            }
            int m = (lx + rx) / 2;
            if(i < m){
                set(i, v, 2*x + 1, lx, m);
            } else{
                set(i, v, 2*x + 2, m, rx);
            }
            tree.set(x, tree.get(2*x + 1) + tree.get(2*x +  2));
        }

        public void set(int i, int v){
            set(i, v, 0, 0, size);
        }

        public void setMin(int i, int v, int x, int lx, int rx){
            if(rx - lx == 1) {
                tree.set(x, (long) v);
                return;
            }
            int m = (lx + rx) / 2;
            if(i < m){
                setMin(i, v, 2*x + 1, lx, m);
            } else{
                setMin(i, v, 2*x + 2, m, rx);
            }
            tree.set(x, Math.min(tree.get(2*x + 1), tree.get(2*x +  2)));
        }

        public void setMin(int i, int v){
            setMin(i, v, 0, 0, size);
        }

        public void setMax(int i, int v, int x, int lx, int rx){
            if(rx - lx == 1) {
                tree.set(x, (long) v);
                return;
            }
            int m = (lx + rx) / 2;
            if(i < m){
                setMax(i, v, 2*x + 1, lx, m);
            } else{
                setMax(i, v, 2*x + 2, m, rx);
            }
            tree.set(x, Math.max(tree.get(2*x + 1), tree.get(2*x +  2)));
        }

        public void setMax(int i, int v){
            setMax(i, v, 0, 0, size);
        }

        public long sum(int l, int r, int x, int lx, int rx){
            if(l >= rx || lx >= r){
                return 0;
            }
            if(lx >= l && rx <= r){
                return tree.get(x);
            }
            int m = (lx + rx) / 2;
            long s1 = sum(l, r, 2*x + 1, lx, m);
            long s2 = sum(l, r, 2*x + 2, m, rx);
            return s1+s2;
        }

        public long sum(int l, int r){
            return sum(l, r, 0, 0, size);
        }

        public long max(int l, int r, int x, int lx, int rx){
            if(l >= rx || lx >= r){
                return -Integer.MAX_VALUE;
            }
            if(lx >= l && rx <= r){
                return tree.get(x);
            }
            int m = (lx + rx) / 2;
            long s1 = max(l, r, 2*x + 1, lx, m);
            long s2 = max(l, r, 2*x + 2, m, rx);
            return Math.max(s1,s2);
        }

        public long max(int l, int r){
            return max(l, r, 0, 0, size);
        }

        public long min(int l, int r, int x, int lx, int rx){
            if(l >= rx || lx >= r){
                return Integer.MAX_VALUE;
            }
            if(lx >= l && rx <= r){
                return tree.get(x);
            }
            int m = (lx + rx) / 2;
            long s1 = min(l, r, 2*x + 1, lx, m);
            long s2 = min(l, r, 2*x + 2, m, rx);
            return Math.min(s1,s2);
        }

        public long min(int l, int r){
            return min(l, r, 0, 0, size);
        }
    }

}
