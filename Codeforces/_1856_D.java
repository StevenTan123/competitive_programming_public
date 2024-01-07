import java.util.*;
import java.io.*;

public class _1856_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());

            int res = max_ind(1, n, in);
            System.out.println("! " + res);
            System.out.flush();
        }
        
        in.close();
    }
    static int max_ind(int l, int r, BufferedReader in) throws IOException {
        if (l == r) {
            return l;
        }
        int m = (r + l) / 2;
        int i = max_ind(l, m, in);
        int j = max_ind(m + 1, r, in);
        
        System.out.println("? " + l + " " + j);
        System.out.flush();
        int inv1 = Integer.parseInt(in.readLine());
        int inv2 = 0;
        if (j > l + 1) {
            System.out.println("? " + l + " " + (j - 1));
            System.out.flush();
            inv2 = Integer.parseInt(in.readLine());
        }
        if (inv1 == inv2) {
            return j;
        } else {
            return i;
        }
    }
}
