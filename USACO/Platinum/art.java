import java.util.*;
import java.io.*;

public class art {
	public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("art.in"));
        PrintWriter out = new PrintWriter("art.out");
        int n = Integer.parseInt(in.readLine());
        int[][] a = new int[n][n];
        int[][] bounds = new int[n * n + 1][4];
        for(int i = 1; i <= n * n; i++) {
            bounds[i] = new int[] {1000, -1, 1000, -1};
        }
        HashSet<Integer> unique = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(line.nextToken());
                if(a[i][j] != 0) {
                    unique.add(a[i][j]);
                }
                bounds[a[i][j]][0] = Math.min(bounds[a[i][j]][0], i);
                bounds[a[i][j]][1] = Math.max(bounds[a[i][j]][1], i);
                bounds[a[i][j]][2] = Math.min(bounds[a[i][j]][2], j);
                bounds[a[i][j]][3] = Math.max(bounds[a[i][j]][3], j);
            }
        }
        int[][] covered = new int[n + 1][n + 1];
        for(int i = 1; i <= n * n; i++) {
            if(bounds[i][0] == 1000) {
                continue;
            }
            covered[bounds[i][0]][bounds[i][2]]++;
            covered[bounds[i][1] + 1][bounds[i][2]]--;
            covered[bounds[i][0]][bounds[i][3] + 1]--;
            covered[bounds[i][1] + 1][bounds[i][3] + 1]++;
        }
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += covered[j][i];
                covered[j][i] = sum;
            }
        }
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += covered[i][j];
                covered[i][j] = sum;
            }
        }
        boolean[] first = new boolean[n * n + 1];
        Arrays.fill(first, true);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(covered[i][j] > 1) {
                    first[a[i][j]] = false;
                }
            }
        }
        int res = 0;
        for(int i = 1; i <= n * n; i++) {
            if(first[i]) {
                res++;
            }
        }
        if(unique.size() == 1) {
            res--;
        }
        out.println(res);
		in.close();
		out.close();
	}
}
