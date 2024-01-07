import java.util.*;
import java.io.*;

public class _1527_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            HashMap<Integer, Long> presums = new HashMap<Integer, Long>();
            long res = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                Long sum = presums.get(a[i]);
                if(sum == null) sum = (long)0;
                res += sum * (n - i);
                sum += i + 1;
                presums.put(a[i], sum);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
