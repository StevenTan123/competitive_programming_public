import java.util.*;
import java.io.*;

public class _1499_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            String s = in.readLine();
            int n = s.length();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = Character.getNumericValue(s.charAt(i));
            boolean res = false;
            for(int i = 0; i <= n; i++) {
                int premove = -2;
                boolean possible = true;
                for(int j = 0; j < n; j++) {
                    if(j < i) {
                        if(a[j] == 1) {
                            if(j - premove > 1) {
                                premove = j;
                            }else {
                                possible = false;
                                break;
                            }
                        }
                    }else {
                        if(a[j] == 0) {
                            if(j - premove > 1) {
                                premove = j;
                            }else {
                                possible = false;
                                break;
                            }
                        }
                    }
                }
                if(possible) {
                    res = true;
                    break;
                }
            }
            out.println(res ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
