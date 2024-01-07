import java.util.*;
import java.io.*;

public class email_filing {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(line.nextToken());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] f = new int[n];
            line = new StringTokenizer(in.readLine());
            int[] last = new int[m];
            for(int i = 0; i < n; i++) {
                f[i] = Integer.parseInt(line.nextToken()) - 1;
                last[f[i]] = i;
            }
            ArrayList<Integer> left = new ArrayList<Integer>();
            int[] active = new int[m];
            int size = 0;
            int l = 0;
            int r = 0;
            int start = 0;
            for(int i = 0; i <= m - k; i++) {
                start = i;
                if(i > 0) {
                    size -= active[i + k - 1];
                    active[i + k - 1] = 0;
                }
                while(r <= last[i]) {
                    if(f[r] < i || f[r] > i + k - 1) {
                        active[f[r]]++;
                        size++;
                        if(size > k) {
                            while(active[f[l]] == 0) {
                                l++;
                            }
                            left.add(f[l]);
                            active[f[l]]--;
                            l++;
                            size--;
                        }
                    }else if(size == k) {
                        while(active[f[l]] == 0) {
                            l++;
                        }
                        left.add(f[l]);
                        active[f[l]]--;
                        l++;
                        size--;
                    }
                    r++;
                }
                if(r == n) {
                    break;
                }
            }
            for(int i = l; i < n; i++) {
                if(active[f[i]] > 0) {
                    left.add(f[i]);
                    active[f[i]]--;
                }
            }
            boolean possible = true;
            for(int i = left.size() - 1; i >= 0; i--) {
                int cur = left.get(i);
                if(cur < start) {
                    possible = false;
                    break;
                }else if(cur >= start + k) {
                    start = cur - k + 1;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
