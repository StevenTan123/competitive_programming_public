import java.util.*;
import java.io.*;

public class _1515_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int[] h = new int[n];
            ArrayList<Integer>[] piles = new ArrayList[m];
            for(int i = 0; i < m; i++) piles[i] = new ArrayList<Integer>();
            int[] sum = new int[m];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(line.nextToken());
                if(i < m) {
                    piles[i].add(i);
                    sum[i] += h[i];
                }else {
                    piles[0].add(i);
                    sum[0] += h[i];
                }
            }
            if(m == 1) {
                out.println("YES");
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    sb.append(1);
                    if(i < n - 1) sb.append(' ');
                }
                out.println(sb.toString());
                continue;
            }
            TreeSet<Pair> sorted = new TreeSet<Pair>();
            for(int i = 0; i < m; i++) {
                sorted.add(new Pair(sum[i], i));
            }
            while(true) {
                Pair top = sorted.pollFirst();
                Pair bottom = sorted.pollLast();
                if(top.a - bottom.a > x) {
                    ArrayList<Integer> toppile = piles[top.b];
                    ArrayList<Integer> botpile = piles[bottom.b];
                    int remove = toppile.get(toppile.size() - 1);
                    top.a -= h[remove];
                    bottom.a += h[remove];
                    botpile.add(remove);
                    toppile.remove(toppile.size() - 1);
                    sorted.add(top);
                    sorted.add(bottom);
                }else {
                    break;
                }
            }
            int[] res = new int[n];
            int counter = 0;
            for(ArrayList<Integer> pile : piles) {
                for(int ind : pile) {
                    res[ind] = counter;
                }
                counter++;
            }
            out.println("YES");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(res[i] + 1);
                if(i < n - 1) sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static class Pair implements Comparable<Pair> {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
        @Override
        public int compareTo(Pair o) {
            if(a == o.a) {
                return b - o.b;
            }
            return o.a - a;
        }
    }
}
