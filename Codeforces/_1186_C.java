import java.util.*;
import java.io.*;

public class _1186_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String a = in.readLine();
        String b = in.readLine();
        int[] aa = new int[a.length()];
        int[] ba = new int[b.length()];
        for(int i = 0; i < a.length(); i++) {
            aa[i] = Character.getNumericValue(a.charAt(i));
            if(i < b.length()) ba[i] = Character.getNumericValue(b.charAt(i));
        }
        long diffs = 0;
        long diffsa = 0;
        for(int i = 0; i < Math.min(a.length(), b.length() + 1); i++) {
            if(i < b.length() && aa[i] != ba[i]) diffs++;
            if(i > 0 && aa[i] != aa[i - 1]) diffsa++;
        }
        int res = diffs % 2 == 0 ? 1 : 0;
        for(int i = 1; i < a.length() - b.length() + 1; i++) {
            diffs += diffsa;
            res += diffs % 2 == 0 ? 1 : 0;
            if(aa[i] != aa[i - 1]) diffsa++;
            if(i + b.length() < a.length() && aa[i + b.length()] != aa[i + b.length() - 1]) diffsa++;
        }
        out.println(res);
        in.close();
        out.close();
    }
}
