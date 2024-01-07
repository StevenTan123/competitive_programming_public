import java.util.*;
import java.io.*;

public class threeD_printing {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int[][] vals = new int[3][4];
            int[] min = new int[4];
            Arrays.fill(min, Integer.MAX_VALUE);
            for(int i = 0; i < 3; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                for(int j = 0; j < 4; j++) {
                    vals[i][j] = Integer.parseInt(line.nextToken());
                    min[j] = Math.min(min[j], vals[i][j]);
                }
            }
            String res = "Case #" + t + ": ";
            int sum = 0;
            for(int i = 0; i < 4; i++) {
                sum += min[i];
            }
            if(sum < 1000000) {
                out.println(res + "IMPOSSIBLE");
            }else {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < 4; i++) {
                    int sub = Math.min(sum - 1000000, min[i]);
                    sb.append(min[i] - sub);
                    sb.append(' ');
                    sum -= sub;
                }
                out.println(res + sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
