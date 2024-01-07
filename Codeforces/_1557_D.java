import java.util.*;
import java.io.*;

public class _1557_D {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Pair>[] segs = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            segs[i] = new ArrayList<Pair>();
        }
        TreeSet<Integer> coords = new TreeSet<Integer>();
        HashMap<Integer, Integer> compress = new HashMap<Integer, Integer>();
        for(int i = 0; i < m; i++) {
            int row = in.nextInt() - 1;
            int l = in.nextInt();
            int r = in.nextInt();
            coords.add(l);
            coords.add(r);
            segs[row].add(new Pair(l, r));
        }
        int count = 0;
        for(int coord : coords) {
            compress.put(coord, count);
            count++;
        }
        for(int i = 0; i < n; i++) {
            for(Pair seg : segs[i]) {
                seg.a = compress.get(seg.a);
                seg.b = compress.get(seg.b);
            }
        }
        Segtree st = new Segtree(new int[count]);
        int[] prev = new int[n];
        for(int i = 0; i < n; i++) {
            Pair max = new Pair(0, -1);
            for(Pair p : segs[i]) {
                Pair cur = st.max_query(1, 0, count - 1, p.a, p.b);
                max = max(max, cur);
            }
            prev[i] = max.b;
            Pair next = new Pair(max.a + 1, i);
            for(Pair p : segs[i]) {
                st.update(1, 0, count - 1, p.a, p.b, next);
            }
        }
        Pair max = st.max_query(1, 0, count - 1, 0, count - 1);
        HashSet<Integer> used = new HashSet<Integer>();
        int cur = max.b;
        while(cur > -1) {
            used.add(cur);
            cur = prev[cur];
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            if(!used.contains(i - 1)) {
                sb.append(i);
                sb.append(' ');
            }
        }
        out.println(n - max.a);
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
    static class Segtree {
        int[] a;
        Pair[] max, lazy;
        Segtree(int[] aa) {
            a = aa;
            max = new Pair[a.length * 4];
            lazy = new Pair[a.length * 4];
            for(int i = 0; i < a.length * 4; i++) {
                lazy[i] = new Pair(0, -1);
            }
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                max[v] = new Pair(a[l], -1);
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                max[v] = max(max[v * 2], max[v * 2 + 1]);
            }
        }
        void push(int v) {
            if(lazy[v].a == 0) {
                return;
            }
            lazy[v * 2] = lazy[v];
            max[v * 2] = lazy[v];
            lazy[v * 2 + 1] = lazy[v];
            max[v * 2 + 1] = lazy[v];
            lazy[v] = new Pair(0, -1);
        }
        void update(int v, int l, int r, int ql, int qr, Pair val) {
            if(ql > qr) {
                return;
            }else if(l == ql && r == qr) {
                lazy[v] = val;
                max[v] = val;
            }else {
                push(v);
                int m = (l + r) / 2;
                update(v * 2, l, m, ql, Math.min(m, qr), val);
                update(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr, val);
                max[v] = max(max[v * 2], max[v * 2 + 1]);
            }
        }
        Pair max_query(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return new Pair(0, -1);
            }else if(l == ql && r == qr) {
                return max[v];
            }else {
                push(v);
                int m = (l + r) / 2;
                Pair maxl = max_query(v * 2, l, m, ql, Math.min(m, qr));
                Pair maxr = max_query(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return max(maxl, maxr);
            }
        }
    }
    static Pair max(Pair a, Pair b) {
        if(a.a > b.a) {
            return a;
        }
        return b;
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
