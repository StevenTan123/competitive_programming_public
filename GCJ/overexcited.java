import java.util.*;
import java.io.*;

public class overexcited {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            String moves = line.nextToken();
            int meet = -1;
            for(int i = 0; i < moves.length(); i++) {
                int dist = Math.abs(x) + Math.abs(y);
                if(dist <= i) {
                    meet = i;
                    break;
                }
                char c = moves.charAt(i);
                if(c == 'N') y++;
                else if(c == 'E') x++;
                else if(c == 'S') y--;
                else x--;
            }
            if(meet == -1) {
                int dist = Math.abs(x) + Math.abs(y);
                if(dist <= moves.length()) meet = moves.length();
            }
            String res = "Case #" + t + ": ";
            if(meet == -1) out.println(res + "IMPOSSIBLE");
            else out.println(res + meet);
        }
        in.close();
        out.close();
    }
}
