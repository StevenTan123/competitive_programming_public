import java.util.*;
import java.io.*;

public class _1644_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            boolean[] seen_door = new boolean[3];
            boolean possible = true;
            for(int i = 0; i < 6; i++) {
                char c = s.charAt(i);
                if(c == 'R') {
                    seen_door[0] = true;
                }else if(c == 'G') {
                    seen_door[1] = true;
                }else if(c == 'B') {
                    seen_door[2] = true;
                }else if(c == 'r') {
                    if(seen_door[0]) {
                        possible = false;
                    }
                }else if(c == 'g') {
                    if(seen_door[1]) {
                        possible = false;
                    }
                }else {
                    if(seen_door[2]) {
                        possible = false;
                    }
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
