import java.util.*;
import java.io.*;

public class _1808_D {
    static int MAXN = 200005;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        int[] a = new int[n];

        ArrayList<Integer>[] odds = new ArrayList[MAXN];
        ArrayList<Integer>[] evens = new ArrayList[MAXN];
        for (int i = 0; i < MAXN; i++) {
            odds[i] = new ArrayList<Integer>();
            evens[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            if (i % 2 == 0) {
                evens[a[i]].add(i);
            } else {
                odds[a[i]].add(i);
            }
        }

        long total = (long)(n - k + 1) * (k / 2);
        long sub = 0;
        for (int i = 0; i < MAXN; i++) {
            sub += contrib(n, k, odds[i]) + contrib(n, k, evens[i]);
        }
        out.println(total - sub);

		in.close();
		out.close();
	}
    static long contrib(int n, int k, ArrayList<Integer> inds) {
        long res = 0;

        int r = 0;
        for (int i = 0; i < inds.size(); i++) {
            while (r < inds.size() && inds.get(r) - inds.get(i) <= k - 1) {
                r++;
            }
            res += r - i - 1;
        }

        r = 1;
        while (r < inds.size() && (inds.get(r) + inds.get(0)) / 2 < (k - 1) / 2) {
            r++;
        }
        r--;
        res -= r;
        for (int i = 1; i < inds.size(); i++) {
            while (r > i && (inds.get(r) + inds.get(i)) / 2 >= (k - 1) / 2) {
                r--;
            }
            if (r > i) {
                res -= r - i;
            }
        }

        int l = inds.size() - 2;
        while (l >= 0 && (inds.get(l) + inds.get(inds.size() - 1)) / 2 + (k - 1) / 2 >= n) {
            l--;
        }
        l++;
        res -= inds.size() - 1 - l;
        for (int i = inds.size() - 2; i >= 0; i--) {
            while (l < i && (inds.get(l) + inds.get(i)) / 2 + (k - 1) / 2 < n) {
                l++;
            }
            if (i > l) {
                res -= i - l;
            }
        }

        return res;
    }
}
