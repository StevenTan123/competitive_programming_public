import java.util.*;
import java.io.*;

public class _1620_E {
    static int MAXQ = 500005;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int q = Integer.parseInt(in.readLine());
        parents = new int[MAXQ];
        for(int i = 0; i < q; i++) {
            parents[i] = i;
        }

        //stores the value associated with a certain component
        int[] val = new int[MAXQ];

        //stores the component associated with value
        int[] inv = new int[MAXQ];
        Arrays.fill(inv, -1);

        int p = 0;
        for(int i = 0; i < q; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            if(type == 1) {
                if(inv[x] == -1) {
                    val[p] = x;
                    inv[x] = p;
                }else {
                    parents[p] = inv[x];
                }
                p++;
            }else {
                int y = Integer.parseInt(line.nextToken());
                if(inv[x] == -1 || x == y) {
                    continue;
                }else {
                    if(inv[y] == -1) {
                        val[inv[x]] = y;
                        inv[y] = inv[x];
                        inv[x] = -1;
                    }else {
                        val[inv[x]] = 0;
                        parents[inv[x]] = inv[y];
                        inv[x] = -1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < p; i++) {
            sb.append(val[find(i)]);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static int find(int node) {
        if(parents[node] == node) {
            return node;
        }
        parents[node] = find(parents[node]);
        return parents[node];
    }
}
