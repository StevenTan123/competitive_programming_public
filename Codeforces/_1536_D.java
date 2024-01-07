import java.util.*;
import java.io.*;

public class _1536_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] b = new int[n];
            TreeSet<Integer> sorted = new TreeSet<Integer>();
            StringTokenizer line = new StringTokenizer(in.readLine());
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(line.nextToken());
                if(i > 0 && b[i] > b[i - 1]) {
                    Integer higher = sorted.higher(b[i - 1]);
                    if(higher != null && higher < b[i]) {
                        possible = false;
                        break;
                    }
                }else if(i > 0 && b[i] < b[i - 1]) {
                    Integer lower = sorted.lower(b[i - 1]);
                    if (lower != null && lower > b[i]) {
                        possible = false;
                        break;
                    }
                }
                sorted.add(b[i]);
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
