import java.util.*;
import java.io.*;

public class _1494_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            String s = in.readLine();
            boolean possible = false;
            for(int j = 0; j < 8; j++) {
                int[] subset = new int[3];
                int counter = 2;
                int sub = j;
                while(sub > 0) {
                    subset[counter] = sub & 1;
                    sub = sub >>> 1;
                    counter--;
                }
                int[] a = new int[s.length()];
                for(int k = 0; k < s.length(); k++) {
                    if(s.charAt(k) == 'A') {
                        a[k] = subset[0];
                    }else if(s.charAt(k) == 'B') {
                        a[k] = subset[1];
                    }else {
                        a[k] = subset[2];
                    }
                }
                possible = possible || balanced(a);
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static boolean balanced(int[] a) {
        int depth = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == 0) depth++;
            else depth--;
            if(depth < 0) return false;
        }
        return depth == 0;
    }
}
