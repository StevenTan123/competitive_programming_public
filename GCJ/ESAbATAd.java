import java.util.*;
import java.io.*;

public class ESAbATAd {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int tt = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        for(int t = 1; t <= tt; t++) {
            int[][] equal_pair = null;
            int[][] opp_pair = null;
            int[] arr = new int[b];
            Arrays.fill(arr, -1);
            int qcount = 0;
            int l = 0;
            int r = b - 1;
            while(l < r) {
                if(qcount > 0 && qcount % 10 == 0) {
                    boolean eq = equal_pair != null;
                    boolean op = opp_pair != null;
                    if(eq && op) {
                        boolean complement = complement(equal_pair, in);
                        boolean both = both(opp_pair, in);
                        if(both) {
                            if(complement) {
                                comp(arr);
                                reverse(arr);
                            }
                        }else {
                            if(complement) {
                                comp(arr);
                            }else {
                                reverse(arr);
                            }
                        }
                        if(complement) {
                            equal_pair[1][0] = 1 - equal_pair[1][0];
                            equal_pair[1][1] = 1 - equal_pair[1][1];
                        }
                        if(!both) {
                            opp_pair[1][0] = 1 - opp_pair[1][0];
                            opp_pair[1][1] = 1 - opp_pair[1][1];
                        }
                    }else if(eq) {
                        boolean complement = complement(equal_pair, in);
                        if(complement) {
                            comp(arr);
                            equal_pair[1][0] = 1 - equal_pair[1][0];
                            equal_pair[1][1] = 1 - equal_pair[1][1];
                        }
                        query(0, in);
                    }else {
                        boolean both = both(opp_pair, in);
                        if(!both) {
                            comp(arr);
                            opp_pair[1][0] = 1 - opp_pair[1][0];
                            opp_pair[1][1] = 1 - opp_pair[1][1];
                        }
                        query(0, in);
                    }
                }else {
                    int q1 = query(l, in);
                    int q2 = query(r, in);
                    if(q1 == q2 && equal_pair == null) {
                        equal_pair = new int[][]{{l, r}, {q1, q2}};
                    }else if(q1 != q2 && opp_pair == null) {
                        opp_pair = new int[][] {{l, r}, {q1, q2}};
                    }
                    arr[l] = q1;
                    arr[r] = q2;
                    l++;
                    r--;
                }
                qcount += 2;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < b; i++) {
                sb.append(arr[i]);
            }
            System.out.println(sb.toString());
            System.out.flush();
            String ok = in.readLine();
            if(ok.equals("N")) return;
        }
        in.close();
    }
    static boolean complement(int[][] eq, BufferedReader in) throws IOException {
        int l = query(eq[0][0], in);
        if(l == eq[1][0]) return false;
        return true;
    }
    static boolean both(int[][] op, BufferedReader in) throws IOException {
        int l = query(op[0][0], in);
        if(l == op[1][0]) return true;
        return false;
    }
    static void comp(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) arr[i] = 1;
            else if(arr[i] == 1) arr[i] = 0;
        }
    }
    static void reverse(int[] arr) {
        for(int i = 0; i * 2 < arr.length; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
    static int query(int index, BufferedReader in) throws IOException {
        System.out.println(index + 1);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }
}
