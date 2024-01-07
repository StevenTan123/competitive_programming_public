import java.util.*;
import java.io.*;

public class trianglane {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int C = Integer.parseInt(in.readLine());
        int[][] tiles = new int[2][C];
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        int count = 0;
        for (int i = 0; i < C; i++) {
            tiles[0][i] = Integer.parseInt(line1.nextToken());
            tiles[1][i] = Integer.parseInt(line2.nextToken());
            count += tiles[0][i] + tiles[1][i];
        }
        
        int adj = 0;
        for (int i = 0; i < C; i++) {
            if (i % 2 == 0 && tiles[0][i] == 1 && tiles[1][i] == 1) {
                adj++;
            }
            if (i < C - 1 && tiles[0][i] == 1 && tiles[0][i + 1] == 1) {
                adj++;
            }
            if (i < C - 1 && tiles[1][i] == 1 && tiles[1][i + 1] == 1) {
                adj++;
            }
        }

        out.println(count * 3 - adj * 2);

        in.close();
        out.close();
    }
}
