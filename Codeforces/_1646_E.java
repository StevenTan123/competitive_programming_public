import java.util.*;
import java.io.*;

public class _1646_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        boolean[] set = new boolean[20 * m + 5];
        int[] unique = new int[25];
        int count = 0;
        for(int i = 1; i <= 20; i++) {
            for(int j = 1; j <= m; j++) {
                if(!set[i * j]) {
                    count++;
                    set[i * j] = true;
                }
            }
            unique[i] = count;
        }
        long res = 1;
        boolean[] marked = new boolean[n + 5];
        for(int i = 2; i <= n; i++) {
            if(marked[i]) {
                continue;
            }
            int num = i;
            count = 1;
            while((long)num * i <= n) {
                num *= i;
                marked[num] = true;
                count++;
            }
            res += unique[count];
        }
        out.println(res);
        in.close();
        out.close();
    }
}
