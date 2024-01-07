import java.util.*;
import java.io.*;

public class _1500_A {
    static final int MAXC = 2500005;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        ArrayList<Pair>[] pairs = new ArrayList[MAXC * 2];
        for(int i = 0; i < pairs.length; i++) pairs[i] = new ArrayList<Pair>();
        boolean possible = false;
        int[] res = null;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                Pair cur = new Pair(i, j);
                int sum = a[i] + a[j];
                ArrayList<Pair> eq = pairs[sum];
                for(Pair p : eq) {
                    if(cur.a != p.a && cur.a != p.b && cur.b != p.a && cur.b != p.b) {
                        possible = true;
                        res = new int[] { cur.a + 1, cur.b + 1, p.a + 1, p.b + 1 };
                        break;
                    }
                }
                eq.add(cur);
                if(possible) break;
                else {
                    if(eq.size() >= 4) {
                        possible = true;
                        Pair w = eq.get(0);
                        Pair x = eq.get(1);
                        Pair y = eq.get(2);
                        Pair z = eq.get(3);
                        if (w.a == x.a) {
                            res = new int[] { w.b + 1, x.b + 1, y.b + 1, z.b + 1 };
                        } else {
                            res = new int[] { w.a + 1, x.a + 1, y.a + 1, z.a + 1 };
                        }
                        break;
                    }
                }
            }
            if(possible) break;
        }
        if(possible) {
            out.println("YES");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 4; i++) {
                sb.append(res[i]);
                if(i < 3) sb.append(' ');
            }
            out.println(sb.toString());
        }else {
            out.println("NO");
        }
        in.close();
        out.close();
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}