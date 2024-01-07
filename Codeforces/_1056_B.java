import java.util.*;
import java.io.*;

public class _1056_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        long res = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if((i * i + j * j) % m == 0) {
                    int numi = (n - i) / m + 1;
                    int numj = (n - j) / m + 1;
                    if(i == 0 || i > n) numi--;
                    if(j == 0 || j > n) numj--;
                    res += (long)numi * numj;
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
