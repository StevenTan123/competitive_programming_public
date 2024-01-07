import java.util.*;
import java.io.*;

public class _1630_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[][] occ = new int[n][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(occ[i], -1);
        }
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken()) - 1;
            if(occ[a[i]][0] == -1) {
                occ[a[i]][0] = i;
            }else {
                occ[a[i]][1] = i;
            }
        }
        int[] segs = new int[n];
        Arrays.fill(segs, -1);
        for(int i = 0; i < n; i++) {
            if(occ[i][1] != -1) {
                segs[occ[i][0]] = occ[i][1];
            }
        }
        int dead = 0;
        int next = -1;
        int best = -1;
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(i <= next) {
                if(segs[i] > next) {
                    best = Math.max(best, segs[i]);
                }
                if(i == next) {
                    if(best == -1) {
                        if(count > 0) {
                            dead += count + 1;
                            count = 0;
                        }
                    }else {
                        next = best;
                        best = -1;
                        count++;
                    }
                }
            }else {
                if(segs[i] != -1) {
                    next = segs[i];
                    count++;
                }else {
                    dead++;
                }
            }
        }
        if(count > 0) {
            dead += count + 1;
        }
        out.println(n - dead);
        in.close();
        out.close();
    }
}
