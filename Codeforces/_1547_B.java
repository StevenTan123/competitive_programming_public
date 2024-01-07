import java.util.*;
import java.io.*;

public class _1547_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            int[] indices = new int[26];
            Arrays.fill(indices, -1);
            boolean possible = true;
            for(int i = 0; i < s.length(); i++) {
                int val = s.charAt(i) - 97;
                if(indices[val] != -1) {
                    possible = false;
                    break;
                }else {
                    indices[val] = i;
                }
            }
            boolean stop = false;
            for(int i = 0; i < 26; i++) {
                if(stop && indices[i] != -1) {
                    possible = false;
                    break;
                }
                if(indices[i] == -1) stop = true;
            }
            if(!possible) {
                out.println("NO");
                continue;
            }
            int l = indices[0];
            int r = indices[0];
            for(int i = 1; i < 26; i++) {
                if(indices[i] == -1) {
                    break;
                }else if(indices[i] == l - 1) {
                    l--;
                }else if(indices[i] == r + 1) {
                    r++;
                }else {
                    possible = false;
                    break;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
