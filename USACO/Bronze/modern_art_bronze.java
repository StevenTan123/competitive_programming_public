import java.util.*;
import java.io.*;

public class modern_art_bronze {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 10; k++) {
                    if (k == grid[i][j]) {
                        continue;
                    }
                    int[] boundsv = bounds(N, grid, k, true);
                    int[] boundsh = bounds(N, grid, k, false);
                    System.out.println(k + " " + boundsv[0] + " " + boundsv[1] + " " + boundsh[0] + " " + boundsh[1]);
                    if (i >= boundsv[0] && i <= boundsv[1] && j >= boundsh[0] && j <= boundsh[1]) {
                        possible[grid[i][j]] = false;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            if (possible[i]) {
                res++;
            }
        }
        out.println(res);

		in.close();
		out.close();
	}

    static int[] bounds(int N, int[][] grid, int num, boolean vert) {
        int min = N;
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == num) {
                    min = Math.min(min, vert ? i : j);
                    max = Math.max(max, vert ? i : j);
                }
            }
        }
        return new int[] { min, max };
    }
}
