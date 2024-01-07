import java.util.*;
import java.io.*;

public class _1626_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String x = in.readLine();
            int earliest = -1;
            int esum = -1;
            int latest = -1;
            int lsum = -1;
            for(int i = 1; i < x.length(); i++) {
                int prev = Character.getNumericValue(x.charAt(i - 1));
                int cur = Character.getNumericValue(x.charAt(i));
                if(cur + prev >= 10) {
                    latest = i;
                    lsum = cur + prev;
                }else {
                    if(earliest == -1) {
                        earliest = i;
                        esum = cur + prev;
                    }
                }
            }
            StringBuilder res = new StringBuilder();
            if(latest == -1) {
                res.append(x.substring(0, earliest - 1));
                res.append(esum);
                res.append(x.substring(earliest + 1));
            }else {
                res.append(x.substring(0, latest - 1));
                res.append(lsum);
                res.append(x.substring(latest + 1));
            }
            out.println(res.toString());
        }
        in.close();
        out.close();
    }
}
