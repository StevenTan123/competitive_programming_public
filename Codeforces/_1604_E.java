import java.util.*;
import java.io.*;

public class _1604_E {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int[][] dp = new int[2][100005];
            ArrayList<Integer>[] keys = new ArrayList[2];
            keys[0] = new ArrayList<Integer>();
            keys[1] = new ArrayList<Integer>();
            dp[0][a[n - 1]] = 1;
            keys[0].add(a[n - 1]);
            long res = 0;
            int cur = 0;
            for(int i = n - 1; i > 0; i--) {
                dp[1 - cur][a[i - 1]] += 1;
                keys[1 - cur].add(a[i - 1]);
                int prev = a[i - 1];
                for(int key : keys[cur]) {
                    int ceil = a[i - 1] / key;
                    if(a[i - 1] % key != 0) ceil++;
                    int min = a[i - 1] / ceil;
                    dp[1 - cur][min] += dp[cur][key];
                    res = modadd(res, modmult(modmult(dp[cur][key], ceil - 1), i));
                    if(min != prev) {
                        keys[1 - cur].add(min);
                        prev = min;
                    }
                    dp[cur][key] = 0;
                }
                keys[cur].clear();
                cur = 1 - cur;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
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
