import java.util.*;
import java.io.*;

public class _1644_B {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int ceil = n / 2;
            if(n % 2 != 0) {
                ceil++;
            }
            StringBuilder end = new StringBuilder();
            for(int i = ceil - 1; i >= 1; i--) {
                end.append(i);
                end.append(' ');
            }
            count = 0;
            gen_perm(ceil, n, new ArrayList<Integer>(), new boolean[n + 1], end);
            if(n == 3) {
                System.out.println("3 1 2");
            }
        }
        in.close();
    }
    static void gen_perm(int l, int r, ArrayList<Integer> perm, boolean[] used, StringBuilder end) {
        if(count >= r) {
            return;
        }
        if(perm.size() == r - l + 1) {
            StringBuilder res = new StringBuilder();
            for(int val : perm) {
                res.append(val);
                res.append(' ');
            }
            res.append(end);
            System.out.println(res.toString());
            count++;
        }
        for(int i = l; i <= r; i++) {
            if(!used[i]) {
                used[i] = true;
                perm.add(i);
                gen_perm(l, r, perm, used, end);
                used[i] = false;
                perm.remove(perm.size() - 1);
            }
        }
    }
}
