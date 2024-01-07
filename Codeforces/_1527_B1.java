import java.util.*;
import java.io.*;

public class _1527_B1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int zerocount = 0;
            boolean midzero = false;
            for(int i = 0; i < n; i++) {
                int cur = Character.getNumericValue(s.charAt(i));
                if(cur == 0) {
                    zerocount++;
                    if(n % 2 == 1 && i == n / 2) {
                        midzero = true;
                    }
                }
            }
            if(midzero) {
                if(zerocount == 1) {
                    out.println("BOB");
                }else {
                    out.println("ALICE");
                }
            }else {
                out.println("BOB");
            }
        }
        in.close();
        out.close();
    }
}
