import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class appendsort {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String[] x = new String[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = line.nextToken();
            }
            int ans = 0;
            for(int i = 1; i < n; i++) {
                if(!greater(x[i], x[i - 1])) {
                    ans += add(x, i);
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
    static int add(String[] x, int ind) {
        String a = x[ind - 1];
        String b = x[ind];
        BigInteger a2 = new BigInteger(a);
        a2 = a2.add(BigInteger.ONE);
        String stra = a2.toString();
        boolean match = true;
        for(int i = 0; i < b.length(); i++) {
            if(b.charAt(i) != stra.charAt(i)) {
                match = false;
                break;
            }
        }
        if(match) {
            x[ind] = stra;
            return stra.length() - b.length();
        }
        StringBuilder sb = new StringBuilder(b);
        for(int i = 0; i < a.length() - b.length(); i++) {
            sb.append('0');
        }
        if(greater(sb.toString(), a)) {
            x[ind] = sb.toString();
            return x[ind].length() - b.length();
        }
        sb.append('0');
        x[ind] = sb.toString();
        return x[ind].length() - b.length();
    }
    static boolean greater(String a, String b) {
        BigInteger a2 = new BigInteger(a);
        BigInteger b2 = new BigInteger(b);
        if(a2.compareTo(b2) > 0) return true;
        return false;
    }
}
