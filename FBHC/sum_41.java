import java.util.*;
import java.io.*;

public class sum_41 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sum_41_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        
        for (int t = 1; t <= tt; t++) {
            int P = Integer.parseInt(in.readLine());
            int factor_sum = 0;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 2; i * i <= P; i++) {
                while (P % i == 0) {
                    arr.add(i);
                    factor_sum += i;
                    P /= i;
                }
            }
            if (P > 1) {
                arr.add(P);
                factor_sum += P;
            }
            String res = "Case #" + t + ": ";
            if (factor_sum > 41) {
                out.println(res + "-1");
            } else {
                for (int i = 0; i < 41 - factor_sum; i++) {
                    arr.add(1);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(arr.size());
                sb.append(' ');
                for (int i = 0; i < arr.size(); i++) {
                    sb.append(arr.get(i));
                    sb.append(' ');
                }
                out.println(res + sb.toString());
            }

        }
        
        in.close();
        out.close();
    }
}
