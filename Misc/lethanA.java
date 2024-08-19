import java.util.*;
import java.io.*;

public class lethanA {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] bubbles = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            bubbles[i][0] = Integer.parseInt(line.nextToken());
            bubbles[i][1] = Integer.parseInt(line.nextToken());
        }
        boolean good = true;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int dist = (bubbles[j][0] - bubbles[i][0]) * (bubbles[j][0] - bubbles[i][0]) + 
                           (bubbles[j][1] - bubbles[i][1]) * (bubbles[j][1] - bubbles[i][1]);
                if(dist < 4) {
                    good = false;
                    break;
                }
            }
        }
        out.println(good ? "YES" : "NO");
        in.close();
        out.close();
    }
}
