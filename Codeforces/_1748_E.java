import java.util.*;
import java.io.*;

public class _1748_E {
    static final long MOD = 1000000007;
    static final int MAXN = 200005;
    static int[] log = new int[MAXN + 1];

    public static void main(String[] args) throws IOException {
        log[1] = 0;
        for (int i = 2; i <= MAXN; i++) {
            log[i] = log[i / 2] + 1;
        }
        
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[n];
            long[][] dp = new long[n][m + 1];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                Arrays.fill(dp[i], -1);
            }

            SparseTable sp = new SparseTable(a);
            long res = recurse_dp(dp, 0, n - 1, m, sp);
            out.println(res);
        }

        in.close();
        out.close();
    }

    static long recurse_dp(long[][] dp, int l, int r, int m, SparseTable sp) {
        if (l > r) {
            return 1;
        }
        if (m <= 0) {
            return 0;
        }
        if (l == r) {
            return m;
        }
        int max = sp.max(l, r);
        if (dp[max][m] != -1) {
            return dp[max][m];
        }
        
        long res = 0;
        res += recurse_dp(dp, l, r, m - 1, sp) + recurse_dp(dp, l, max - 1, m - 1, sp) * recurse_dp(dp, max + 1, r, m, sp) % MOD;
        res %= MOD;
        
        dp[max][m] = res;
        return res;
    }

    static class SparseTable {
        int K = 20;
        int N;
        int[] a;
        int[][] min = new int[MAXN][K + 1];
        int[][] max = new int[MAXN][K + 1];

        SparseTable(int[] a) {
            this.a = a;
            N = a.length;
            for (int i = 0; i < N; i++) {
                min[i][0] = i;
                max[i][0] = i;
            }
            for (int j = 1; j <= K; j++) {
                for (int i = 0; i + (1 << j) <= N; i++) {
                    min[i][j] = min[i][j - 1];
                    if (a[min[i + (1 << (j - 1))][j - 1]] < a[min[i][j]]) {
                        min[i][j] = min[i + (1 << (j - 1))][j - 1];
                    }
                    max[i][j] = max[i][j - 1];
                    if (a[max[i + (1 << (j - 1))][j - 1]] > a[max[i][j]]) {
                        max[i][j] = max[i + (1 << (j - 1))][j - 1];
                    }
                }
            }
        }

        int min(int l, int r) {
            int j = log[r - l + 1];
            int one = min[l][j];
            int two = min[r - (1 << j) + 1][j];
            if (a[one] <= a[two]) {
                return one;
            } else {
                return two;
            }
        }

        int max(int l, int r) {
            int j = log[r - l + 1];
            int one = max[l][j];
            int two = max[r - (1 << j) + 1][j];
            if (a[one] <= a[two]) {
                return one;
            } else {
                return two;
            }
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }
}
