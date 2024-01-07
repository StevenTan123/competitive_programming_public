import java.util.*;
import java.io.*;

public class _1535_E {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int q = in.nextInt();
        int[][] anc = new int[q + 1][20];
        int[] gold = new int[q + 1];
        int[] price = new int[q + 1];
        gold[0] = in.nextInt();
        price[0] = in.nextInt();
        for(int i = 1; i <= q; i++) {
            if(in.nextInt() == 1) {
                anc[i][0] = in.nextInt();
                gold[i] = in.nextInt();
                price[i] = in.nextInt();
                for(int j = 1; j < 20; j++) {
                    anc[i][j] = anc[anc[i][j - 1]][j - 1];
                }
            }else {
                int v = in.nextInt();
                int w = in.nextInt();
                int left = w;
                long spent = 0;
                while(left > 0) {
                    int start = find_earliest(v, anc, gold);
                    if(gold[start] == 0) {
                        break;
                    }
                    int buy = Math.min(left, gold[start]);
                    left -= buy;
                    gold[start] -= buy;
                    spent += (long)buy * price[start];
                }
                out.println((w - left) + " " + spent);
                out.flush();
            }
        }
        in.close();
        out.close();
    }
    static int find_earliest(int node, int[][] anc, int[] gold) {
        for(int i = 19; i >= 0; i--) {
            if(gold[anc[node][i]] > 0) {
                node = anc[node][i];
            }
        }
        return node;
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
