import java.util.*;
import java.io.*;

public class kakyoins_painting {
    static final long MOD = 1000000007;
    static final long[][] fact = new long[2000005][2];
    public static void main(String[] args) throws IOException {
        fact[0][0] = 1;
        fact[0][1] = 1;
        for(int i = 1; i < 2000005; i++) {
            fact[i][0] = modmult(fact[i - 1][0], i);
            fact[i][1] = modinv(fact[i][0]);
        }
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            long sum = 0;
            for(int i = 1; i <= n; i++) {
                long one = binom(n + i, 2 * i);
                long add = modmult(modmult(modmult(one, one), fact[i][0]), i);
                sum = modadd(sum, add);
            }
            out.println(sum);
        }
        in.close();
        out.close();
    }
    static long binom(int n, int k) {
        return modmult(modmult(fact[n][0], fact[n - k][1]), fact[k][1]);
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return modmult(small, small);
        } else {
            return modmult(modmult(small, small), a);
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
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
