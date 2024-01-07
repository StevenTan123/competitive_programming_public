import java.util.*;
import java.io.*;

public class overrandomized {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int u = Integer.parseInt(in.readLine());
            HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
            HashSet<Character> chars = new HashSet<Character>();
            for(int i = 0; i < 10000; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                long m = Long.parseLong(line.nextToken());
                String str = line.nextToken();
                for(int j = 0; j < str.length(); j++) {
                    char c = str.charAt(j);
                    if(j == 0) {
                        Integer freq = freqs.get(c);
                        if(freq == null) freq = 0;
                        freqs.put(c, freq + 1);
                    }
                    chars.add(c);
                }
            }
            Pair[] pairs = new Pair[freqs.size()];
            int pointer = 0;
            for(char c : freqs.keySet()) {
                pairs[pointer] = new Pair(freqs.get(c), c);
                pointer++;
            }
            Arrays.sort(pairs);
            char[] darr = new char[10];
            for(char c : chars) {
                if(!freqs.containsKey(c)) darr[0] = c;
            }
            for(int i = 0; i < pairs.length; i++) {
                darr[i + 1] = pairs[i].b;
            }
            String res = "Case #" + t + ": ";
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 10; i++) {
                sb.append(darr[i]);
            }
            out.println(res + sb.toString());
        }
        in.close();
        out.close();
    }
    static class Pair implements Comparable<Pair> {
        int a;
        char b;
        Pair(int aa, char bb) {
            a = aa;
            b = bb;
        }
        @Override
        public int compareTo(Pair o) {
            if(a == o.a) return b - o.b;
            return o.a - a;
        }
    }
}