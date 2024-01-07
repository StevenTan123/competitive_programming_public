import java.util.*;
import java.io.*;

public class oversized {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int d = Integer.parseInt(line.nextToken());
            long[] a = new long[n];
            line = new StringTokenizer(in.readLine());
            HashMap<Long, Long> freqs = new HashMap<Long, Long>();
            long max = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Long.parseLong(line.nextToken());
                max = Math.max(max, a[i]);
                Long freq = freqs.get(a[i]);
                if(freq == null) freq = (long)0;
                freqs.put(a[i], freq + 1);
            }
            String res = "Case #" + t + ": ";
            if(d == 2) {
                boolean dupe = false;
                for(long key : freqs.keySet()) {
                    if(freqs.get(key) > 1) dupe = true;
                }
                out.println(res + (dupe ? 0 : 1));
            }else {
                boolean trip = false;
                boolean doub = false;
                boolean doub2 = false;
                for(long key : freqs.keySet()) {
                    if(freqs.get(key) > 2) trip = true;
                    if(freqs.get(key) > 1 && key < max) doub = true;
                    if(freqs.containsKey(key * 2)) doub2 = true;
                }
                if(trip) out.println(res + 0);
                else if(doub || doub2) out.println(res + 1);
                else out.println(res + 2);
            }
        }
        in.close();
        out.close();
    }
}
