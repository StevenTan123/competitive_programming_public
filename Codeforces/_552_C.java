import java.util.*;
import java.io.*;

public class _552_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int w = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        boolean possible = true;
        while(m > 0) {
            int digit = m % w;
            if(digit > 1) {
                digit -= w;
                m += w;
                if(digit < -1) {
                    possible = false;
                    break;
                }
            }
            m /= w;
        }
        out.println(possible ? "YES" : "NO");
        in.close();
        out.close();
    }
}
