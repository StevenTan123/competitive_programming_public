import java.util.*;
import java.io.*;

public class _1108_E1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int[][] segments = new int[m][2];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            segments[i][0] = Integer.parseInt(line.nextToken()) - 1;
            segments[i][1] = Integer.parseInt(line.nextToken()) - 1;
        }
        int max = 0;
        ArrayList<Integer> used = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ArrayList<Integer> curused = new ArrayList<Integer>();
                int subcount = 0;
                for(int k = 0; k < m; k++) {
                    if(insegment(j, segments[k][0], segments[k][1]) && !insegment(i, segments[k][0], segments[k][1])) {
                        curused.add(k + 1);
                        subcount++;
                    }
                }
                int val = a[i] - a[j] + subcount;
                if(val > max) {
                    max = val;
                    used = curused;
                }
            }
        }
        out.println(max);
        out.println(used.size());
        StringBuilder sb = new StringBuilder();
        for(int ind : used) {
            sb.append(ind);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static boolean insegment(int x, int s, int e) {
        return x <= e && x >= s;
    }
}
