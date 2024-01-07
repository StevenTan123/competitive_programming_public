import java.util.*;
import java.io.*;

public class acidic {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int N = Integer.parseInt(in.readLine());
        int[][] freqs = new int[1000][2];
        for (int i = 0; i < 1000; i++) {
            freqs[i][1] = i;
        }
        for (int i = 0; i < N; i++) {
            freqs[Integer.parseInt(in.readLine()) - 1][0]++;
        }
        Arrays.sort(freqs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        if (freqs[0][0] == freqs[1][0]) {
            int min = 1000;
            int max = -1;
            for (int i = 0; i <= 1000; i++) {
                if (freqs[i][0] != freqs[0][0]) break;
                min = Math.min(min, freqs[i][1]);
                max = Math.max(max, freqs[i][1]);
            }
            out.println(max - min);
        } else {
            int min = 1000;
            int max = -1;
            for (int i = 1; i <= 1000; i++) {
                if (freqs[i][0] != freqs[1][0]) break;
                min = Math.min(min, freqs[i][1]);
                max = Math.max(max, freqs[i][1]);
            }
            out.println(Math.max(max - freqs[0][1], freqs[0][1] - min)); 
        }
        in.close();
        out.close();
    }
}
