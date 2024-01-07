import java.util.*;
import java.io.*;

public class _1527_B2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int paldist = 0;
            int numzeroes = 0;
            for(int i = 0; i < n / 2; i++) {
                int a = Character.getNumericValue(s.charAt(i));
                int b = Character.getNumericValue(s.charAt(n - i - 1));
                if(a != b) {
                    paldist++;
                }else if(a == 0) {
                    numzeroes += 2;
                }
            }
            boolean midzero = false;
            if(n % 2 == 1 && Character.getNumericValue(s.charAt(n / 2)) == 0) {
                midzero = true;
                numzeroes++;
            }
            if(paldist == 0) {
                if(midzero) {
                    if(numzeroes == 1) {
                        out.println("BOB");
                    }else {
                        out.println("ALICE");
                    }
                }else {
                    out.println("BOB");
                }
            }else {
                if(midzero) {
                    if(paldist == 1 && numzeroes == 1) {
                        out.println("DRAW");
                    }else {
                        out.println("ALICE");
                    }
                }else {
                    out.println("ALICE");
                }
            }
        }
        in.close();
        out.close();
    }
}
