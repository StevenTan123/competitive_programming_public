import java.util.*;
import java.io.*;

public class _1284_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] starts = new int[n];
        int[] ends = new int[n];
        boolean[] ascented = new boolean[n];
        int numa = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken());
            int[] a = new int[l];
            for(int j = 0; j < l; j++) {
                a[j] = Integer.parseInt(line.nextToken());
                if(j > 0 && a[j] > a[j - 1]) ascented[i] = true;
            }
            starts[i] = ascented[i] ? -1 : a[0];
            ends[i] = a[l - 1];
            if(ascented[i]) numa++;
        }
        Arrays.sort(starts);
        long res = 0;
        for(int i = 0; i < n; i++) {
            if(ascented[i]) {
                res += n;
            }else {
                int ind = bsearch(starts, ends[i]);
                res += n - ind;
                res += numa;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static int bsearch(int[] arr, int val) {
        int l = 0;
        int r = arr.length - 1;
        int res = arr.length;
        while(l <= r) {
            int avg = (l + r) / 2;
            if(arr[avg] > val) {
                res = avg;
                r = avg - 1;
            }else {
                l = avg + 1;
            }
        }
        return res;
    }
}
