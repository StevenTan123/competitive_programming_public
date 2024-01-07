import java.util.*;
import java.io.*;

public class on_the_run {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("on_the_run_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][] coords = new int[k + 1][2];
            for(int i = 0; i < k + 1; i++) {
                line = new StringTokenizer(in.readLine());
                coords[i][0] = Integer.parseInt(line.nextToken());
                coords[i][1] = Integer.parseInt(line.nextToken());
            }
            String res = "Case #" + t + ": ";
            if(k == 1) {
                out.println(res + 'N');
            }else {
                int color1 = (coords[0][0] + coords[0][1]) % 2;
                int color2 = (coords[1][0] + coords[1][1]) % 2;
                int color3 = (coords[2][0] + coords[2][1]) % 2;
                if(color1 == color2 && color1 == color3) {
                    out.println(res + 'Y');
                }else {
                    out.println(res + 'N');
                }
            }
        }
        in.close();
        out.close();
    }
}
