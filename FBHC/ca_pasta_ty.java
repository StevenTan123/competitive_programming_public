import java.util.*;
import java.io.*;

public class ca_pasta_ty {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("capastaty_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());

            int[] s = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(line.nextToken());
            }

            int[] conss = new int[4];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 4; i++) {
                conss[i] = Integer.parseInt(line.nextToken());
            }

            int[] x = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < k; i++) {
                x[i] = Integer.parseInt(line.nextToken());
            }

            int[] consx = new int[4];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 4; i++) {
                consx[i] = Integer.parseInt(line.nextToken());
            }

            int[] y = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < k; i++) {
                y[i] = Integer.parseInt(line.nextToken());
            }

            int[] consy = new int[4];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 4; i++) {
                consy[i] = Integer.parseInt(line.nextToken());
            }

            for(int i = k; i < n; i++) {
                s[i] = (int)(((long)conss[0] * s[i - 2] + (long)conss[1] * s[i - 1] + conss[2]) % conss[3]);
                x[i] = (int)(((long)consx[0] * x[i - 2] + (long)consx[1] * x[i - 1] + consx[2]) % consx[3]);
                y[i] = (int)(((long)consy[0] * y[i - 2] + (long)consy[1] * y[i - 1] + consy[2]) % consy[3]);
            }

            long people = 0;
            long lower = 0;
            long upper = 0;
            long above = 0;
            long below = 0;
            for(int i = 0; i < n; i++) {
                people += s[i];
                lower += x[i];
                upper += x[i] + y[i];
                above += Math.max(0, s[i] - (x[i] + y[i]));
                below += Math.max(0, x[i] - s[i]);
            }
            String res = "Case #" + t + ": ";
            if(people < lower || people > upper) {
                out.println(res + -1);
            }else {
                out.println(res + Math.max(above, below));
            }
        }
        in.close();
        out.close();
    }
}
