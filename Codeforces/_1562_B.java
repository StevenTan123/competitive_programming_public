import java.util.*;
import java.io.*;

public class _1562_B {
    static int[] pow = new int[5];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        for(int i = 1; i < 5; i++) {
            pow[i] = pow[i - 1] * 2;
        } 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int k = Integer.parseInt(in.readLine());
            String n = in.readLine();
            int len = Integer.MAX_VALUE;
            int res = Integer.MAX_VALUE;
            HashSet<Integer> digits = new HashSet<Integer>();
            for(int i = 0; i < k; i++) {
                int digit = Character.getNumericValue(n.charAt(i));
                if(!primeDigit(digit)) {
                    len = 1;
                    res = digit;
                    break;
                }
                if(digits.contains(digit)) {
                    len = 2;
                    res = digit * 10 + digit;
                }
                digits.add(digit);
            }
            if(len != Integer.MAX_VALUE) {
                out.println(len);
                out.println(res);
                continue;
            }
            int num = Integer.parseInt(n);
            for(int i = 1; i < pow[k]; i++) {
                int cur = num;
                int curi = i;
                String curans = "";
                for(int j = 0; j < k; j++) {
                    int digit = cur % 10;
                    int use = curi % 2;
                    if(use == 1) {
                        curans = digit + curans;
                    }
                    cur /= 10;
                    curi /= 2;
                }
                int curres = Integer.parseInt(curans);
                if(!isPrime(curres)) {
                    if(curans.length() < len) {
                        len = curans.length();
                        res = curres;
                    }
                }
            }
            out.println(len);
            out.println(res);
        }
        in.close();
        out.close();
    }
    static boolean primeDigit(int a) {
        return a == 2 || a == 3 || a == 5 || a == 7;
    }
    static boolean isPrime(int a) {
        for(int i = 2; i * i <= a; i++) {
            if(a % i == 0) {
                return false;
            }
        }
        return true;
    }
}
