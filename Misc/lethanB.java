import java.util.*;
import java.io.*;

public class lethanB {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] a = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            String type = line.nextToken();
            a[i][0] = Integer.parseInt(line.nextToken());
            if(type.equals("C")) {
                a[i][1] = 1;
            }
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int res = 0;
        int p = 1;
        boolean[] paired = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(paired[i]) {
                continue;
            }
            while((a[p][0] - a[i][0]) % 2 == 0 || paired[p]) {
                p++;
            }
            paired[p] = true;
            if(a[i][1] == a[p][1]) {
                res++;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}