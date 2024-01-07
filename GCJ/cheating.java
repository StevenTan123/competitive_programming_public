import java.util.*;
import java.io.*;

public class cheating {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        int p = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int[][] scores = new int[100][10000];
            int[] count = new int[100];
            int max = 0;
            for(int i = 0; i < 100; i++) {
                String line = in.readLine();
                for(int j = 0; j < 10000; j++) {
                    scores[i][j] = Character.getNumericValue(line.charAt(j));
                    count[i] += scores[i][j];
                }
                if(count[i] > count[max]) max = i;
            }
            String res = "Case #" + t + ": " + (max + 1);
            out.println(res);
        }
        in.close();
        out.close();
    }
}
