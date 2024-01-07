import java.util.*;
import java.io.*;

public class _1561_E {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                a[i] = in.nextInt() - 1;
                if(a[i] % 2 != i % 2) {
                    possible = false;
                }
            }
            if(!possible) {
                out.println(-1);
            }else {
                int res = 0;
                StringBuilder sb = new StringBuilder();
                for(int i = n - 1; i > 0; i -= 2) {

                    int ind = findVal(a, i);
                    reverse(a, ind);
                    sb.append(ind + 1);
                    sb.append(' ');

                    ind = findVal(a, i - 1);
                    reverse(a, ind - 1);
                    sb.append(ind);
                    sb.append(' ');

                    reverse(a, ind + 1);
                    sb.append(ind + 2);
                    sb.append(' ');

                    reverse(a, 2);
                    sb.append(3);
                    sb.append(' ');

                    reverse(a, i);
                    sb.append(i + 1);
                    sb.append(' ');

                    res += 5;

                }
                for(int i = 0; i < n; i++) {
                    if(i != a[i]) {
                        possible = false;
                        break;
                    }
                }
                if(!possible) {
                    out.println(-1);
                }else {
                    out.println(res);
                    out.println(sb.toString());
                }
            }
        }
        in.close();
        out.close();
    }
    static void reverse(int[] a, int ind) {
        for(int i = 0; i <= ind / 2; i++) {
            int temp = a[i];
            a[i] = a[ind - i];
            a[ind - i] = temp;
        }
    }
    static int findVal(int[] a, int val) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] == val) {
                return i;
            }
        }
        return -1;
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
