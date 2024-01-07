import java.util.*;
import java.io.*;

public class _1737_D {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            int[][] edges = new int[m][3];
            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int w = in.nextInt();
                graph[u].add(v);
                graph[v].add(u);
                edges[i] = new int[] { u, v, w };
            }

            int[][] dists = new int[n][n];
            // lca_depth[i] stores the depth of the LCA between node 1 and n in the shortest path tree rooted at i.
            int[] lca_depth = new int[n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dists[i], -1);
                int[] par = new int[n];
                int[][] next = new int[n][2];
                for (int j = 0; j < n; j++) {
                    next[j][0] = Integer.MAX_VALUE;
                    next[j][1] = -1;
                }
                next[i][0] = 0;
                for (int j = 0; j < n; j++) {
                    int min = -1;
                    for (int k = 0; k < n; k++) {
                        if (dists[i][k] == -1 && (min == -1 || next[k][0] < next[min][0])) {
                            min = k;
                        }
                    }
                    dists[i][min] = (next[min][1] == -1 ? -1 : dists[i][next[min][1]]) + 1;
                    par[min] = next[min][1];

                    for (int nei : graph[min]) {
                        if (dists[i][min] + 1 < next[nei][0]) {
                            next[nei][0] = dists[i][min] + 1;
                            next[nei][1] = min;
                        }
                    }
                }

                boolean[] marked = new boolean[n];
                int node = 0;
                while (node != -1) {
                    marked[node] = true;
                    node = par[node];
                }
                node = n - 1;
                int depth = 0;
                int lca = -1;
                while (node != -1) {
                    if (marked[node] && lca == -1) {
                        lca = depth;
                    }
                    node = par[node];
                    depth++;
                }
                lca_depth[i] = depth - lca - 1;
            }

            long min = Long.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                int u = edges[i][0];
                int v = edges[i][1];
                int w = edges[i][2];
                
                int du1 = dists[u][0];
                int dun = dists[u][n - 1];
                int dv1 = dists[v][0];
                int dvn = dists[v][n - 1];
                int moves = Integer.MAX_VALUE;
                if (du1 <= dv1 && dun <= dvn) {
                    moves = Math.min(moves, du1 + dun - lca_depth[u] + 1);
                } else if (du1 >= dv1 && dun >= dvn) {
                    moves = Math.min(moves, dv1 + dvn - lca_depth[v] + 1);
                }
                if (du1 <= dv1 && dun >= dvn) {
                    moves = Math.min(moves, du1 + dvn);
                } else if (du1 >= dv1 && dun <= dvn) {
                    moves = Math.min(moves, dv1 + dun);
                }
                min = Math.min(min, (long)(moves + 1) * w);
            }
            out.println(min);
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