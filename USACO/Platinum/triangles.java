import java.util.*;
import java.io.*;

public class triangles {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter("triangles.out");
        int n = Integer.parseInt(in.readLine());
        int[][] points = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            points[i][0] = Integer.parseInt(line.nextToken());
            points[i][1] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        int[] count = new int[1000005];
        int p = 0;
        for(int i = 0; i < 1000005; i++) {
            while(p < n && points[p][0] == i) {
                p++;
            }
            count[i] = p;
        }
        int[][] above = new int[n][n];
        int[][] below = new int[n][n];
        int[] above2 = new int[n];
        int[] below2 = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j || points[i][0] != points[j][0]) {
                    continue;
                }
                if(points[j][1] > points[i][1]) {
                    above2[i]++;
                }else {
                    below2[i]++;
                }
            }
            for(int j = i + 1; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(i != j && k != i && k != j && points[k][0] >= points[i][0] && points[k][0] <= points[j][0]) {
                        if(points[i][0] == points[j][0]) {
                            continue;
                        }
                        double LHS = points[k][1] - points[i][1];
                        double RHS = (double)(points[j][1] - points[i][1]) / (points[j][0] - points[i][0]) * (points[k][0] - points[i][0]);
                        if(LHS > RHS) {
                            above[i][j]++;
                        }else {
                            below[i][j]++;
                        }
                    }
                }
            }
        }
        int[] res = new int[n - 2];
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    int[] v1 = points[i];
                    int[] v2 = points[j];
                    int[] v3 = points[k];
                    double m1 = slope(v1, v2);
                    double m2 = slope(v2, v3);
                    int total = count[v3[0]] - (v1[0] > 0 ? count[v1[0] - 1] : 0) - 3;
                    if(m2 > m1) {
                        total -= above[i][k];
                        total -= below[i][j];
                        total -= below[j][k];
                        total += below2[j];
                    }else {
                        total -= below[i][k];
                        total -= above[i][j];
                        total -= above[j][k];
                        total += above2[j];
                    }
                    res[total]++;
                }
            }
        }
        for(int i = 0; i < n - 2; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static double slope(int[] p1, int[] p2) {
        if(p1[0] == p2[0]) {
            return Double.MAX_VALUE;
        }
        return (double)(p2[1] - p1[1]) / (p2[0] - p1[0]);
    }
}
