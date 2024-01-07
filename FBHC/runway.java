import java.util.*;
import java.io.*;

public class runway {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("runway_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            HashMap<Integer, Integer>[] arr = new HashMap[n + 1];
            HashMap<Integer, Integer> used = new HashMap<Integer, Integer>();
            int ans = 0;
            for(int i = 0; i < n + 1; i++) {
                arr[i] = new HashMap<Integer, Integer>();
                line = new StringTokenizer(in.readLine());
                for(int j = 0; j < m; j++) {
                    int cur = Integer.parseInt(line.nextToken());
                    Integer freq = arr[i].get(cur);
                    if(freq == null) freq = 0;
                    arr[i].put(cur, freq + 1);
                    if(i == 0) {
                        used.put(cur, 0);
                    }
                }
                if(i > 0) {
                    HashMap<Integer, Integer> next = new HashMap<Integer, Integer>();
                    int equal = 0;
                    for(int key : arr[i].keySet()) {
                        int freq = arr[i].get(key);
                        if(!arr[i - 1].containsKey(key)) {
                            next.put(key, freq);
                            continue;
                        }
                        int pfreq = arr[i - 1].get(key);
                        int use = used.get(key);
                        if(use < freq) {
                            int val = use;
                            if(pfreq < freq) {
                                val += freq - pfreq;
                            }
                            next.put(key, val);
                        }else {
                            next.put(key, freq);
                        }
                        equal += Math.min(freq, pfreq);
                    }
                    used = next;
                    ans += m - equal;
                }
            }
            int count = 0;
            for(int key : used.keySet()) {
                count += used.get(key);
            }
            String res = "Case #" + t + ": ";
            out.println(res + (ans - count));
        }
        in.close();
        out.close();
    }
}
