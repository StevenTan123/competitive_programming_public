import java.util.*;
import java.io.*;

public class _1562_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            boolean found = false;
            for(int i = 0; i < n; i++) {
                int digit = Character.getNumericValue(s.charAt(i));
                if(digit == 0) {
                    if(i < n / 2) {
                        found = true;
                        out.println((i + 1) + " " + n + " " + (i + 2) + " " + n);
                    }else {
                        found = true;
                        out.println(1 + " " + (i + 1) + " " + 1 + " " + i);
                    }
                    break;
                }
            }
            if(found) {
                continue;
            }
            out.println(1 + " " + (n - 1) + " " + 2 + " " + n);
        }
        in.close();
        out.close();
    }
}
