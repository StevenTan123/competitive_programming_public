import java.util.*;
import java.io.*;

public class _1556_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[][] a = new int[n][31];
        for (int i = 0; i < n - 1; i++) {
            int and = query("and", i + 1, i + 2, in);
            int or = query("or", i + 1, i + 2, in);
            for (int j = 30; j >= 0; j--) {
                int ad = and % 2;
                int od = or % 2;
                if (ad == 1 && od == 1) {
                    a[i][j] = 1;
                    a[i + 1][j] = 1;
                } else if (ad == 0 && od == 0) {
                    a[i][j] = 0;
                    a[i + 1][j] = 0;
                } else {
                    if(i == 0) a[i][j] = 2;
                    a[i + 1][j] = 2;
                }
                and /= 2;
                or /= 2;
            }
        }
        if (n % 2 == 0) {
            int and = query("and", 1, n - 1, in);
            int or = query("or", 1, n - 1, in);
            for (int j = 30; j >= 0; j--) {
                int ad = and % 2;
                int od = or % 2;
                if (ad == 1 && od == 1) {
                    a[0][j] = 1;
                    a[n - 2][j] = 1;
                } else if (ad == 0 && od == 0) {
                    a[0][j] = 0;
                    a[n - 2][j] = 0;
                }
                and /= 2;
                or /= 2;
            }
        } else {
            int and = query("and", 1, n, in);
            int or = query("or", 1, n, in);
            for (int j = 30; j >= 0; j--) {
                int ad = and % 2;
                int od = or % 2;
                if (ad == 1 && od == 1) {
                    a[0][j] = 1;
                    a[n - 1][j] = 1;
                } else if (ad == 0 && od == 0) {
                    a[0][j] = 0;
                    a[n - 1][j] = 0;
                }
                and /= 2;
                or /= 2;
            }
        }
        for (int i = 0; i < 31; i++) {
            int prev = -1;
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 2) {
                    if (prev > -1) {
                        a[j][i] = 1 - prev;
                        prev = a[j][i];
                    }
                } else {
                    prev = a[j][i];
                }
            }
            prev = -1;
            for (int j = n - 1; j >= 0; j--) {
                if (a[j][i] == 2) {
                    if (prev > -1) {
                        a[j][i] = 1 - prev;
                        prev = a[j][i];
                    }
                } else {
                    prev = a[j][i];
                }
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 31; j++) {
                res[i] = res[i] * 2 + a[i][j];
            }
        }
        Arrays.sort(res);
        System.out.println("finish " + res[k - 1]);
        System.out.flush();
        in.close();
    }

    static int query(String type, int i, int j, BufferedReader in) throws IOException {
        System.out.println(type + " " + i + " " + j);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }
}