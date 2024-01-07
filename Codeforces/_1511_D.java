import java.util.*;
import java.io.*;

public class _1511_D {
    static int n, k, pointer;
    static int[] path, next;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        k = Integer.parseInt(line.nextToken());
        path = new int[k * k];
        next = new int[k];
        eulerian(0);
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        for(int i = 0; i < n - 1; i++) {
            int val = path[i % path.length];
            sb.append((char)(val + 97));
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static void eulerian(int v) {
        while(next[v] < k) {
            int temp = next[v];
            next[v]++;
            eulerian(temp);
            path[pointer] = temp;
            pointer++;
        }
    }
}
