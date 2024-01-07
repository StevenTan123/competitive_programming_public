import java.util.*;
import java.io.*;

public class _1493_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            String s = in.readLine();
            int[] sarr = new int[n];
            HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
            for(int j = 0; j < n; j++) {
                sarr[j] = (int)s.charAt(j) - 97;
                Integer val = freqs.get(sarr[j]);
                if(val == null) val = 0;
                freqs.put(sarr[j], val + 1);
            }
            if(n % k != 0) {
                out.println(-1);
                continue;
            }
            HashMap<Integer, Integer> res = null;
            int ind = -1;
            for(int j = n; j >= 0; j--) {
                if(j == n) {
                    boolean beautiful = true;
                    for(int letter : freqs.keySet()) {
                        if(freqs.get(letter) % k != 0) {
                            beautiful = false;
                            break;
                        }
                    }
                    if(beautiful) {
                        res = freqs;
                        ind = j;
                        break;
                    }
                    continue;
                }
                Integer val = freqs.get(sarr[j]);
                if(val == 1) {
                    freqs.remove(sarr[j]);
                }else {
                    freqs.put(sarr[j], val - 1);
                }
                res = next_beautiful(sarr, j, freqs, k);
                if(res != null) {
                    ind = j;
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < ind; j++) {
                char c = (char)(sarr[j] + 97);
                sb.append(c);
                Integer val = res.get(sarr[j]);
                if(val == 1) {
                    res.remove(sarr[j]);
                }else {
                    res.put(sarr[j], val - 1);
                }
            }
            for(int j = 0; j < 26; j++) {
                if(res.containsKey(j) && j > sarr[ind]) {
                    char c = (char)(j + 97);
                    sb.append(c);
                    int val = res.get(j);
                    if(val == 1) res.remove(j);
                    else res.put(j, val - 1);
                    break;
                }
            }
            for(int j = 0; j < 26; j++) {
                Integer freq = res.get(j);
                if(freq == null) freq = 0;
                for(int a = 0; a < freq; a++) {
                    char c = (char)(j + 97);
                    sb.append(c);
                }
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static HashMap<Integer, Integer> next_beautiful(int[] sarr, int ind, HashMap<Integer, Integer> freqs, int k) {
        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>(freqs);
        int curletter = sarr[ind];
        if(curletter == 25) return null;
        boolean greaterused = false;
        boolean greaterused2 = false;
        boolean finished = true;
        int left = sarr.length - ind;
        for(int letter : res.keySet()) {
            int freq = res.get(letter);
            int ceil = freq / k;
            if(freq % k != 0) ceil++;
            int goal = ceil * k;
            left -= goal - freq;
            if(left >= 0) {
                if(letter > curletter && goal - freq > 0) {
                    greaterused = true;
                    if(letter == curletter + 1) greaterused2 = true;
                }
            }else {
                finished = false;
                break;
            }
            res.put(letter, goal);
        }
        if(!finished) return null;
        if(!greaterused && left == 0) return null;
        if(greaterused2) {
            Integer val = res.get(0);
            if(val == null) val = 0;
            if(val + left > 0) res.put(0, val + left);
        }else if(left > 0) {
            Integer val = res.get(curletter + 1);
            if(val == null) val = 0;
            res.put(curletter + 1, val + k);
            left -= k;
            val = res.get(0);
            if(val == null) val = 0;
            if(val + left > 0) res.put(0, val + left);
        }
        return res;
    }
}
