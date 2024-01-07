import java.util.*;
import java.io.*;

public class bathroom {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long n = Long.parseLong(line.nextToken());
            long k = Long.parseLong(line.nextToken());
            long lastgap = -1;
            TreeMap<Long, Long> gaps = new TreeMap<Long, Long>();
            gaps.put(n, (long) 1);
            while (k > 0) {
                long bigap = gaps.lastKey();
                long freq = gaps.get(bigap);
                long half1 = (bigap - 1) / 2;
                long half2 = bigap - 1 - half1;
                Long val1 = gaps.get(half1);
                if (val1 == null)
                    val1 = (long) 0;
                if (half1 > 0)
                    gaps.put(half1, val1 + freq);
                Long val2 = gaps.get(half2);
                if (val2 == null)
                    val2 = (long) 0;
                if (half2 > 0)
                    gaps.put(half2, val2 + freq);
                gaps.remove(bigap);
                k -= freq;
                lastgap = bigap;
            }
            long half1 = (lastgap - 1) / 2;
            long half2 = lastgap - 1 - half1;
            String res = "Case #" + t + ": " + Math.max(half1, half2) + " " + Math.min(half1, half2);
            out.println(res);
        }
        in.close();
        out.close();
    }
}