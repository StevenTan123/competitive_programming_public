import java.util.*;
import java.io.*;

public class _1418_D {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] p = new int[n];
        TreeSet<Integer> piles = new TreeSet<Integer>();
        TreeMap<Integer, Integer> gaps = new TreeMap<Integer, Integer>();
        for(int i = 0; i < n; i++) {
            p[i] = in.nextInt();
            piles.add(p[i]);
        }
        Random rand = new Random();
        for(int i = 0; i < n; i++) {
            int j = rand.nextInt(n);
            int temp = p[i];
            p[i] = p[j];
            p[j] = temp;
        }
        Arrays.sort(p);
        for(int i = 0; i < n - 1; i++) {
            insert_gap(gaps, p[i + 1] - p[i]);
        }
        if(piles.size() <= 1) {
            out.println(0);
        }else {
            out.println(piles.last() - piles.first() - gaps.lastKey());
        }
        for(int i = 0; i < q; i++) {
            int t = in.nextInt();
            int x = in.nextInt();
            if(t == 0) {
                piles.remove(x);
                Integer lower = piles.lower(x);
                Integer higher = piles.higher(x);
                if(lower != null) {
                    remove_gap(gaps, x - lower);
                }
                if(higher != null) {
                    remove_gap(gaps, higher - x);
                }
                if(lower != null && higher != null) {
                    insert_gap(gaps, higher - lower);
                }
            }else {
                piles.add(x);
                Integer lower = piles.lower(x);
                Integer higher = piles.higher(x);
                if(lower != null && higher != null) {
                    remove_gap(gaps, higher - lower);
                }
                if(lower != null) {
                    insert_gap(gaps, x - lower);
                }
                if(higher != null) {
                    insert_gap(gaps, higher - x);
                }
            }
            if(piles.size() <= 1) {
                out.println(0);
            }else {
                out.println(piles.last() - piles.first() - gaps.lastKey());
            }
        }
        in.close();
        out.close();
    }
    static void insert_gap(TreeMap<Integer, Integer> gaps, int gap) {
        Integer count = gaps.get(gap);
        if(count == null) {
            count = 0;
        }
        gaps.put(gap, count + 1);
    }
    static void remove_gap(TreeMap<Integer, Integer> gaps, int gap) {
        Integer count = gaps.get(gap);
        if(count <= 1) {
            gaps.remove(gap);
        }else {
            gaps.put(gap, count - 1);
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