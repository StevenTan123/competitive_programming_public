import java.util.*;
import java.io.*;

public class santa_claus {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("here_comes_santa_claus_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            int[] X = new int[N];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Integer.parseInt(line.nextToken());
            }

            Arrays.sort(X);
            String res = "Case #" + t + ": ";
            out.print(res);
            if (N == 5) {
                double mid1 = (double) (X[0] + X[2]) / 2;
                double mid2 = (double) (X[N - 2] + X[N - 1]) / 2;
                
                double mid3 = (double) (X[0] + X[1]) / 2;
                double mid4 = (double) (X[N - 3] + X[N - 1]) / 2;
                double ans = Math.max(mid2 - mid1, mid4 - mid3);
                out.printf("%.9f", ans);
            } else {
                double mid1 = (double) (X[0] + X[1]) / 2;
                double mid2 = (double) (X[N - 2] + X[N - 1]) / 2;
                out.printf("%.9f", mid2 - mid1);
            }
            out.println();
        }
        
        in.close();
        out.close();
    }
}
