import java.util.*;
import java.io.*;

public class _1484_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int limit = m / 2;
            if(m % 2 != 0) limit++;
            ArrayList<Integer>[] available = new ArrayList[m];
            int[] count = new int[n + 1];
            int[] res = new int[m];
            for(int i = 0; i < m; i++) {
                available[i] = new ArrayList<Integer>();
                line = new StringTokenizer(in.readLine());
                int k = Integer.parseInt(line.nextToken());
                for(int j = 0; j < k; j++) {
                    available[i].add(Integer.parseInt(line.nextToken()));
                }
                if(k == 1) {
                    count[available[i].get(0)]++;
                    res[i] = available[i].get(0);
                }
            }
            boolean possible = true;
            for(int i = 1; i < n + 1; i++) {
                if(count[i] > limit) {
                    possible = false;
                    break;
                }
            }
            if(!possible) {
                out.println("NO");
                continue;
            }
            for(int i = 0; i < m; i++) {
                if(available[i].size() == 1) continue;
                int minf = -1;
                for(int friend : available[i]) {
                    if(minf == -1 || count[friend] < count[minf]) {
                        minf = friend;
                    }
                }
                count[minf]++;
                res[i] = minf;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++) {
                sb.append(res[i] + " ");
                sb.append(' ');
            }
            out.println("YES");
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
