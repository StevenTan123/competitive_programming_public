import java.util.*;
import java.io.*;

public class sum_41_chapter_2 {
    static ArrayList<Integer> ans;
    static int ans_sum;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sum_41_chapter_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            System.out.println(t);
            ans = null;
            ans_sum = 0;

            int P = Integer.parseInt(in.readLine());
            recurse(new ArrayList<Integer>(), 0, 1, P);

            String res = "Case #" + t + ": ";
            if (ans == null) {
                out.println(res + -1);
            } else {
                for (int i = 0; i < 41 - ans_sum; i++) {
                    ans.add(1);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ans.size());
                sb.append(' ');
                for (int val : ans) {
                    sb.append(val);
                    sb.append(' ');
                }
                out.println(res + sb.toString());
            }
        }
        
        in.close();
        out.close();
    }
    static void recurse(ArrayList<Integer> cur, int sum, long prod, int P) {
        if (prod == P) {
            if (ans == null || cur.size() + 41 - sum < ans.size() + 41 - ans_sum) {
                ans = new ArrayList<Integer>();
                for (int val : cur) {
                    ans.add(val);
                }
                ans_sum = sum;
            }
        }
        
        for (int i = 2; i <= 41 - sum; i++) {
            if (P % (prod * i) == 0) {
                cur.add(i);
                recurse(cur, sum + i, prod * i, P);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
