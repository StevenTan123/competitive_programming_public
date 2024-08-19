import java.util.*;
import java.io.*;

public class pichuAlchemy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        double[][] input = new double[2][4];
        for(int i = 0; i < 2; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < 4; j++) {
                input[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        int m = Integer.parseInt(in.readLine());
        double[] first = new double[3];
        for(int i = 0; i < 3; i++) {
            first[i] = (input[0][i + 1] / 100 * m + input[1][i + 1] / 100 * input[1][0]) / (input[1][0] + m);
        }
        for(int i = 0; i < 3; i++) {
            double fraction = (first[i] * m + input[0][i + 1] / 100 * (input[0][0] - m)) / input[0][0];
            out.print(fraction * 100 + " ");
        }
        out.println();
        for(int i = 0; i < 3; i++) {
            out.print(first[i] * 100 + " ");
        }
        out.println();
        in.close();
        out.close();
    }
}
