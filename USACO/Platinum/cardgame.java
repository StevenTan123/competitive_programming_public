import java.util.*;
import java.io.*;

public class cardgame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter out = new PrintWriter("cardgame.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] a2 = new int[n];
        HashSet<Integer> aa = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine());
            a2[n - i - 1] = -a[i];
            aa.add(a[i]);
        }
        TreeSet<Integer> b = new TreeSet<Integer>();
        TreeSet<Integer> b2 = new TreeSet<Integer>();
        for(int i = 1; i <= 2 * n; i++) {
            if(!aa.contains(i)) {
                b.add(i);
                b2.add(-i);
            }
        }
        int[] pre = prefix(a, b);
        int[] suf = prefix(a2, b2);
        int max = Math.max(pre[n - 1], suf[n - 1]);
        for(int i = 0; i < n - 1; i++) {
            max = Math.max(max, pre[i] + suf[n - i - 2]);
        }
        out.println(max);
        in.close();
        out.close();
    }
    static int[] prefix(int[] a, TreeSet<Integer> b) {
        int[] ret = new int[a.length];
        int score = 0;
        for(int i = 0; i < a.length; i++) {
            Integer higher = b.higher(a[i]);
            if(higher != null) {
                score++;
                b.remove(higher);
            }
            ret[i] = score;
        }
        return ret;
    }
}
