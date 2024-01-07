import java.util.*;
import java.io.*;

public class _1840_F {
    static int[][] dirs = new int[][] { {0, 1}, {1, 0} };
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int r = in.nextInt();
            
            HashSet<Integer>[] rows = new HashSet[n + 1];
            HashSet<Integer>[] cols = new HashSet[m + 1];
            for (int i = 0; i <= n; i++) rows[i] = new HashSet<Integer>();
            for (int i = 0; i <= m; i++) cols[i] = new HashSet<Integer>();
            for (int i = 0; i < r; i++) {
                int time = in.nextInt();
                if (in.nextInt() == 1) {
                    rows[in.nextInt()].add(time);
                } else {
                    cols[in.nextInt()].add(time);
                }
            }

            boolean[][][] dp = new boolean[n + 1][m + 1][r + 1];
            dp[0][0][0] = true;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= r; k++) {
                        int time = i + j + k;
                        if (!rows[i].contains(time) && !cols[j].contains(time)) {
                            if (i > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][k];
                            }
                            if (j > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i][j - 1][k];
                            }
                            if (k > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i][j][k - 1];
                            }
                        }
                    }
                }
            }

            int res = -1;
            for (int i = 0; i <= r; i++) {
                if (dp[n][m][i]) {
                    res = n + m + i;
                    break;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
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
