import java.util.*;
import java.io.*;

public class _1790_G {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            int p = in.nextInt();
            int b = in.nextInt();

            boolean[] token = new boolean[n];
            boolean[] bonus = new boolean[n];
            for (int i = 0; i < p; i++) {
                token[in.nextInt() - 1] = true;
            }

            for (int i = 0; i < b; i++) {
                bonus[in.nextInt() - 1] = true;
            }

            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            // A node is a "staller" node if it has a bonus and a neighbor has a bonus. This allows a token to stall back and forth.
            boolean[] staller = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (bonus[i]) {
                    for (int nei : graph[i]) {
                        if (bonus[nei]) {
                            staller[i] = true;
                            break;
                        }
                    }
                }
            }

            int path_len = -1;

            ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
            bfs.add(new BFS(0, 0));
            boolean[] visited = new boolean[n];
            while(bfs.size() > 0) {
                BFS cur = bfs.poll();
                if (visited[cur.node]) {
                    continue;
                }
                visited[cur.node] = true;
                if (token[cur.node]) {
                    path_len = cur.dist;
                    break;
                }
                if (cur.node != 0 && !bonus[cur.node]) {
                    continue;
                }
                for (int nei : graph[cur.node]) {
                    bfs.add(new BFS(nei, cur.dist + 1));
                }
            }

            if (path_len == -1) {
                out.println("NO");
            } else {
                int staller_count = 0;
                for (int i = 0; i < n; i++) {
                    boolean is_staller = staller[i];
                    for (int nei : graph[i]) {
                        is_staller |= staller[nei];
                    }
                    if (token[i] && is_staller) {
                        staller_count++;
                    }
                }
                if (staller_count > 1) {
                    out.println("YES");
                } else {
                    int sink_count = 0;
                    for (int i = 0; i < n; i++) {
                        boolean is_sink = false;
                        for (int nei : graph[i]) {
                            is_sink |= bonus[nei];
                        }
                        if (token[i] && is_sink) {
                            sink_count++;
                        }
                    }
                    if (path_len <= 1 || sink_count >= path_len) {
                        out.println("YES");
                    } else {
                        out.println("NO");
                    }
                }
            }
        }
        in.close();
        out.close();
    }

    static class BFS {
        int node, dist;
        BFS(int n, int d) {
            node = n;
            dist = d;
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
