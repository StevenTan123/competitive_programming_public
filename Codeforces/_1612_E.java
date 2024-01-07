import java.util.*;
import java.io.*;

public class _1612_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());

        //for every message stores the students who need to see it, and their k values
        HashMap<Integer, ArrayList<Integer>> by_msg = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            if(!by_msg.containsKey(m)) {
                by_msg.put(m, new ArrayList<Integer>());
            }
            by_msg.get(m).add(k);
        }

        int[] best_msg = new int[0];
        double best_exp = 0;
        for(int t = 1; t <= Math.min(20, by_msg.size()); t++) {
            double[][] cur_vals = new double[by_msg.size()][2];
            int p = 0;
            for(int msg : by_msg.keySet()) {
                double contr = 0;
                ArrayList<Integer> k_vals = by_msg.get(msg);
                for(int k_val : k_vals) {
                    contr += Math.min(1, (double)k_val / t);
                }
                cur_vals[p][0] = contr;
                cur_vals[p][1] = msg;
                p++;
            }
            Arrays.sort(cur_vals, new Comparator<double[]>() {
                @Override
                public int compare(double[] a, double[] b) {
                    if(Math.abs(a[0] - b[0]) < 1e-6) {
                        return 0;
                    }else if(a[0] > b[0]) {
                        return -1;
                    }else {
                        return 1;
                    }
                }
            });
            double cur_exp = 0;
            int[] cur_msg = new int[t];
            for(int i = 0; i < t; i++) {
                cur_exp += cur_vals[i][0];
                cur_msg[i] = (int)cur_vals[i][1];
            }
            if(cur_exp > best_exp) {
                best_exp = cur_exp;
                best_msg = cur_msg;
            }
        }

        out.println(best_msg.length);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < best_msg.length; i++) {
            sb.append(best_msg[i]);
            sb.append(' ');
        }
        out.println(sb.toString());

        in.close();
        out.close();
    }
}
