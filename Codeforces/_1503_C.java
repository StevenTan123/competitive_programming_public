import java.util.*;
import java.io.*;

public class _1503_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] cities = new int[n][2];
        long base = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            cities[i][0] = Integer.parseInt(line.nextToken());
            cities[i][1] = Integer.parseInt(line.nextToken());
            base += cities[i][1];
        }
        Arrays.sort(cities, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int res = 0;
        int reach = cities[0][0];
        for(int i = 0; i < n; i++) {
            if(cities[i][0] <= reach) {
                reach = Math.max(reach, cities[i][0] + cities[i][1]);
            }else {
                res += cities[i][0] - reach;
                reach = cities[i][0] + cities[i][1];
            }
        }
        out.println(base + res);
        in.close();
        out.close();
    }
}
