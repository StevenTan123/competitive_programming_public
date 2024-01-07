import java.util.*;
import java.io.*;

public class tidy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String n = in.readLine();
            int[] a = new int[n.length()];
            for(int i = 0; i < n.length(); i++) a[i] = Character.getNumericValue(n.charAt(i));
            int latest = a.length;
            for(int i = a.length - 1; i > 0; i--) {
                if(a[i] < a[i - 1]) {
                    a[i] = 9;
                    a[i - 1]--;
                    latest = i;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < a.length; i++) {
                if(i >= latest) a[i] = 9;
                if(i > 0 || (i == 0 && a[i] > 0)) sb.append(a[i]);
            }
            String res = "Case #" + t + ": " + sb.toString();
            out.println(res);
        }
        in.close();
        out.close();
    }
}
