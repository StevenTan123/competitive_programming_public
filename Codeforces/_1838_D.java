import java.util.*;
import java.io.*;

public class _1838_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] seq = new int[n];
        String bs = in.readLine();
        TreeSet<Repeat> reps = new TreeSet<Repeat>();
        for (int i = 0; i < n; i++) {
            if (bs.charAt(i) == ')') {
                seq[i] = 1;
            }
            if (i > 0 && seq[i - 1] == seq[i]) {
                reps.add(new Repeat(i - 1, seq[i]));
            }
        }
        

        for (int i = 0; i < q; i++) {
            int flip = Integer.parseInt(in.readLine()) - 1;
            if (n % 2 == 1) {
                out.println("NO");
                continue;
            }
            if (flip > 0) {
                if (seq[flip - 1] == seq[flip]) {
                    reps.remove(new Repeat(flip - 1, 0));
                } else {
                    reps.add(new Repeat(flip - 1, seq[flip - 1]));
                }
            }
            if (flip < n - 1) {
                if (seq[flip + 1] == seq[flip]) {
                    reps.remove(new Repeat(flip, 0));
                } else {
                    reps.add(new Repeat(flip, seq[flip + 1]));
                }
            }
            seq[flip] = 1 - seq[flip];

            int tfirst = reps.size() > 0 ? reps.first().type : -1;
            int tlast = reps.size() > 0 ? reps.last().type : -1;
            if (seq[0] == 0 && seq[n - 1] == 1 && (tfirst == 0 && tlast == 1 || (tfirst == -1 && tlast == -1))) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        in.close();
        out.close();
    }
    static class Repeat implements Comparable<Repeat> {
        int ind, type;
        Repeat(int i, int t) {
            ind = i;
            type = t;
        }
        @Override
        public int compareTo(Repeat o) {
            return ind - o.ind;
        }
    } 
}
