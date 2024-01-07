import java.util.*;
import java.io.*;

public class _1062_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        long sum = 0;
        for(int i = 2; i <= n; i++) {
            for(int j = i * 2; j <= n; j += i) {
                sum += i;
            }
        }
        out.println(sum * 4);
        in.close();
        out.close();
    }
}
