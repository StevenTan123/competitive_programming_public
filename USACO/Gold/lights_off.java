import java.util.*;
import java.io.*;

public class lights_off {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int T = Integer.parseInt(line.nextToken());
        int N = Integer.parseInt(line.nextToken());
        int X = 1 << N;

        // cons[i][j] stores the bitmask containing i consecutive 1's starting at j, and wraps when needed
        int[][] cons = new int[3 * N + 1][N];
        for (int i = 0; i <= 3 * N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < i; k++) {
                    int ind = j - k;
                    while (ind < 0) {
                        ind += N;
                    }
                    cons[i][j] ^= 1 << ind;
                }
            }
        }

        // dp[i][j] stores if its possible to reach the bitmask j of lights in i moves
        boolean[][] dp = new boolean[3 * N + 1][X];
        dp[0][0] = true;
        for (int i = 0; i < 3 * N; i++) {
            for (int j = 0; j < X; j++) {
                if (dp[i][j]) {
                    for (int k = 0; k < N; k++) {
                        dp[i + 1][j ^ cons[i + 1][k]] = true;
                    }
                }
            }
        }

        for (int i = 0; i < T; i++) {
            line = new StringTokenizer(in.readLine());
            int lights = Integer.parseInt(line.nextToken(), 2);
            int switches = Integer.parseInt(line.nextToken(), 2);
            int res = -1;
            
            // For each j, check if it's possible to turn off in j moves
            for (int j = 0; j <= 3 * N; j++) {
                int bitmask = lights;
                int num = switches;
                int bit = 0;
                while (num > 0) {
                    if (num % 2 == 1) {
                        bitmask ^= cons[j][bit];
                    }
                    num >>= 1;
                    bit++;
                }
                if (dp[j][bitmask]) {
                    res = j;
                    break;
                }
            }
            out.println(res);
        }

        in.close();
        out.close();
    }
}
