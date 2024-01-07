import java.util.*;
import java.io.*;

public class _1506_G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            int[] chars = new int[s.length()];
            for(int i = 0; i < s.length(); i++) {
                chars[i] = s.charAt(i) - 97;
            }
            StringBuilder sb = new StringBuilder();
            while(chars.length > 0) {
                int[] occs = new int[26];
                int[] freqs = new int[26];
                Arrays.fill(occs, -1);
                for(int i = 0; i < chars.length; i++) {
                    if(occs[chars[i]] == -1) {
                        occs[chars[i]] = i;
                    }
                    freqs[chars[i]]++;
                }
                int illegal = chars.length;
                int[] newfreq = new int[26];
                for(int i = 0; i < chars.length; i++) {
                    newfreq[chars[i]]++;
                    if(newfreq[chars[i]] == freqs[chars[i]]) {
                        illegal = i;
                        break;
                    }
                }
                for(int i = 25; i >= 0; i--) {
                    if(occs[i] != -1 && occs[i] <= illegal) {
                        sb.append((char)(i + 97));
                        int[] newchars = new int[chars.length - occs[i] - freqs[i]];
                        int p = 0;
                        for(int j = occs[i] + 1; j < chars.length; j++) {
                            if(chars[j] != i) {
                                newchars[p] = chars[j];
                                p++;
                            }
                        }
                        chars = newchars;
                        break;
                    }
                }
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
