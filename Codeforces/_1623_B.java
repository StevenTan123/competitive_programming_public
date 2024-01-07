import java.util.*;
import java.io.*;

public class _1623_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] ranges = new int[n][4];
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                ranges[i][0] = Integer.parseInt(line.nextToken());
                ranges[i][1] = Integer.parseInt(line.nextToken());
                ranges[i][2] = ranges[i][1] - ranges[i][0];
                ranges[i][3] = i;
            }
            Arrays.sort(ranges, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[2] - b[2];
                }
            });
            int[][] res = new int[n][3];
            for(int i = 0; i < n; i++) {
                int split = ranges[i][0];
                for(int j = i - 1; j >= 0; j--) {
                    if(ranges[j][0] == ranges[i][0]) {
                        split = ranges[j][1] + 1;
                        break;
                    }
                }
                res[ranges[i][3]] = new int[]{ranges[i][0], ranges[i][1], split};
            }
            for(int i = 0; i < n; i++) {
                out.println(res[i][0] + " " + res[i][1] + " " + res[i][2]);
            }
            out.println();
        }
        in.close();
        out.close();
    }
}
