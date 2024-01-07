import java.util.*;
import java.io.*;

public class pattern_matching {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String[] p = new String[n];
            int[][] splits = new int[n][2];
            int pref = 0;
            int suf = 0;
            for(int i = 0; i < n; i++) {
                p[i] = in.readLine();
                int first = -1;
                int last = -1;
                for(int j = 0; j < p[i].length(); j++) {
                    if(p[i].charAt(j) == '*') {
                        if(first == -1) first = j;
                        last = j;
                    }
                }
                splits[i][0] = first;
                splits[i][1] = last;
                if(splits[i][0] > splits[pref][0]) pref = i;
                if(p[i].length() - splits[i][1] > p[suf].length() - splits[suf][1]) suf = i;
            }
            boolean possible = true;
            String longpref = p[pref].substring(0, splits[pref][0]);
            String longsuf = p[suf].substring(splits[suf][1] + 1);
            for(int i = 0; i < n; i++) {
                String curpref = p[i].substring(0, splits[i][0]);
                String cursuf = p[i].substring(splits[i][1] + 1);
                if(!longpref.startsWith(curpref) || !longsuf.endsWith(cursuf)) {
                    possible = false;
                    break;
                }
            }
            String res = "Case #" + t + ": ";
            if(!possible) {
                out.println(res + "*");
                continue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(longpref);
            for(int i = 0; i < n; i++) {
                for(int j = splits[i][0] + 1; j < splits[i][1]; j++) {
                    char c = p[i].charAt(j);
                    if(c != '*') sb.append(c);
                }
            }
            sb.append(longsuf);
            out.println(res + sb.toString());
        }
        in.close();
        out.close();
    }
}
