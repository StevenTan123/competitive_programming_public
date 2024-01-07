import java.util.*;
import java.io.*;

public class second_meaning {
    public static void main(String[] args) throws IOException {
        String[] codes = new String[512];
        for(int i = 0; i < 512; i++) {
            StringBuilder sb = new StringBuilder();
            int val = i;
            for(int j = 0; j < 9; j++) {
                sb.append(val % 2 == 0 ? '.' : '-');
                val /= 2;
            }
            codes[i] = sb.toString();
        }
        BufferedReader in = new BufferedReader(new FileReader("second_second_meaning_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String c1 = in.readLine();
            out.println("Case #" + t + ":");
            char start = c1.charAt(0) == '.' ? '-' : '.';
            for(int i = 0; i < n - 1; i++) {
                out.println(start + codes[i]);
            }
        }
        in.close();
        out.close();
    }
}
