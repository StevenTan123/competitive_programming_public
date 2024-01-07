import java.util.*;
import java.io.*;

public class up_down {
    static int[] d, d2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        String s = in.readLine();

        d = new int[n + 1];
        d2 = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        d2[0] = Integer.MAX_VALUE;

        int prev = 0;
        int cur_ind = 0;
        int res = 0;
        for(int i = 1; i < n; i++) {
            if(i == n - 1 || s.charAt(i) != s.charAt(i - 1)) {
                if(s.charAt(prev) == 'U') {
                    cur_ind = LIS(a, cur_ind, i - prev + 1);
                }else {
                    cur_ind = LDS(a, cur_ind, i - prev + 1);
                }
                if(cur_ind >= 0) {
                    res += i - prev;
                }else {
                    res -= cur_ind + 1;
                    break;
                }
                prev = i;
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
    static int LIS(int[] a, int ind, int count) {
        int max = 0;
        for(int i = ind; i < a.length; i++) {
            int j = bsearch(a[i]);
            if(j < d.length - 1 && a[i] < d[j + 1]) {
                d[j + 1] = a[i];
                if(j + 1 == count) {
                    for(int k = 1; k <= j + 1; k++) {
                        d[k] = Integer.MAX_VALUE;
                    }
                    return i;
                }
                max = Math.max(max, j + 1);
            }
        }
        return -1 * max;
    }
    static int LDS(int[] a, int ind, int count) {
        int max = 0;
        for(int i = ind; i < a.length; i++) {
            int j = bsearch2(a[i]);
            if(j < d2.length - 1 && a[i] > d2[j + 1]) {
                d2[j + 1] = a[i];
                if(j + 1 == count) {
                    for(int k = 1; k <= j + 1; k++) {
                        d2[k] = 0;
                    }
                    return i;
                }
                max = Math.max(max, j + 1);
            }
        }
        return -1 * max;
    }
    static int bsearch(int val) {
        int l = 0;
        int r = d.length - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(d[m] <= val) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
    static int bsearch2(int val) {
        int l = 0;
        int r = d2.length - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(d2[m] >= val) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
}
