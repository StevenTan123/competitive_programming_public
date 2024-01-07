import java.util.*;
import java.io.*;

public class _1552_D {
    static int[] pow = new int[15];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        for(int i = 1; i < 15; i++) {
            pow[i] = pow[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(a[i] < 0) {
                    a[i] *= -1;
                }
            }
            HashSet<Integer> sums = new HashSet<Integer>();
            boolean possible = false;
            for(int i = 0; i < pow[n]; i++) {
                int sum = 0;
                int num = i;
                for(int j = 0; j < n; j++) {
                    int digit = num % 2;
                    sum += a[j] * digit;
                    num /= 2;
                }
                if(sums.contains(sum)) {
                    possible = true;
                    break;
                }
                sums.add(sum);   
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
