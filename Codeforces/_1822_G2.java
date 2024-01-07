import java.util.*;
import java.io.*;
 
public class _1822_G2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                Integer freq = freqs.get(a[i]);
                if (freq == null) {
                    freq = 0;
                }
                freqs.put(a[i], freq + 1);
            }
 
            long res = 0;
            for (int num : freqs.keySet()) {
                int freq = freqs.get(num);
                if (freq >= 3) {
                    res += (long) freq * (freq - 1) * (freq - 2);
                }
            
                for (int i = 1; i <= 1000; i++) {
                    if (num % i == 0) {
                        Integer prev = freqs.get(num / i);
                        if (i > 1 && (long) num * i <= 1000000000) {
                            Integer next = freqs.get(num * i);
                            if (prev != null && next != null) {
                                res += (long) prev * freq * next;
                            }
                        }
 
                        if (num / i > 1000) {
                            prev = freqs.get(i);
                            if ((long) num / i * num <= 1000000000) {
                                Integer next = freqs.get(num / i * num);
                                if (prev != null && next != null) {
                                    res += (long) prev * freq * next;
                                }
                            }
                        }
                    }
                }
            }
            out.println(res);
        }
        
        in.close();
        out.close();
    }
}
