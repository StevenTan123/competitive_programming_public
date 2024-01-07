import java.util.*;
import java.io.*;

public class _466_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int a = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        long target = (long)6 * n;
        if((long)a * b >= target) {
            out.println((long)a * b);
            out.println(a + " " + b);
        }else {
            boolean found = false;
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            for(long i = target; i <= target + min; i++) {
                for(long j = min; j * j <= i; j++) {
                    if(i % j == 0) {
                        if(i / j >= max) {
                            out.println(i);
                            if(min == a) {
                                out.println(j + " " + i / j);
                            }else {
                                out.println(i / j + " " + j);
                            }
                            found = true;
                            break;
                        }
                    }
                }
                if(found) break;
            }
        }
        in.close();
        out.close();
    }
}