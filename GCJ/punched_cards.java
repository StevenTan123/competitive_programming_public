import java.util.*;
import java.io.*;

public class punched_cards {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            out.println("Case #" + t + ": ");
            for(int i = 0; i < r * 2 + 1; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < c * 2 + 1; j++) {
                    if(i % 2 != 0 && j % 2 != 0 || (i <= 1 && j <= 1)) {
                        sb.append('.');
                    }else if(i % 2 == 0 && j % 2 == 1) {
                        sb.append('-');
                    }else if(i % 2 == 1 && j % 2 == 0) {
                        sb.append('|');
                    }else {
                        sb.append('+');
                    }
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
