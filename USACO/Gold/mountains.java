import java.util.*;
import java.io.*;

public class mountains {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int N = Integer.parseInt(in.readLine());
        int[] h = new int[N];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(line.nextToken());
        }
        TreeSet<Frac>[] visible = new TreeSet[N];
        for (int i = 0; i < N; i++) {
            visible[i] = genVisible(h, i);
        }
        int Q = Integer.parseInt(in.readLine());
        for (int i = 0; i < Q; i++) {
            line = new StringTokenizer(in.readLine());
            int ind = Integer.parseInt(line.nextToken()) - 1;
            h[ind] += Integer.parseInt(line.nextToken());
            visible[ind] = genVisible(h, ind);
            for (int j = 0; j < ind; j++) {
                Frac updated = new Frac(h[ind] - h[j], ind - j, ind);
                if (visible[j].contains(updated)) {
                    visible[j].remove(updated);
                }
                Frac lower = visible[j].lower(updated);
                if (lower == null || updated.compareTo(lower) >= 0) {
                    visible[j].add(updated);
                    Frac higher = visible[j].higher(updated);
                    while (higher != null && updated.compareTo(higher) > 0) {
                        visible[j].remove(higher);
                        higher = visible[j].higher(updated);
                    }
                }
            }
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += visible[j].size();
            }
            out.println(sum);
        }
        in.close();
        out.close();
    }
    static TreeSet<Frac> genVisible(int[] h, int ind) {
        TreeSet<Frac> visible = new TreeSet<Frac>(new Comparator<Frac>() {
            @Override
            public int compare(Frac a, Frac b) {
                return a.ind - b.ind;
            }
        });
        Frac prev = null;
        for (int i = ind + 1; i < h.length; i++) {
            Frac cur = new Frac(h[i] - h[ind], i - ind, i);
            if (prev == null || cur.compareTo(prev) >= 0) {
                visible.add(cur);
                prev = cur;
            }
        }
        return visible;
    }
    static class Frac {
        int a, b, ind;
        Frac(int aa, int bb, int i) {
            a = aa;
            b = bb;
            ind = i;
        }
        int compareTo(Frac o) {
            long val = (long)a * o.b - (long)o.a * b;
            if (val > 0) {
                return 1;
            }else if (val < 0) {
                return -1;
            }
            return 0;
        }
    }
}
