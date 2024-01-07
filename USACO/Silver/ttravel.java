import java.util.*;
import java.io.*;

public class ttravel {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("ttravel.in"));
        PrintWriter out = new PrintWriter("ttravel.out");
        int n = Integer.parseInt(in.readLine());
        int[] save = new int[n + 1];
        int[] prev = new int[n + 1];
        save[0] = -1;
        for(int i = 1; i <= n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            char c = line.nextToken().charAt(0);
            if(c == 'a') {
                int k = Integer.parseInt(line.nextToken());
                save[i] = k;
                prev[i] = i - 1;
            }else if(c == 't'){
                int k = Integer.parseInt(line.nextToken());
                save[i] = save[k - 1];
                prev[i] = prev[k - 1];
            }else {
                save[i] = save[prev[i - 1]];
                prev[i] = prev[prev[i - 1]];
            }
            out.println(save[i]);
        }
        in.close();
        out.close();
    }
}