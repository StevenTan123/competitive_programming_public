import java.util.*;
import java.io.*;

public class travel_restrictions {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("travel_restrictions_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            boolean[] I = new boolean[n];
            boolean[] O = new boolean[n];
            String line = in.readLine();
            for(int i = 0; i < n; i++) {
                I[i] = line.charAt(i) == 'Y' ? true : false;
            }
            line = in.readLine();
            for (int i = 0; i < n; i++) {
                O[i] = line.charAt(i) == 'Y' ? true : false;
            }
            int comp = 0;
            int[] left = new int[n];
            for(int i = 1; i < n; i++) {
                if(!O[i - 1] || !I[i]) {
                    comp++;
                    left[i] = comp;
                }else {
                    left[i] = comp;
                }
            }
            comp = 0;
            int[] right = new int[n];
            for(int i = n - 2; i >= 0; i--) {
                if(!O[i + 1] || !I[i]) {
                    comp++;
                    right[i] = comp;
                }else {
                    right[i] = comp;
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res);
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if(i <= j) {
                        sb.append(left[i] == left[j] ? 'Y' :'N');
                    }else {
                        sb.append(right[i] == right[j] ? 'Y' : 'N');
                    }
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
