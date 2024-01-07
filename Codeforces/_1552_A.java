import java.util.*;
import java.io.*;

public class _1552_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            char[] c = new char[n];
            char[] csort = new char[n];
            for(int i = 0; i < n; i++) {
                c[i] = s.charAt(i);
                csort[i] = s.charAt(i);
            }
            Arrays.sort(csort);
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(c[i] == csort[i]) {
                    count++;
                }
            }
            out.println(n - count);
        }
        in.close();
        out.close();
    }
}
