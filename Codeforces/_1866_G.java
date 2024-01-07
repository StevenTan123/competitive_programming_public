import java.util.*;
import java.io.*;

public class _1866_G {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.nextInt();
        int[] A = new int[n];
        int[] D = new int[n];
        Seg[] segs = new Seg[n];
        long avg = 0;
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
            avg += A[i];
        }
        avg /= n;
        for (int i = 0; i < n; i++) {
            D[i] = in.nextInt();
            segs[i] = new Seg(Math.max(i - D[i], 0), Math.min(i + D[i], n - 1), A[i]);
        }
        Arrays.sort(segs);

        int l = (int) avg;
        int r = 1000000000;
        int res = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (possible(segs, m)) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        out.println(res);
        
        in.close();
        out.close();
    }
    static boolean possible(Seg[] segs, int max) {
        PriorityQueue<Event> active = new PriorityQueue<Event>();
        int p = 0;
        for (int i = 0; i < segs.length; i++) {
            if (active.size() > 0 && active.peek().r < i) {
                return false;
            }
            while (p < segs.length && segs[p].l == i) {
                if (segs[p].n > 0) { 
                    active.add(new Event(segs[p].r, segs[p].n, p));
                }
                p++;
            }
            int left = max;
            Event cur = null;
            while (active.size() > 0 && left > 0) {
                if (cur == null) {
                    cur = active.peek();
                }
                int min = Math.min(left, cur.n);
                cur.n -= min;
                left -= min;
                if (cur.n == 0) {
                    active.poll();
                    cur = null;
                }
            }
        }
        return active.size() == 0;
    }
    static class Event implements Comparable<Event> {
        int r, n, id;
        Event(int rr, int nn, int i) {
            r = rr;
            n = nn;
            id = i;
        }
        @Override
        public int compareTo(Event o) {
            if (r == o.r) {
                return id - o.id;
            }
            return r - o.r;
        }
    }
    static class Seg implements Comparable<Seg> {
        int l, r, n;
        Seg(int ll, int rr, int nn) {
            l = ll;
            r = rr;
            n = nn;
        }
        @Override
        public int compareTo(Seg o) {
            if (l == o.l) {
                return r - o.r;
            }
            return l - o.l;
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
