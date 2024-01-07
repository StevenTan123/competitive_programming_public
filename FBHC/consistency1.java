import java.util.*;
import java.io.*;

public class consistency1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("consistency_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String s = in.readLine();
            HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                Integer freq = freqs.get(c);
                if(freq == null) freq = 0;
                freqs.put(c, freq + 1);
            }
            int best_vowel = 0;
            int num_vowel = 0;
            int best_cons = 0;
            int num_cons = 0;
            for(char c : freqs.keySet()) {
                if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    best_vowel = Math.max(best_vowel, freqs.get(c));
                    num_vowel += freqs.get(c);
                }else {
                    best_cons = Math.max(best_cons, freqs.get(c));
                    num_cons += freqs.get(c);
                }
            }
            int ans = (num_vowel - best_vowel) * 2 + num_cons;
            ans = Math.min(ans, (num_cons - best_cons) * 2 + num_vowel);
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
}
