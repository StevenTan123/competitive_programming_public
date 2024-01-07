import java.util.*;
import java.io.*;

public class _1761_E {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] graph = new int[n][n];
            int[] degree = new int[n];
            int[] par = new int[n];
            int[] size = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
                size[i] = 1;
            }
            for (int i = 0; i < n; i++) {
                String line = in.readLine(n + 1);
                for (int j = 0; j < n; j++) {
                    if (line.charAt(j) == '1') {
                        degree[i]++;
                        graph[i][j] = 1;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (graph[i][j] == 1) {
                        union(par, size, i, j);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                find(par, i);
            }
            int[] edges = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (graph[i][j] == 1 && par[i] == par[j]) {
                        edges[par[i]]++;
                    }
                }
            }
            // index of head of a non-fully connected component
            int non_ind = -1;
            int min_size = -1;
            ArrayList<Integer> heads = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (i == par[i]) {
                    heads.add(i);
                    if (min_size == -1 || size[i] < size[min_size]) {
                        min_size = i;
                    }
                    if (edges[i] != size[i] * (size[i] - 1) / 2) {
                        non_ind = i;
                    }
                }
            }
            if (heads.size() == 1) {
                out.println(0);
            } else if (size[min_size] == 1) {
                out.println(1);
                out.println(min_size + 1);
            } else if (non_ind == -1) {
                if (heads.size() == 2) {
                    out.println(size[min_size]);
                    for (int i = 0; i < n; i++) {
                        if (par[i] == min_size) {
                            out.print((i + 1) + " ");
                        }
                    }
                    out.println();
                } else {
                    out.println(2);
                    out.println((heads.get(0) + 1) + " " + (heads.get(1) + 1));
                }
            } else {
                int min_deg = non_ind;
                for (int i = 0; i < n; i++) {
                    if (par[i] == non_ind && degree[i] < degree[min_deg]) {
                        min_deg = i;
                    }
                }
                out.println(1);
                out.println(min_deg + 1);
            }
        }
        in.close();
        out.close();
    }

    static int find(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = find(parents, parents[node]);
        return parents[node];
    }

    static void union(int[] parents, int[] size, int a, int b) {
        int roota = find(parents, a);
        int rootb = find(parents, b);
        if (roota == rootb) {
            return;
        }
        if (size[roota] > size[rootb]) {
            int temp = roota;
            roota = rootb;
            rootb = temp;
        }
        parents[roota] = rootb;
        size[rootb] += size[roota];
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

        public String readLine(int len) throws IOException {
            byte[] buf = new byte[len]; // line length
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
