import java.util.*;
import java.io.*;

public class _1523_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(in.readLine());
            }
            boolean[][] used = new boolean[n][n + 1];
            boolean[] used2 = new boolean[n];
            String[][] res = new String[n][2];
            res[0][0] = "1";
            res[0][1] = "";
            for(int i = 1; i < n; i++) {
                if(a[i] == 1) {
                    for(int j = i - 1; j >= 0; j--) {
                        if(!used[j][a[i]] && !used2[j]) {
                            res[i][0] = res[j][0] + ".1";
                            res[i][1] = res[j][0];
                            used[j][a[i]] = true;
                            break;
                        }
                    }
                }else {
                    for(int j = i - 1; j >= 0; j--) {
                        if(!used[j][a[i]] && !used2[j] && a[j] == a[i] - 1) {
                            if(res[j][1].equals("")) {
                                res[i][0] = "" + a[i];
                            }else {
                                res[i][0] = res[j][1] + "." + a[i];
                            }
                            res[i][1] = res[j][1];
                            used[j][a[i]] = true;
                            break;
                        }
                        used2[j] = true;
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                out.println(res[i][0]);
            }
        }
        in.close();
        out.close();
    }
}
