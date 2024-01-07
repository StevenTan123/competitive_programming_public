import java.util.*;
import java.io.*;

public class _1530_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            HashSet<Integer> unique = new HashSet<Integer>();
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
                unique.add(a[i]);
            }
            HashSet<Integer> unused = new HashSet<Integer>();
            for(int i = 0; i < n; i++) {
                if(!unique.contains(i)) {
                    unused.add(i);
                }
            }
            out.println(unique.size());
            int[] b = new int[n];
            if(unique.size() == n) {
                b = a;
            }else if(unique.size() == n - 1) {
                int notused = 0;
                for(int val : unused) {
                    notused = val;
                }
                HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
                int i1 = 0;
                int i2 = 0;
                for(int i = 0; i < n; i++) {
                    if(seen.containsKey(a[i])) {
                        i1 = seen.get(a[i]);
                        i2 = i;
                        break;
                    }else {
                        seen.put(a[i], i);
                    }
                }
                b = a;
                if(i1 != notused) {
                    b[i1] = notused;
                }else {
                    b[i2] = notused;
                }
            }else {
                HashSet<Integer> seen = new HashSet<Integer>();
                int[] indices = new int[unused.size()];
                int p = 0;
                for(int i = 0; i < n; i++) {
                    if(seen.contains(a[i])) {
                        b[i] = -1;
                        indices[p] = i;
                        p++;
                    }else {
                        seen.add(a[i]);
                        b[i] = a[i];
                    }
                }
                boolean[] filled = new boolean[unused.size()];
                for(int i = 0; i < indices.length; i++) {
                    int prev = indices[(i == 0 ? indices.length - 1 : i - 1)];
                    if(unused.contains(prev)) {
                        filled[i] = true;
                        b[indices[i]] = prev;
                        unused.remove(prev);
                    }
                }
                ArrayList<Integer> left = new ArrayList<Integer>(unused);
                p = 0;
                for(int i = 0; i < filled.length; i++) {
                    if(!filled[i]) {
                        b[indices[i]] = left.get(p);
                        p++;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < a.length; i++) {
                sb.append((b[i] + 1));
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
