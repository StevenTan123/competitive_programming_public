import java.util.*;
import java.io.*;

public class _1514_D {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        HashMap<Integer, ArrayList<Integer>> indices = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < n; i++) {
            if(!indices.containsKey(a[i])) {
                indices.put(a[i], new ArrayList<Integer>());
            }
            indices.get(a[i]).add(i);
        }
        Random rand = new Random();
        for(int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int max = 0;
            for(int j = 0; j < 40; j++) {
                int ind = rand.nextInt(r - l + 1) + l;
                int freq = frequency(indices, l, r, a[ind]);
                max = Math.max(max, freq);
            }
            int res = 2 * max - (r - l + 1);
            if(res <= 0) res = 1;
            out.println(res);
        }
        in.close();
        out.close();
    }
    static int frequency(HashMap<Integer, ArrayList<Integer>> indices, int l, int r, int num) {
        int freq = 0;
        ArrayList<Integer> cur = indices.get(num);
        int low = bsearch_high(cur, l);
        int high = bsearch_low(cur, r);
        if(low > -1 && high > -1) {
            freq = high - low + 1;
        }
        return freq;
    }
    static int bsearch_high(ArrayList<Integer> a, int key) {
        int l = 0;
        int r = a.size() - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(a.get(m) >= key) {
                res = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return res;
    }
    static int bsearch_low(ArrayList<Integer> a, int key) {
        int l = 0;
        int r = a.size() - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(a.get(m) <= key) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
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
