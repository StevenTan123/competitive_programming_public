import java.util.*;
import java.io.*;

public class lunchline {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();
            int[] a = new int[n];
            int[] h = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = in.nextInt() - 1;
            }
            for(int i = 0; i < n; i++) {
                h[i] = in.nextInt();
            }
            int[] arr = new int[2 * n];
            for(int i = 0; i < n; i++) {
                arr[i] = h[a[i]];
                arr[i + n] = arr[i];
            }
            int[] ranges = new int[2 * n];
            long[] food = new long[2 * n];
            long sum = 0;
            int r = 0;
            for(int l = 0; l < n; l++) {
                if(r < l) {
                    r = l;
                }else if(l > 0) {
                    sum -= arr[r];
                }
                if(l > 0) {
                    sum -= arr[l - 1];
                }
                while(r < l + n && sum < p) {
                    sum += arr[r];
                    r++;
                }
                r--;
                ranges[l]++;
                ranges[r]--;
                if(sum < p) {
                    food[r] += arr[r];
                }else {
                    food[r] += arr[r] - (sum - p);
                }
            }
            int cursum = 0;
            double[] res = new double[n];
            for(int i = 0; i < 2 * n; i++) {
                cursum += ranges[i];
                food[i] += (long)cursum * arr[i];
                res[a[i % n]] += (double)food[i] / n;
            }
            for(int i = 0; i < n; i++) {
                out.printf("%.9f ", res[i]);
            }
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
