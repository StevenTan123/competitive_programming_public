import java.util.*;
import java.io.*;

public class pair_programming {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String b_str = in.readLine();
            String e_str = in.readLine();
            ArrayList<Integer> bb = new ArrayList<Integer>();
            ArrayList<Integer> eb = new ArrayList<Integer>();
            for(int i = n - 1; i >= 0; i--) {
                char bc = b_str.charAt(i);
                char ec = e_str.charAt(i);
                if(bb.size() == 0 || bb.get(bb.size() - 1) != 0) {
                    if(bc == '+') {
                        bb.add(-1);
                    }else {
                        int digit = Character.getNumericValue(bc);
                        if(digit != 1) {
                            bb.add(digit);
                        }
                    }
                }
                if(eb.size() == 0 || eb.get(eb.size() - 1) != 0) {
                    if(ec == '+') {
                        eb.add(-1);
                    }else {
                        int digit = Character.getNumericValue(ec);
                        if(digit != 1) {
                            eb.add(digit);
                        }
                    }
                }
            }
            bb.add(0);
            eb.add(0);
            int bp = bb.size();
            int ep = eb.size();
            int[] b = new int[bp];
            for(int i = bp - 1; i >= 0; i--) {
                b[bp - i - 1] = bb.get(i);
            }
            int[] e = new int[ep];
            for(int i = ep - 1; i >= 0; i--) {
                e[ep - i - 1] = eb.get(i);
            }
            long[][][] dp = new long[bp][ep][4];
            dp[0][0][0] = 1;
            for(int i = 0; i < bp; i++) {
                for(int j = 0; j < ep; j++) {
                    if(i < bp - 1) {
                        if(b[i + 1] == 0) {
                            if(dp[i][j][0] > 0 || dp[i][j][2] > 0 || dp[i][j][3] > 0) {
                                dp[i + 1][j][0] = 1;
                            }
                        }else if(b[i + 1] == -1) {
                            dp[i + 1][j][2] = add(dp[i][j][0], add(dp[i][j][2], dp[i][j][1]));
                        }else {
                            dp[i + 1][j][0] = add(dp[i][j][0], add(dp[i][j][2], dp[i][j][3]));
                        }
                    } 
                    if(j < ep - 1) {
                        if(e[j + 1] == 0) {
                            if(dp[i][j][0] > 0 || dp[i][j][1] > 0 || dp[i][j][2] > 0 || dp[i][j][3] > 0) {
                                dp[i][j + 1][1] = 1;
                            }
                        }else if(e[j + 1] == -1) {
                            dp[i][j + 1][3] = add(add(dp[i][j][0], dp[i][j][2]), add(dp[i][j][1], dp[i][j][3]));
                        }else {
                            dp[i][j + 1][1] = add(add(dp[i][j][0], dp[i][j][2]), add(dp[i][j][1], dp[i][j][3]));
                        }
                    }
                }
            }
            long res = 0;
            for(int i = 0; i < 4; i++) {
                res = add(res, dp[bp - 1][ep - 1][i]);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long add(long a, long b) {
        long res = a + b;
        if(a + b >= MOD) {
            res -= MOD;
        }
        return res;
    }
}
