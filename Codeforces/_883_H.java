import java.util.*;
import java.io.*;

public class _883_H {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        String s = in.readLine();
        HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer freq = freqs.get(c);
            if(freq == null) freq = 0;
            freqs.put(c, freq + 1);
        }
        char[][] res = null;
        for(int i = 1; i <= n; i++) {
            res = split(n, i, freqs);
            if(res != null) break;
        }
        out.println(res.length);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[i].length; j++) {
                sb.append(res[i][j]);
            }
            if(i < res.length - 1) sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static char[][] split(int n, int k, HashMap<Character, Integer> freqs) {
        if(n % k != 0) return null;
        int size = n / k;
        char[][] pals = new char[k][size];
        if(size % 2 == 0) {
            boolean alleven = true;
            for(char c : freqs.keySet()) {
                if(freqs.get(c) % 2 == 1) alleven = false;
            }
            if(!alleven) return null;
            int rp = 0;
            int cp = 0;
            for(char c : freqs.keySet()) {
                int freq = freqs.get(c);
                for(int i = 0; i < freq; i += 2) {
                    pals[rp][cp] = c;
                    pals[rp][size - cp - 1] = c;
                    cp++;
                    if(cp >= size / 2) {
                        cp = 0;
                        rp++;
                    }
                }
            }
        }else {
            int mid = size / 2;
            int numodd = 0;
            for(char c : freqs.keySet()) {
                if(freqs.get(c) % 2 == 1) numodd++;
            }
            if(numodd > k) return null;
            HashMap<Character, Integer> freqs2 = new HashMap<Character, Integer>();
            int rp = 0;
            for(char c : freqs.keySet()) {
                int freq = freqs.get(c);
                if(freq % 2 == 1) {
                    pals[rp][mid] = c;
                    if(freq > 1) {
                        freqs2.put(c, freq - 1);
                    }
                    rp++;
                }else {
                    freqs2.put(c, freq);
                }
            }
            HashMap<Character, Integer> freqs3 = new HashMap<Character, Integer>(); 
            for(char c : freqs2.keySet()) {
                int freq = freqs2.get(c);
                int left = freq;
                for(int i = 0; i < freq; i++) {
                    if(rp >= pals.length) break;
                    pals[rp][mid] = c;
                    rp++;
                    left--;
                }
                if(left > 0) {
                    freqs3.put(c, left);
                }
            }
            rp = 0;
            int cp = 0;
            for(char c : freqs3.keySet()) {
                int freq = freqs3.get(c);
                for(int i = 0; i < freq; i += 2) {
                    pals[rp][cp] = c;
                    pals[rp][size - cp - 1] = c;
                    cp++;
                    if(cp >= mid) {
                        cp = 0;
                        rp++;
                    }
                }
            }
        }
        return pals;
    }
}
