import java.util.*;
import java.io.*;

public class procoI {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        long avg = 0;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            avg += a[i];
        }
        avg = avg / n;
        int[] pre = new int[n];
        HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++) {
            a[i] -= avg;
            pre[i] = (i > 0 ? pre[i - 1] : 0) + a[i];
            Integer freq = freqs.get(pre[i]);
            if(freq == null) freq = 0;
            freqs.put(pre[i], freq + 1);
        }
        int max = 0;
        for(int key : freqs.keySet()) {
            max = Math.max(max, freqs.get(key));
        }
        out.println(n - max);
        in.close();
        out.close();
    }
}
