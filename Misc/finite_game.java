import java.util.*;
import java.io.*;

public class finite_game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int[] vals = new int[3];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 3; i++) {
                vals[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(vals);
            if(vals[1] == vals[2]) {
                out.println("YES");
            }else {
                if(vals[2] - vals[1] == 2 && vals[0] == vals[1]) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
}
