import java.util.*;
import java.io.*;

public class _1620_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int max = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                max = Math.max(max, a[i]);
            }
            int min = Integer.MAX_VALUE;
            int threes = max / 3;
            for(int i = threes - 1; i <= threes; i++) {
                for(int j = 0; j <= 2; j++) {
                    for(int k = 0; k <= 2; k++) {
                        if(valid(i, j, k, a)) {
                            min = Math.min(min, i + j + k);
                        }
                    }
                }
            }
            out.println(min);
        }
        in.close();
        out.close();
    }
    static boolean valid(int threes, int twos, int ones, int[] a) {
        for(int i = 0; i < a.length; i++) {
            if(threes >= a[i] / 3) {
                int left = a[i] % 3;
                if(left == 1) {
                    if(ones == 0 && (twos < 2 || a[i] == 1)) {
                        return false;
                    }
                }else if(left == 2) {
                    if(twos == 0 && ones < 2) {
                        return false;
                    }
                }
            }else {
                if(threes * 3 + twos * 2 + ones < a[i]) {
                    return false;
                }
                int left = a[i] - threes * 3;
                if(left % 2 == 1 && ones == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
