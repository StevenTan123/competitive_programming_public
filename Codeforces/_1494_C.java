import java.util.*;
import java.io.*;

public class _1494_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];
            int apos = n;
            int bpos = m;
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken());
                if(apos == n && a[j] > 0) apos = j;
            }
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                b[j] = Integer.parseInt(line.nextToken());
                if(bpos == m && b[j] > 0) bpos = j;
            }
            int[] a1 = new int[apos];
            int[] a2 = new int[n - apos];
            int[] b1 = new int[bpos];
            int[] b2 = new int[m - bpos];
            for(int j = 0; j < n; j++) {
                if(j < apos) {
                    a1[apos - j - 1] = a[j] * -1;
                }else {
                    a2[j - apos] = a[j];
                }
            }
            for(int j = 0; j < m; j++) {
                if(j < bpos) {
                    b1[bpos - j - 1] = b[j] * -1;
                }else {
                    b2[j - bpos] = b[j];
                }
            }
            int match1 = maxmatch(a1, b1);
            int match2 = maxmatch(a2, b2);
            out.println(match1 + match2);
        }
        in.close();
        out.close();
    }
    static int maxmatch(int[] a, int[] b) {
        int[] clumped = new int[b.length];
        int rpointer = 0;
        for(int i = 0; i < b.length; i++) {
            while(rpointer < a.length && a[rpointer] <= b[i]) {
                rpointer++;
            }
            while(rpointer < a.length && a[rpointer] <= b[i] + rpointer) {
                rpointer++;
            }
            clumped[i] = rpointer;
        }
        int[] sufmatch = new int[b.length + 1];
        int pointer = a.length - 1;
        int match = 0;
        for(int i = b.length - 1; i >= 0; i--) {
            while(pointer >= 0 && a[pointer] >= b[i]) {
                if(a[pointer] == b[i]) match++;
                pointer--;
            }
            sufmatch[i] = match;
        }
        rpointer = 0;
        int maxmatch = 0;
        for(int i = 0; i < b.length; i++) {
            while(rpointer < b.length && b[rpointer] <= b[i] + clumped[i] - 1) {
                rpointer++;
            }
            maxmatch = Math.max(maxmatch, rpointer - i + sufmatch[rpointer]);
        }
        return maxmatch;
    }
}
